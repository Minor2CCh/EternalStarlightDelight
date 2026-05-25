package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minor2cch.eternalstarlightdelight.block.BouldershroomColonyBlock;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.RichSoilBlock;


@Mixin(RichSoilBlock.class)
public class RichSoilMixin {
    @ModifyReturnValue(at = @At("RETURN"), method = "convertMushroomToColony")
    private boolean ESShroomColonized(boolean original, BlockState targetState, BlockPos targetPos, ServerLevel level){
        if (!original) {
            if (targetState.is(ESBlocks.BOULDERSHROOM.get())) {
                return true;
            }
            if (targetState.is(ESBlocks.GLOWING_MUSHROOM.get())) {
                level.setBlockAndUpdate(targetPos, ESDBlocks.GLOWING_MUSHROOM_COLONY.get().defaultBlockState());
                return true;
            }
            if (targetState.is(ESBlocks.SHINING_MUSHROOM.get())) {
                level.setBlockAndUpdate(targetPos, ESDBlocks.SHINING_MUSHROOM_COLONY.get().defaultBlockState());
                return true;
            }
            if (targetState.is(ESBlocks.MARIMOLD.get())) {
                boolean waterlogged = targetState.getValue(BlockStateProperties.WATERLOGGED);
                level.setBlockAndUpdate(targetPos, ESDBlocks.MARIMOLD_COLONY.get().defaultBlockState().setValue(BouldershroomColonyBlock.WATERLOGGED, waterlogged));
                return true;
            }
        }
        return original;

    }

}
