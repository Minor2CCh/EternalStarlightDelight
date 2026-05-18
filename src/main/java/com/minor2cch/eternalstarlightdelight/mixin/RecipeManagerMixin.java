package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.google.common.collect.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Shadow
    private Map<ResourceLocation, RecipeHolder<?>> byName;
    @Shadow
    private Multimap<RecipeType<?>, RecipeHolder<?>> byType;
    /**
     * apply() は全てのレシピをロードして internalMap に詰める処理。
     * ここでRETURN時にフックを入れることで、ロード後に削除が可能。
     */
    @Inject(method = "apply*", at = @At("RETURN"))
    private void onApply(Map<ResourceLocation, ?> map, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        if(ESDPlatform.REMOVE_RECIPE_LIST.isEmpty()){ //何も消さない場合はスキップ
            return;
        }
        Map<ResourceLocation, RecipeHolder<?>> newByName = new HashMap<>();
        Multimap<RecipeType<?>, RecipeHolder<?>> newByType = ArrayListMultimap.create();
        byName.forEach((ResourceLocation resourceLocation, RecipeHolder<?> holder) -> {
            if(!ESDPlatform.REMOVE_RECIPE_LIST.contains(resourceLocation)){
                newByName.put(resourceLocation, holder);
            }/*else{
                System.out.println("Delete_ResourceLocation:"+resourceLocation);

            }*/
        });
        byType.forEach((recipe, holder) -> {
            if(newByName.containsValue(holder)){
                newByType.put(recipe, holder);
            }
        });
        byType = newByType;
        byName = newByName;
    }
}
