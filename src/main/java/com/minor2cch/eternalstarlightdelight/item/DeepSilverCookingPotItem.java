package com.minor2cch.eternalstarlightdelight.item;

import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverCookingPotInterface;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.common.item.CookingPotItem;

import java.util.Optional;

public class DeepSilverCookingPotItem extends CookingPotItem {
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    public DeepSilverCookingPotItem(Block block, Properties properties) {
        super(block, properties);
    }
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getServingCount(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.min(1 + 12 * getServingCount(stack) / 64, 13);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BAR_COLOR;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        ItemStack mealStack = DeepSilverCookingPotInterface.getMealFromItem(stack);
        return Optional.of(new CookingPotTooltip.CookingPotTooltipComponent(mealStack));
    }

    private static int getServingCount(ItemStack stack) {
        ItemStack mealStack = DeepSilverCookingPotInterface.getMealFromItem(stack);
        return mealStack.getCount();
    }
}
