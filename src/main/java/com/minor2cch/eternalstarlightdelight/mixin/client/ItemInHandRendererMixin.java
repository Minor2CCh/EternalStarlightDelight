package com.minor2cch.eternalstarlightdelight.mixin.client;

import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.util.ESDTags;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
    @WrapOperation(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getUseAnimation()Lnet/minecraft/world/item/UseAnim;"))
    private UseAnim getUseAnimation(ItemStack instance, Operation<UseAnim> original, @Local(argsOnly = true) AbstractClientPlayer player) {
        if (ESAccessoryUtil.getActiveAccessoriesOnArmors(player).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get()) && instance.is(ESDTags.Items.CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS) && !instance.is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET)) {
            return UseAnim.EAT;
        }
        return original.call(instance);
    }
}
