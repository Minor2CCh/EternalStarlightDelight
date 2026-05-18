package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.block.BouldershroomBlock;
import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import com.minor2cch.eternalstarlightdelight.block.BouldershroomColonyBlock;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Inject(at = @At("HEAD"), method = "randomTick")
    public void BouldershroomColonized(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand, CallbackInfo ci){
        if(((Object)(this)) instanceof BouldershroomBlock block){
            if (!level.isClientSide) {
                Direction direction = state.getValue(BlockStateProperties.FACING);
                boolean waterlogged = state.getValue(BlockStateProperties.WATERLOGGED);
                BlockPos blockPos = pos.relative(direction.getOpposite());
                BlockState blockState = level.getBlockState(blockPos);
                Block floorBlock = blockState.getBlock();
                if (block == ESBlocks.BOULDERSHROOM.get()) {
                    if(floorBlock instanceof RichSoilBlock || floorBlock instanceof RichSoilFarmlandBlock){
                        level.setBlockAndUpdate(pos, ESDBlocks.BOULDERSHROOM_COLONY.get().defaultBlockState().setValue(BouldershroomColonyBlock.FACING, direction).setValue(BouldershroomColonyBlock.WATERLOGGED, waterlogged));
                    }
                }

            }
        }
    }
}
