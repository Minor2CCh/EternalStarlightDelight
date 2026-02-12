package com.minor2cch.eternalstarlightdelight.fabric.event;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import vectorwing.farmersdelight.common.tag.ModTags;

public class LootModifyEvent {
    private static final ResourceKey<LootTable> ENTITIES_AURORA_DEER = esKey("entities/aurora_deer");
    private static final ResourceKey<LootTable> ENTITIES_NIGHTFALL_SPIDER = esKey("entities/nightfall_spider");
    private static final ResourceKey<LootTable> ENTITIES_RATLIN = esKey("entities/ratlin");
    private static final ResourceKey<LootTable> ENTITIES_YETI = esKey("entities/yeti");
    private static final ResourceKey<LootTable> ENTITIES_ZOMBIFIED_RATLIN = esKey("entities/zombified_ratlin");
    @SuppressWarnings("all")
    private static ResourceKey<LootTable> key(String name, String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(name, path));
    }
    private static ResourceKey<LootTable> esKey(String path) {
        return key(EternalStarlight.ID, path);
    }
    public static void init(){
        LootTableEvents.MODIFY.register(LootModifyEvent::modifyTable);
    }
    private static void modifyTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        // scavenging_string
        if (key == ENTITIES_NIGHTFALL_SPIDER || key == ENTITIES_YETI) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.STRING)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.KNIVES))
                    )))));
        }
        if (key == ENTITIES_AURORA_DEER || key == ENTITIES_RATLIN || key == ENTITIES_ZOMBIFIED_RATLIN) {
            tableBuilder.withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.LEATHER)
                    .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().equipment(
                            EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.KNIVES))
                    )))));
        }

    }
}
