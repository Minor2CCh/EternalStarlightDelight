package com.minor2cch.eternalstarlightdelight.neoforge.block.entity;

import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverCookingPotInterface;
import com.minor2cch.eternalstarlightdelight.neoforge.registry.ESDBlockEntityTypesNeoForge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

public class DeepSilverCookingPotBlockEntityNeoForge extends CookingPotBlockEntity implements DeepSilverCookingPotInterface {
    public DeepSilverCookingPotBlockEntityNeoForge(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ESDBlockEntityTypesNeoForge.DEEPSILVER_COOKING_POT.get();
    }
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) {
        return this.getType().isValid(blockState);
    }
    public void animationTick(Level level, BlockPos pos, BlockState state) {
        CookingPotBlockEntity.animationTick(level, pos, state, this);
    }
    public void cookingTick(Level level, BlockPos pos, BlockState state) {
        CookingPotBlockEntity.cookingTick(level, pos, state, this);
    }
    @Override
    public Level getLevel(){
        return super.getLevel();
    }
}
