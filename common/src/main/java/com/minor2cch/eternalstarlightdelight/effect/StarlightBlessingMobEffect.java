package com.minor2cch.eternalstarlightdelight.effect;

import cn.leolezury.eternalstarlight.common.registry.ESDataAttachments;
import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class StarlightBlessingMobEffect extends MobEffect {
    public StarlightBlessingMobEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }
    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int i) {
        if(livingEntity.hasEffect(ESMobEffects.NUMBNESS.asHolder())){
            float damage = ESDataAttachments.NUMBNESS_DAMAGE.getData(livingEntity);
            if (damage > 0.0F) {
                ESDataAttachments.NUMBNESS_DAMAGE.setData(livingEntity, Math.max(damage - 1, 0.0F));
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        int k = 100 >> j;
        return k == 0 || i % k == 0;
    }
}
