package com.minor2cch.eternalstarlightdelight.util;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class ESDBiomeTags {
    private ESDBiomeTags() {}
    public static final TagKey<Biome> IN_ETERNAL_STARLIGHT = of("in_eternal_starlight");
    public static final TagKey<Biome> WILD_RICE_WHITELIST = of("wild_rice_whitelist");
    public static final TagKey<Biome> WILD_TOMATOES_WHITELIST = of("wild_tomato_whitelist");
    public static final TagKey<Biome> WILD_CABBAGE_WHITELIST = of("wild_cabbage_whitelist");
    public static final TagKey<Biome> WILD_BEETROOT_WHITELIST = of("wild_beetroot_whitelist");
    private static TagKey<Biome> of(String id) {
        return TagKey.create(Registries.BIOME, EternalStarlightDelight.of(id));
    }
}
