package com.minor2cch.eternalstarlightdelight;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import com.minor2cch.eternalstarlightdelight.registry.ESDDataComponents;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.Objects;

public final class ESDUtils {
    private ESDUtils() {}
    public static boolean isESItem(ItemStack stack){
        ResourceLocation rl = ResourceLocation.tryParse(stack.getItemHolder().getRegisteredName());
        if(rl == null){
            return false;
        }
        String name = rl.getNamespace();
        return Objects.equals(name, EternalStarlight.ID) || Objects.equals(name, EternalStarlightDelight.MOD_ID);
    }
    @SuppressWarnings("all")
    public static boolean completeAdvancement(ServerPlayer player, ResourceLocation advancementId){
        if(player.getServer() != null){
            AdvancementHolder advancement = player.getServer().getAdvancements().get(advancementId);
            if (advancement != null) {
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                if (!progress.isDone()) {
                    for (String criterion : progress.getRemainingCriteria()) {
                        player.getAdvancements().award(advancement, criterion);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean canSmeltTool(ItemStack stack){
        if(stack == null || stack.isEmpty()){
            return false;
        }
        return ESAccessoryUtil.getAccessories(stack).contains(ESDItems.THERMAL_SPRINGBLADE_STRAP.get());
    }
    public static boolean canSmeltStack(ServerLevel level, ItemStack stack){
        if(stack.isEmpty()){
            return false;
        }
        RecipeHolder<?> recipeEntry;
        final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> matchGetter = RecipeManager.createCheck(RecipeType.SMELTING);
        recipeEntry = matchGetter.getRecipeFor(new SingleRecipeInput(stack), level).orElse(null);
        return recipeEntry != null;
    }
    public static ItemStack smeltStack(ServerLevel level, ItemStack stack){
        RecipeHolder<?> recipeEntry;
        final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> matchGetter = RecipeManager.createCheck(RecipeType.SMELTING);
        recipeEntry = matchGetter.getRecipeFor(new SingleRecipeInput(stack), level).orElse(null);
        if(recipeEntry == null){
            return stack;
        }
        ItemStack copyStack = recipeEntry.value().getResultItem(level.registryAccess()).copy();
        copyStack.setCount(stack.getCount());
        return copyStack;
    }
    public static void setMakeFresh(ItemStack stack){
        FoodProperties properties = stack.getItem().components().get(DataComponents.FOOD);
        if(properties != null){
            float saturationModifier = properties.nutrition() <= 0 ? 0 : properties.saturation() / properties.nutrition();
            int extraNutrition;
            if(properties.nutrition() < 1){
                extraNutrition = 0;
            }else if(properties.nutrition() < 5){
                extraNutrition = 1;
            }else if(properties.nutrition() < 11){
                extraNutrition = 2;
            }else{
                extraNutrition = 3;
            }
            FoodProperties foodProperties = new FoodProperties(properties.nutrition()+extraNutrition, properties.saturation()+extraNutrition*saturationModifier, properties.canAlwaysEat(), properties.eatSeconds(), properties.usingConvertsTo(), properties.effects());
            stack.set(DataComponents.FOOD, foodProperties);
            stack.set(ESDDataComponents.IS_FRESH.get(), true);
        }

    }
    public static boolean canMakeFreshTool(ItemStack stack){
        if(stack == null || stack.isEmpty()){
            return false;
        }
        return ESAccessoryUtil.getAccessories(stack).contains(ESDItems.STARFIRE_FLOWER_STRAP.get());
    }
    public static boolean isFreshFood(ItemStack stack){
        if(stack == null || stack.isEmpty()){
            return false;
        }
        Boolean bl = stack.get(ESDDataComponents.IS_FRESH.get());
        if(bl == null){
            return false;
        }
        return bl;
    }
}
