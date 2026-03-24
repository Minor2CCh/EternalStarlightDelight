package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ESItemTiers.class)
public class ESItemTiersMixin {
    @Inject(at = @At("HEAD"), method = "getRepairIngredient()Lnet/minecraft/world/item/crafting/Ingredient;", cancellable = true)
    private void injectRepairIngredient(CallbackInfoReturnable<Ingredient> cir){
        ESItemTiers tier = (ESItemTiers)(Object)this;
        if(Objects.equals(tier, ESItemTiers.PUNGENCY_FRUIT)){
            cir.setReturnValue(Ingredient.of(ESItems.PUNGENCY_FRUIT.get(), ESDItems.PUNGENCY_FRUIT_SLICE.get()));
            cir.cancel();
        }
    }
}
