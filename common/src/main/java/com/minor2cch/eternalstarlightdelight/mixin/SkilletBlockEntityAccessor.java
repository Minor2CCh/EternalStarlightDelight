package com.minor2cch.eternalstarlightdelight.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

@Mixin(SkilletBlockEntity.class)
public interface SkilletBlockEntityAccessor {
    @Accessor("skilletStack")
    void setSkilletStack(ItemStack newSkilletStack);
}
