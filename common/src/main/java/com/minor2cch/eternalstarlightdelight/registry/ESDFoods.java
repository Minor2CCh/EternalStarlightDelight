package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import com.google.common.base.Suppliers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.function.Supplier;

public final class ESDFoods {
    private ESDFoods() {}
    // MODのエフェクトを使う場合はSupplierにする必要あり
    public static final Supplier<FoodProperties> DRIED_ROOKFISH = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).effect(new MobEffectInstance(ModEffects.COMFORT, 600, 0, false, false, false), 1.0F).build());
    public static final Supplier<FoodProperties> PUNGENCY_FRUIT_SLICE = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(2).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0), 0.8F).build());
    public static final Supplier<FoodProperties> STARLIGHT_SOUP = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(7).saturationModifier(0.6F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F).build());
    public static final Supplier<FoodProperties> SHADOW_SNAIL_PIE_SLICE = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(2).saturationModifier(0.6F).fast().build());
    public static final Supplier<FoodProperties> ABYSSAL_JUICE = Suppliers.memoize(() -> new FoodProperties.Builder().alwaysEdible().effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 1200, 0), 1.0F).build());
    public static final Supplier<FoodProperties> ETHER_BOTTLE = Suppliers.memoize(() -> new FoodProperties.Builder().alwaysEdible().effect(new MobEffectInstance(ESDMobEffects.ETHER_INFESTED, 1200, 0), 1.0F).build());
    public static final Supplier<FoodProperties> AETHERSENT_FLAVOR_CANDY = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).fast().alwaysEdible().effect(new MobEffectInstance(ESDMobEffects.METEOR_OMEN, 1200, 1), 1.0F).build());
    public static final Supplier<FoodProperties> STARCORE_FLAVOR_CANDY = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).fast().alwaysEdible().effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F).build());
    public static final Supplier<FoodProperties> STARFIRE_FRIED_EGG = Suppliers.memoize(() -> new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.6f).effect(new MobEffectInstance(ESDMobEffects.STARFIRE_WARMTH, 600, 1), 1.0F).build());
    public static final Supplier<FoodProperties> CRINOA_DOUGH = Suppliers.memoize(() -> new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3f).effect(new MobEffectInstance(ESMobEffects.CRYSTAL_INFECTION.asHolder(), 600, 0), 0.3F).build());
    public static final Supplier<FoodProperties> CRINOA_BREAD = Suppliers.memoize(() -> new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6f).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 600, 0, false, false, false), 1.0F).build());
    public static final Supplier<FoodProperties> MARIMOLD_STEW = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).effect(new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false, false), 1.0F).effect(new MobEffectInstance(MobEffects.GLOWING, 600, 0), 1.0F).build());
    public static final Supplier<FoodProperties> STARLIGHT_HAMBURGER = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F).build());
    public static final Supplier<FoodProperties> AURORA_DEER_DICE_STEAK_PASTA = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(14).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0, false, false, false), 1.0f).build());
    public static final Supplier<FoodProperties> LUNARIS_SHERBET = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(7).saturationModifier(0.6F).alwaysEdible().effect(new MobEffectInstance(ESDMobEffects.STARLIGHT_BLESSING, 1200, 0), 1.0F).build());
    public static final Supplier<FoodProperties> AMARAMBER_POPSICLE = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(3).saturationModifier(0.6F).fast().alwaysEdible().effect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1), 1.0F).build());
    public static final Supplier<FoodProperties> STARMINA_NOODLE_SOUP = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(14).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0), 1.0f).effect(new MobEffectInstance(ESDMobEffects.STARLIGHT_BLESSING, 3600, 0), 1.0f).build());
    public static final Supplier<FoodProperties> AURORA_DEER_JERKY = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(3).saturationModifier(1.0F).effect(new MobEffectInstance(ModEffects.COMFORT, 600, 0, false, false, false), 1.0F).effect(new MobEffectInstance(ESDMobEffects.STARLIGHT_BLESSING, 300, 0), 1.0f).fast().build());
    public static final Supplier<FoodProperties> AURORA_DEER_STEAK_SPECIAL_LUNCH = Suppliers.memoize(() -> new FoodProperties.Builder().nutrition(16).saturationModifier(0.75F).effect(new MobEffectInstance(ModEffects.NOURISHMENT, 3600, 0, false, false, false), 1.0f).effect(new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false, false), 1.0f).effect(new MobEffectInstance(ESDMobEffects.STARFIRE_WARMTH, 3600, 0), 1.0f).build());
}
