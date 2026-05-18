package com.minor2cch.eternalstarlightdelight.fabric.worldgen;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.util.ESDBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import vectorwing.farmersdelight.common.registry.ModBiomeModifiers;

public final class ESDFeatures {
    private ESDFeatures() {}
    private static final ResourceKey<PlacedFeature> WILD_CABBAGE = register("patch_wild_cabbages");
    private static final ResourceKey<PlacedFeature> WILD_BEETROOT = register("patch_wild_beetroots");
    private static final ResourceKey<PlacedFeature> WILD_CARROTS = register("patch_wild_carrots");
    private static final ResourceKey<PlacedFeature> WILD_ONIONS = register("patch_wild_onions");
    private static final ResourceKey<PlacedFeature> WILD_TOMATOES = register("patch_wild_tomatoes");
    private static final ResourceKey<PlacedFeature> WILD_POTATOES = register("patch_wild_potatoes");
    private static final ResourceKey<PlacedFeature> WILD_RICE = register("patch_wild_rice");
    public static ResourceKey<PlacedFeature> register(String id) {
        ResourceLocation oreID = EternalStarlightDelight.of(id);
        return ResourceKey.create(Registries.PLACED_FEATURE, oreID);
    }
    public static void init() {
        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ESDBiomeTags.WILD_CABBAGE_WHITELIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_CABBAGE);

        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ESDBiomeTags.WILD_BEETROOT_WHITELIST),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_BEETROOT);
        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(0.4f, 0.9f,
                        ESDBiomeTags.IN_ETERNAL_STARLIGHT, null),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_CARROTS);

        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(0.4f, 0.9f,
                        ESDBiomeTags.IN_ETERNAL_STARLIGHT, null),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_ONIONS);

        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(-0.5f, 0.3f,
                        ESDBiomeTags.IN_ETERNAL_STARLIGHT, null),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_POTATOES);
        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(-4, 4,
                        ESDBiomeTags.WILD_RICE_WHITELIST, null),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_RICE);
        BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(-4f, 4f,
                        ESDBiomeTags.WILD_TOMATOES_WHITELIST, null),
                GenerationStep.Decoration.VEGETAL_DECORATION, WILD_TOMATOES);

    }
}
