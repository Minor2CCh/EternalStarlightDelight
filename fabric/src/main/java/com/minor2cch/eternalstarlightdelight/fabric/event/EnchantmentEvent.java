package com.minor2cch.eternalstarlightdelight.fabric.event;

import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.world.item.enchantment.Enchantment;
import vectorwing.farmersdelight.common.registry.ModItems;

public final class EnchantmentEvent {
    private EnchantmentEvent() {}
    public static void init(){
        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, stack, context) -> {
            Enchantment enchant = enchantment.value();
            if(stack.is(ESDItems.DEEPSILVER_SKILLET.get())){
                if (enchant.isPrimaryItem(ModItems.SKILLET.get().getDefaultInstance()) && enchant.canEnchant(stack)) {
                    return TriState.TRUE;
                }
            }
            return TriState.DEFAULT;
        });
    }
}
