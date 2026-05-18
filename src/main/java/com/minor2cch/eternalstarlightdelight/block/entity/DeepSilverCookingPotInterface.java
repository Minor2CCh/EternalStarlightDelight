package com.minor2cch.eternalstarlightdelight.block.entity;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface DeepSilverCookingPotInterface {
    void animationTick(Level level, BlockPos pos, BlockState state);
    void cookingTick(Level level, BlockPos pos, BlockState state);
    static void animationTick(Level level, BlockPos pos, BlockState state, BlockEntity cookingPot){
        if(cookingPot instanceof DeepSilverCookingPotInterface deepSilverCookingPot){
            deepSilverCookingPot.animationTick(level, pos, state);
        }
    }
    static void cookingTick(Level level, BlockPos pos, BlockState state, BlockEntity cookingPot){
        if(cookingPot instanceof DeepSilverCookingPotInterface deepSilverCookingPot){
            deepSilverCookingPot.cookingTick(level, pos, state);
        }
    }
    static ItemStack getMealFromItem(ItemStack cookingPotStack) {
        if (!cookingPotStack.is(ESDItems.DEEPSILVER_COOKING_POT.get())) {
            return ItemStack.EMPTY;
        }

        return ESDPlatform.INSTANCE.getMealFromItem(cookingPotStack);
    }
    Level getLevel();
}
