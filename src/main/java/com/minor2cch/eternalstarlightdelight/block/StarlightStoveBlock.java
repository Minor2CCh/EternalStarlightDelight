package com.minor2cch.eternalstarlightdelight.block;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.block.entity.StarlightStoveBlockEntity;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.block.AbstractStoveBlock;

import javax.annotation.Nullable;

public class StarlightStoveBlock extends AbstractStoveBlock {
    public static final BooleanProperty STARFIRE = BooleanProperty.create("starfire");
    public static final MapCodec<StarlightStoveBlock> CODEC = simpleCodec(StarlightStoveBlock::new);

    @Override
    public MapCodec<StarlightStoveBlock> codec() {
        return CODEC;
    }
    public StarlightStoveBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(STARFIRE, false));
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ESDBlockEntityTypes.STARLIGHT_STOVE.get().create(pos, state);
    }
    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(ESItems.STARFIRE.get()) && !state.getValue(STARFIRE) && state.getValue(LIT)) {
            level.setBlockAndUpdate(pos, state.setValue(STARFIRE, true));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return blockState.getValue(LIT) ? createTickerHelper(blockEntityType, ESDBlockEntityTypes.STARLIGHT_STOVE.get(), StarlightStoveBlockEntity::particleTick) : null;
        } else {
            return createTickerHelper(blockEntityType, ESDBlockEntityTypes.STARLIGHT_STOVE.get(), StarlightStoveBlockEntity::serverTick);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STARFIRE);
    }
}
