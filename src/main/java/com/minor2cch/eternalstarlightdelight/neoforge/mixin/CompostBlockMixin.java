package com.minor2cch.eternalstarlightdelight.neoforge.mixin;

import com.minor2cch.eternalstarlightdelight.neoforge.platform.NeoForgePlatform;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class CompostBlockMixin {
    @Inject(method = "getValue(Lnet/minecraft/world/item/ItemStack;)F", at = @At("HEAD"), cancellable = true)
    private static void checkCustomCompostItem(ItemStack item, CallbackInfoReturnable<Float> cir){
        Float compostChance = NeoForgePlatform.COMPOST_TRUE_MAP.get(item.getItem());
        if(compostChance != null){
            cir.setReturnValue(compostChance);
            cir.cancel();
        }
    }
}
