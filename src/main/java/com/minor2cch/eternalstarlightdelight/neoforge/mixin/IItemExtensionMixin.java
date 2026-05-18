package com.minor2cch.eternalstarlightdelight.neoforge.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.registry.ModItems;

@Mixin(IItemExtension.class)
public interface IItemExtensionMixin {
    @ModifyReturnValue(method = "isPrimaryItemFor(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/Holder;)Z", at = @At("RETURN"))
    private boolean bePrimaryEnchantment(boolean original, ItemStack stack, Holder<Enchantment> enchantment){
        if(stack.is(ESDItems.DEEPSILVER_SKILLET.get())){
            return original || ModItems.SKILLET.get().getDefaultInstance().isPrimaryItemFor(enchantment);
        }
        return original;
    }
}
