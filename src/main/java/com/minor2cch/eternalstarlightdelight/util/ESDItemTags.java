package com.minor2cch.eternalstarlightdelight.util;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ESDItemTags {
    private ESDItemTags() {}
    public static final TagKey<Item> KNIFE_ACCESSORY_APPLICABLE = of("knife_accessory_applicable");
    public static final TagKey<Item> SKILLET_ACCESSORY_APPLICABLE = of("skillet_accessory_applicable");
    private static TagKey<Item> of(String id) {
        return TagKey.create(Registries.ITEM, EternalStarlightDelight.of(id));
    }
}
