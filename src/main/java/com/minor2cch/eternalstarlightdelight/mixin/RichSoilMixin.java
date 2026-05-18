package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import com.minor2cch.eternalstarlightdelight.block.BouldershroomColonyBlock;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.RichSoilBlock;


@Mixin(RichSoilBlock.class)
public class RichSoilMixin {
    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    public void ESShroomColonized(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand, CallbackInfo ci){
        if (!level.isClientSide) {
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            Block aboveBlock = aboveState.getBlock();
            if (aboveBlock == ESBlocks.BOULDERSHROOM.get()) {
                ci.cancel();
            }
            if (aboveBlock == ESBlocks.GLOWING_MUSHROOM.get()) {
                level.setBlockAndUpdate(pos.above(), ESDBlocks.GLOWING_MUSHROOM_COLONY.get().defaultBlockState());
                ci.cancel();
            }
            if (aboveBlock == ESBlocks.MARIMOLD.get()) {
                boolean waterlogged = aboveState.getValue(BlockStateProperties.WATERLOGGED);
                level.setBlockAndUpdate(pos.above(), ESDBlocks.MARIMOLD_COLONY.get().defaultBlockState().setValue(BouldershroomColonyBlock.WATERLOGGED, waterlogged));
                ci.cancel();
            }
        }

    }

}
