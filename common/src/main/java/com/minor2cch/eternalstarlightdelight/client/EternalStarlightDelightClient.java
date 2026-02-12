package com.minor2cch.eternalstarlightdelight.client;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public final class EternalStarlightDelightClient {
    private EternalStarlightDelightClient() {}
    public static final List<ESDPlatform.QuadConsumer<ItemStack, Item.TooltipContext, TooltipFlag, List<Component>>> TOOLTIP_FUNCTIONS = new ArrayList<>();

    public static void init() {
        TOOLTIP_FUNCTIONS.add((stack, tooltipContext, tooltipFlag, lines) -> {
            if(stack.getItem() == ESDItems.DEEPSILVER_SKILLET.get()
                    || stack.getItem() == ESDItems.DEEPSILVER_COOKING_POT.get()
                    || stack.getItem() == ESDItems.STARLIGHT_STOVE.get()){

                lines.add(Component.translatable(stack.getDescriptionId()+".desc"));
            }else if(stack.getItem() == ESDItems.BLOSSOM_OF_STARS_CRATE.get()){
                lines.add(Component.translatable(stack.getDescriptionId()+".desc").withColor(0x5187c4).withStyle(ChatFormatting.ITALIC));
            }
        });
    }
}
