package com.minor2cch.eternalstarlightdelight.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    @Shadow
    public abstract boolean canEnchant(ItemStack itemStack);
    @ModifyReturnValue(method = "canEnchant(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("RETURN"))
    private boolean canEnchantDeepSilverSkillet(boolean original, ItemStack itemStack) {
        return original || itemStack.is(ESDItems.DEEPSILVER_SKILLET.get()) && canEnchant(itemStack.transmuteCopy(ModItems.SKILLET.get()));
    }
}
