package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.registry.ESAttributes;
import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.effect.EmptyMobEffect;
import com.minor2cch.eternalstarlightdelight.effect.StarfireWarmthMobEffect;
import com.minor2cch.eternalstarlightdelight.effect.StarlightBlessingMobEffect;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Supplier;

public final class ESDMobEffects {
    private ESDMobEffects() {}
    public static final Holder<MobEffect> ETHER_INFESTED = registerMobEffect("ether_infested", () -> new EmptyMobEffect(MobEffectCategory.HARMFUL, 0xD1FFE1)
            .addAttributeModifier(Attributes.ARMOR, EternalStarlightDelight.of("ether_infested_armor"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, EternalStarlightDelight.of("ether_infested_armor_toughness"), -1.0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    public static final Holder<MobEffect> METEOR_OMEN = registerMobEffect("meteor_omen", () -> new EmptyMobEffect(MobEffectCategory.BENEFICIAL, 0xE9ADED)
            .addAttributeModifier(ESAttributes.METEOR_COUNTERATTACK_CHANCE.asHolder(), EternalStarlightDelight.of("meteor_extra_chance"), 0.25, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> STARLIGHT_BLESSING = registerMobEffect("starlight_blessing", () -> new StarlightBlessingMobEffect(MobEffectCategory.BENEFICIAL, 0x7597EB)
            .addAttributeModifier(ESAttributes.HEAL_MULTIPLIER.asHolder(), EternalStarlightDelight.of("extra_heal_multiplier"), 0.25, AttributeModifier.Operation.ADD_VALUE));
    public static final Holder<MobEffect> STARFIRE_WARMTH = registerMobEffect("starfire_warmth", () -> new StarfireWarmthMobEffect(MobEffectCategory.BENEFICIAL, 0xFF7D3D));
    private static Holder<MobEffect> registerMobEffect(String id, Supplier<MobEffect> effect){
        return ESDPlatform.INSTANCE.mobEffectRegister(id, effect);
    }
    public static void init(){
    }
}
