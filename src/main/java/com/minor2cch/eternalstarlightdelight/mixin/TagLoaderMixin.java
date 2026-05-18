package com.minor2cch.eternalstarlightdelight.mixin;


import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mixin(TagLoader.class)
public class TagLoaderMixin {
    @Shadow
    @Final
    private String directory;
    @Inject(
            method = "build(Ljava/util/Map;)Ljava/util/Map;",
            at = @At("HEAD")
    )
    private <T> void beforeBuild(Map<ResourceLocation, List<TagLoader.EntryWithSource>> map,
                                 CallbackInfoReturnable<Map<ResourceLocation, Collection<T>>> cir) {

        if (!Registries.tagsDirPath(Registries.ITEM).equals(directory)) return;
        for(Map.Entry<TagKey<Item>, Item> itemList : ESDPlatform.TAG_INJECT_ITEM_LIST){
            ResourceLocation target = itemList.getKey().location();
            List<TagLoader.EntryWithSource> entries = map.get(target);
            if (entries != null) {
                Item item = itemList.getValue();
                ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
                EternalStarlightDelight.LOGGER.info("target:{},item:{}", target, item);
                if (item == Items.AIR) continue; // require=false

                // EntryWithSource の追加（TagLoader が自動で Holder に変換する）
                TagEntry entry = TagEntry.element(itemId); // JSON 相当のアイテム指定
                TagLoader.EntryWithSource ews = new TagLoader.EntryWithSource(entry, EternalStarlightDelight.of(target.getPath()+".to."+itemId.getPath()).toString());
                entries.add(ews);
            }
        }
        for(Map.Entry<TagKey<Item>, TagKey<Item>> itemList : ESDPlatform.TAG_INJECT_TAG_LIST){
            ResourceLocation target = itemList.getKey().location();
            List<TagLoader.EntryWithSource> entries = map.get(target);
            if (entries != null) {
                TagKey<Item> injectedTag = itemList.getValue();
                TagEntry entry = TagEntry.tag(injectedTag.location());
                EternalStarlightDelight.LOGGER.info("target:{},tag:{}", target, injectedTag);
                TagLoader.EntryWithSource ews = new TagLoader.EntryWithSource(entry, EternalStarlightDelight.of(target.getPath()+".to."+injectedTag.location().getPath()).toString());
                entries.add(ews);
            }
        }
    }
}
