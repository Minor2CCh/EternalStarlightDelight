package com.minor2cch.eternalstarlightdelight.compat;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.google.common.collect.Lists;
import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.List;
import java.util.Optional;

public class ESDRecipes {
    private final RecipeManager recipeManager;

    public ESDRecipes() {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;

        if (level != null) {
            this.recipeManager = level.getRecipeManager();
        } else {
            throw new NullPointerException("minecraft world must not be null.");
        }
    }

    public List<RecipeHolder<CraftingRecipe>> getSpecialWheatDoughRecipe() {
        Optional<RecipeHolder<?>> specialRecipe = recipeManager.byKey(EternalStarlightDelight.of("crinoa_dough_from_water"));
        List<RecipeHolder<CraftingRecipe>> recipes = Lists.newArrayList();

        specialRecipe.ifPresent((recipe) -> {
            NonNullList<Ingredient> inputs = NonNullList.of(
                    Ingredient.EMPTY,
                    Ingredient.of(ESItems.CRINOA.get()),
                    Ingredient.of(ESDPlatform.INSTANCE.getWaterBucketTag())
            );
            ItemStack output = new ItemStack(ESDItems.CRINOA_DOUGH.get());

            ResourceLocation id = recipe.id();
            CraftingRecipe newRecipe = new ShapelessRecipe("esd_crinoa_dough", CraftingBookCategory.MISC, output, inputs);
            recipes.add(new RecipeHolder<>(id, newRecipe));
        });

        return recipes;
    }

}
