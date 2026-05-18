package com.minor2cch.eternalstarlightdelight.effect;

import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import com.minor2cch.eternalstarlightdelight.registry.ESDMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class StarfireWarmthMobEffect extends MobEffect {
    public StarfireWarmthMobEffect(MobEffectCategory mobEffectCategory, int i) {
        super(mobEffectCategory, i);
    }
    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int i) {
        if(!livingEntity.level().isClientSide()){
            AABB box = AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(livingEntity.getOnPos())).inflate(10);
            for (LivingEntity target : livingEntity.level().getEntitiesOfClass(LivingEntity.class, box)) {
                if (!isEnemy(livingEntity, target)) {
                    if(i > 0){ // II以上の場合は味方に星火のダメージが及ばないようにする
                        if(!target.hasEffect(ESDMobEffects.STARFIRE_WARMTH)){
                            target.addEffect(new MobEffectInstance(ESDMobEffects.STARFIRE_WARMTH, 99));
                        }

                    }
                    target.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
                }
                if(i > 0){
                    if (isEnemy(livingEntity, target)) {
                        target.addEffect(new MobEffectInstance(ESMobEffects.STARFIRE.asHolder(), 100));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return i % 100 == 0;
    }
    private static boolean isEnemy(@NotNull LivingEntity self, @NotNull LivingEntity target){
        if(Objects.equals(self, target)){ // 自分自身の場合
            return false;
        }
        if(target instanceof OwnableEntity animal && Objects.equals(animal.getOwner(), self)){ // 対象の飼い主が自分の場合
            return false;
        }
        if(self instanceof OwnableEntity animal && Objects.equals(animal.getOwner(), target)){ // 自分がペット化でき、かつ対象が飼い主の場合
            return false;
        }
        if(self instanceof Enemy){ // モンスターの場合
            if(target instanceof Mob mob){ //Mobに付与する場合
                if(Objects.equals(self, mob.getTarget())){ //そのMobに敵対されている
                    return true;
                }
            }
            return !(target instanceof Enemy); //モンスター以外なら敵


        }else{ // その他の場合
            if(target instanceof Mob mob){ //Mobに付与する場合
                if(Objects.equals(self, mob.getTarget())){ //そのMobに敵対されている
                    return true;
                }
            }
            return target instanceof Enemy; //モンスターなら敵
        }

    }
}
