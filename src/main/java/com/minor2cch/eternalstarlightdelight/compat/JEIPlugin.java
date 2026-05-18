package com.minor2cch.eternalstarlightdelight.compat;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.integration.jei.FDRecipeTypes;

@JeiPlugin
@MethodsReturnNonnullByDefault
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(EternalStarlightDelight.MOD_ID, "jei_plugin");

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ESDItems.DEEPSILVER_COOKING_POT.get()), FDRecipeTypes.COOKING);
        registration.addRecipeCatalyst(new ItemStack(ESDItems.STARLIGHT_STOVE.get()), RecipeTypes.CAMPFIRE_COOKING);
        registration.addRecipeCatalyst(new ItemStack(ESDItems.DEEPSILVER_SKILLET.get()), RecipeTypes.CAMPFIRE_COOKING);
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ESDRecipes esdRecipes = new ESDRecipes();
        registration.addRecipes(RecipeTypes.CRAFTING, esdRecipes.getSpecialWheatDoughRecipe());

    }


    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
