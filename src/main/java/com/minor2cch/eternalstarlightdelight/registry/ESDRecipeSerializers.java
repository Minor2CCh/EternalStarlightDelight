package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.crafting.CrinoaDoughRecipe;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ESDRecipeSerializers {
    private ESDRecipeSerializers() {}
    public static final Supplier<SimpleCraftingRecipeSerializer<?>> CRINOA_DOUGH = ESDPlatform.INSTANCE.recipeSerializerRegister(
            "crinoa_dough", () -> new SimpleCraftingRecipeSerializer<>(CrinoaDoughRecipe::new)
    );
    public static void init(){

    }
}
