package com.minor2cch.eternalstarlightdelight.fabric.mixin;

import cn.leolezury.eternalstarlight.common.block.PungencyFruitVinesBlock;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModBlocks;
// NeoForgeは素で植えることが出来るのでFabricのみ
@Mixin(PungencyFruitVinesBlock.class)
public class PungencyFruitVinesBlockMixin {
    @ModifyReturnValue(method = "mayPlaceOn(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z", at = @At("RETURN"))
    private boolean placeableRichSoilFarmlandBlock(boolean original, BlockState blockState, BlockGetter blockGetter, BlockPos blockPos){
        return original || blockState.is(ModBlocks.RICH_SOIL_FARMLAND.get());

    }

    @ModifyReturnValue(method = "canPlaceSeeds(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z", at = @At("RETURN"))
    private boolean canPlaceableRichSoilFarmlandBlock(boolean original, BlockState blockState, BlockGetter blockGetter, BlockPos blockPos){
        return original || blockState.is(ModBlocks.RICH_SOIL_FARMLAND.get());

    }
}
