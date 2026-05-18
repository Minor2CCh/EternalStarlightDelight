package com.minor2cch.eternalstarlightdelight.crafting;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.registry.ESDRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CrinoaDoughRecipe extends CustomRecipe {
    public CrinoaDoughRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput container, @NotNull Level level) {
        ItemStack crinoaStack = ItemStack.EMPTY;
        ItemStack waterStack = ItemStack.EMPTY;

        for (int index = 0; index < container.size(); ++index) {
            ItemStack selectedStack = container.getItem(index);
            if (!selectedStack.isEmpty()) {
                if (selectedStack.is(ESItems.CRINOA.get())) {
                    if (!crinoaStack.isEmpty()) return false;
                    crinoaStack = selectedStack;
                } else {
                    if (!selectedStack.is(ESDPlatform.INSTANCE.getWaterBucketTag())) {
                        return false;
                    }
                    waterStack = selectedStack;
                }
            }
        }

        return !crinoaStack.isEmpty() && !waterStack.isEmpty();
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput container, HolderLookup.@NotNull Provider registryAccess) {
        return new ItemStack(ESDItems.CRINOA_DOUGH.get());
    }

    @Override
    public @NotNull NonNullList<ItemStack> getRemainingItems(CraftingInput container) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(container.size(), ItemStack.EMPTY);

        for (int index = 0; index < remainders.size(); ++index) {
            ItemStack selectedStack = container.getItem(index);
            if (selectedStack.is(ESDPlatform.INSTANCE.getWaterBucketTag())) {
                remainders.set(index, selectedStack.copy());
            }
        }

        return remainders;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ESDRecipeSerializers.CRINOA_DOUGH.get();
    }
}