package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.block.NocturnalMilletTopBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vectorwing.farmersdelight.common.block.*;

@Mixin(NocturnalMilletTopBlock.class)
public abstract class NocturnalMilletTopBlockMixin {
    @Redirect(
            method = "randomTick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;destroyBlock(Lnet/minecraft/core/BlockPos;Z)Z"
            )
    )
    private boolean restraintDestroyBlock(
            ServerLevel instance, BlockPos pos, boolean dropBlock
    ) {
        Block block = instance.getBlockState(pos).getBlock();
        Block belowBlock = instance.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock();
        Block below2Block = instance.getBlockState(new BlockPos(pos.getX(), pos.getY() - 2, pos.getZ())).getBlock();
        Block below3Block = instance.getBlockState(new BlockPos(pos.getX(), pos.getY() - 3, pos.getZ())).getBlock();
        if((belowBlock instanceof RichSoilBlock || belowBlock instanceof RichSoilFarmlandBlock)
             || belowBlock instanceof CropBlock && (below2Block instanceof RichSoilBlock || below2Block instanceof RichSoilFarmlandBlock)
                || belowBlock instanceof CropBlock && below2Block instanceof CropBlock && (below3Block instanceof RichSoilBlock || below3Block instanceof RichSoilFarmlandBlock)
                || block instanceof RicePaniclesBlock && belowBlock instanceof RiceBlock && (below2Block instanceof RichSoilBlock || below2Block instanceof RichSoilFarmlandBlock)
                || block instanceof RicePaniclesBlock && belowBlock instanceof RiceBlock && below2Block instanceof CropBlock && (below3Block instanceof RichSoilBlock || below3Block instanceof RichSoilFarmlandBlock)){
            if(block instanceof CropBlock cropBlock){
                if(cropBlock.getAge(instance.getBlockState(pos)) > 0){
                    Block aboveBlock = instance.getBlockState(pos.above()).getBlock();
                    if(!(aboveBlock instanceof CropBlock) || (aboveBlock instanceof TomatoBlock)){
                        instance.setBlockAndUpdate(pos, instance.getBlockState(pos).setValue((((CropBlockInvoker)cropBlock).getCropAgeProperty()), Math.clamp(cropBlock.getAge(instance.getBlockState(pos)) - 1, 0, cropBlock.getMaxAge())));
                    }
                }
            }
            return false;
        }
        return instance.destroyBlock(pos, dropBlock, null);
    }
}
