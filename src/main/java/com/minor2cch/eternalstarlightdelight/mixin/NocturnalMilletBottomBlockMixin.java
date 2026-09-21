package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.block.NocturnalMilletBottomBlock;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;

@Mixin(NocturnalMilletBottomBlock.class)
public class NocturnalMilletBottomBlockMixin {
    @ModifyReturnValue(at = @At("RETURN"), method = "canSurvive")
    private boolean ESShroomColonized(boolean original, BlockState state, LevelReader level, BlockPos pos){
        BlockState belowState = level.getBlockState(pos.below());
        return original || level.getRawBrightness(pos, 0) >= 8 && (belowState.getBlock() instanceof RichSoilFarmlandBlock);
    }

}
