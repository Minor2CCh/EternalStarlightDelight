package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.block.MarimoldBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MarimoldBlock.class)
public interface MarimoldBlockInvoker {
    @Invoker("mayPlaceOn")
    boolean getMarimoldMayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos);
}
