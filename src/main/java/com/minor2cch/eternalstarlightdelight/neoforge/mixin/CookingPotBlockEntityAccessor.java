package com.minor2cch.eternalstarlightdelight.neoforge.mixin;

import net.neoforged.neoforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@Mixin(CookingPotBlockEntity.class)
public interface CookingPotBlockEntityAccessor {
    @Accessor("inputHandler")
    IItemHandler getInputHandler();
    @Accessor("outputHandler")
    IItemHandler getOutputHandler();
}
