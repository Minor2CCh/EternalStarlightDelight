package com.minor2cch.eternalstarlightdelight.fabric.compat;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultShapelessDisplay;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import vectorwing.farmersdelight.integration.rei.REICategoryIdentifiers;

import java.util.Optional;

public class ClientREIPlugin implements REIClientPlugin {
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        RecipeHolder<ShapelessRecipe> recipeHolder = getSpecialCrinoaDoughRecipe(registry.getRecipeManager());
        if (recipeHolder != null) {
            registry.add(new DefaultShapelessDisplay(recipeHolder));
        }

    }
    @Override
    public void registerCategories(CategoryRegistry registry) {

        registry.addWorkstations(REICategoryIdentifiers.COOKING, EntryStacks.of(ESDItems.DEEPSILVER_COOKING_POT.get()));
        registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(ESDItems.DEEPSILVER_SKILLET.get()));
        registry.addWorkstations(BuiltinPlugin.CAMPFIRE, EntryStacks.of(ESDItems.STARLIGHT_STOVE.get()));
    }
    public RecipeHolder<ShapelessRecipe> getSpecialCrinoaDoughRecipe(RecipeManager recipeManager) {
        Optional<RecipeHolder<?>> specialRecipe = recipeManager.byKey(ResourceLocation.fromNamespaceAndPath(EternalStarlightDelight.MOD_ID, "crinoa_dough_from_water"));

        return specialRecipe.map((recipe) -> {
            NonNullList<Ingredient> inputs = NonNullList.of(
                    Ingredient.EMPTY,
                    Ingredient.of(ESItems.CRINOA.get()),
                    Ingredient.of(Items.WATER_BUCKET)
            );
            ItemStack output = new ItemStack(ESDItems.CRINOA_DOUGH.get());

            ResourceLocation id = recipe.id();
            ShapelessRecipe newRecipe = new ShapelessRecipe("esd_dough", CraftingBookCategory.MISC, output, inputs);
            return new RecipeHolder<>(id, newRecipe);
        }).orElse(null);
    }
}
