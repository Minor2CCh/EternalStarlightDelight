package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mixin(SkilletItem.SkilletEvents.class)
public class SkilletEventsMixin {
    @WrapOperation(
            method = "playSkilletAttackSound",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean wrapIsSkillet(ItemStack instance, Item arg, Operation<Boolean> original) {
        return original.call(instance, arg) || arg == ModItems.SKILLET.get() && original.call(instance, ESDItems.DEEPSILVER_SKILLET.get());
    }
}
