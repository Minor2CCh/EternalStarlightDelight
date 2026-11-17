package com.minor2cch.eternalstarlightdelight.util;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;

public final class ESDTags {
    private ESDTags() {}
    public static final class Items {
        private Items() {}
        public static final TagKey<Item> KNIFE_ACCESSORY_APPLICABLE = of("knife_accessory_applicable");
        public static final TagKey<Item> SKILLET_ACCESSORY_APPLICABLE = of("skillet_accessory_applicable");
        public static final TagKey<Item> HELMET_ACCESSORY_APPLICABLE = of("helmet_accessory_applicable");
        public static final TagKey<Item> CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS = of("consumable_when_wearing_starlight_flower_headdress");
        public static final TagKey<Item> EXTRA_STARLIGHT_BLESSING_FLOWERS = of("extra_starlight_bressing_flowers");
        private static TagKey<Item> of(String id) {
            return TagKey.create(Registries.ITEM, EternalStarlightDelight.of(id));
        }
    }
    @SuppressWarnings("unused")
    public static final class Biomes {
        private Biomes() {}
        public static final TagKey<Biome> IN_ETERNAL_STARLIGHT = of("in_eternal_starlight");
        public static final TagKey<Biome> WILD_RICE_WHITELIST = of("wild_rice_whitelist");
        public static final TagKey<Biome> WILD_TOMATOES_WHITELIST = of("wild_tomato_whitelist");
        public static final TagKey<Biome> WILD_CABBAGE_WHITELIST = of("wild_cabbage_whitelist");
        public static final TagKey<Biome> WILD_BEETROOT_WHITELIST = of("wild_beetroot_whitelist");
        public static final TagKey<Biome> MUSHROOM_COLONIES_WHITELIST = of("mushroom_colonies_whitelist");
        private static TagKey<Biome> of(String id) {
            return TagKey.create(Registries.BIOME, EternalStarlightDelight.of(id));
        }
    }
    public static final class DamageTypes {
        private DamageTypes() {}
        public static final TagKey<DamageType> STARLIGHT_FLOWER_HEADDRESS_IMMUNE = of("starlight_flower_headdress_immune");
        @SuppressWarnings("all")
        private static TagKey<DamageType> of(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, EternalStarlightDelight.of(id));
        }
    }
}
