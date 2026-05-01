package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.Configuration;

@Mixin(Item.class)
public class PlaceShadowSnailPieMixin {
    @Inject(
            method = "useOn",
            at = @At("TAIL"),
            cancellable = true)
    private void usePumpkinPie(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (!context.getItemInHand().is(ESItems.SHADOW_SNAIL_PIE.get()) || !ESDConfigLoader.getConfig().getPlaceableShadowSnailPie())
            return;

        if (Configuration.ENABLE_PUMPKIN_PIE_SNEAK_TO_PLACE.get()) {
            Player player = context.getPlayer();
            if (player != null && player.isSecondaryUseActive()) {
                cir.setReturnValue(ESDItems.DEBUG_SHADOW_SNAIL_PIE.get().useOn(context));
            }
        } else {
            cir.setReturnValue(ESDItems.DEBUG_SHADOW_SNAIL_PIE.get().useOn(context));
        }
    }
}
