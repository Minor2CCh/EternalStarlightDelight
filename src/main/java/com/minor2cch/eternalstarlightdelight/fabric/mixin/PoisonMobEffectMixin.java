package com.minor2cch.eternalstarlightdelight.fabric.mixin;

import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.registry.ESDMobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.effect.PoisonMobEffect")
public abstract class PoisonMobEffectMixin {
    @Inject(method = "applyEffectTick", at = @At("HEAD"), cancellable = true)
    private void avoidPoisonDamage(LivingEntity entity, int amplifier, CallbackInfoReturnable<Boolean> cir){
        if(ESAccessoryUtil.getActiveAccessoriesOnArmors(entity).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get())){
            if(entity.hasEffect(ESDMobEffects.STARLIGHT_BLESSING)){
                cir.setReturnValue(true);
            }
        }
    }
}
