package com.minor2cch.eternalstarlightdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;

public class BouldershroomColonyBlock extends MushroomColonyBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public BouldershroomColonyBlock(Holder<Item> mushroomType, Properties properties) {
        super(mushroomType, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.UP));
    }
    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        Direction direction = state.getValue(FACING);
        double border = 16 - (12 + state.getValue(getAgeProperty()));
        double height = 12 + state.getValue(getAgeProperty());
        return switch (direction) {
            case NORTH -> Block.box(border, border, (16 - height), (16 - border), (16 - border), 16.0D);
            case SOUTH -> Block.box(border, border, 0.0D, (16 - border), (16 - border), height);
            case EAST -> Block.box(0.0D, border, border, height, (16 - border), (16 - border));
            case WEST -> Block.box((16 - height), border, border, 16.0D, (16 - border), (16 - border));
            case DOWN -> Block.box(border, (16 - height), border, (16 - border), 16.0D, (16 - border));
            default -> Block.box(border, 0.0D, border, (16 - border), height, (16 - border));
        };
    }
    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        int age = state.getValue(COLONY_AGE);
        BlockPos groundPos = pos.relative(state.getValue(FACING).getOpposite());
        BlockState groundState = level.getBlockState(groundPos);
        if (age < getMaxAge() && groundState.is(ModTags.MUSHROOM_COLONY_GROWABLE_ON) && random.nextInt(4) == 0) {
            level.setBlock(pos, state.setValue(COLONY_AGE, age + 1), 2);
            //CommonHooks.fireCropGrowPost(level, pos, state);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos attachPos = pos.relative(direction.getOpposite());
        BlockState attachState = level.getBlockState(attachPos);
        return attachState.isFaceSturdy(level, attachPos, direction);
    }
    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction direction, @NotNull BlockState blockState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos, @NotNull BlockPos blockPos) {
        if (state.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(levelAccessor, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, levelAccessor, pos, blockPos);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState().setValue(WATERLOGGED, levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER).setValue(FACING, context.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, FACING);
    }
}
