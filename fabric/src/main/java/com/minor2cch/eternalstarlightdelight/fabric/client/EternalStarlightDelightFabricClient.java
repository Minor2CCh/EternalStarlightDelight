package com.minor2cch.eternalstarlightdelight.fabric.client;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import cn.leolezury.eternalstarlight.common.registry.ESDataComponents;
import com.minor2cch.eternalstarlightdelight.client.EternalStarlightDelightClient;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

@Environment(EnvType.CLIENT)
public final class EternalStarlightDelightFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EternalStarlightDelightClient.init();
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ESDBlocks.BOULDERSHROOM_COLONY.get(),
                ESDBlocks.GLOWING_MUSHROOM_COLONY.get(),
                ESDBlocks.MARIMOLD_COLONY.get(),
                ESDBlocks.DEEPSILVER_COOKING_POT.get());

        ItemTooltipCallback.EVENT.register((ItemStack stack, Item.TooltipContext context, TooltipFlag tooltipType, java.util.List<Component> lines) ->
                EternalStarlightDelightClient.TOOLTIP_FUNCTIONS.forEach((consumer) ->
                        consumer.accept(stack, context, tooltipType, lines)));
        BuiltinItemRendererRegistry.INSTANCE.register(ESDItems.DEEPSILVER_SKILLET.get(), new SkilletItemRenderer());
        ItemProperties.register(ESDItems.DEEPSILVER_SKILLET.get(), ResourceLocation.withDefaultNamespace("cooking"),
                (stack, world, entity, s) -> stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY).getStack().isEmpty() ? 0 : 1);
        BlockEntityRenderers.register(ESDBlockEntityTypes.DEEPSILVER_SKILLET.get(), vectorwing.farmersdelight.client.renderer.SkilletRenderer::new);
        ItemProperties.register(ESDItems.KNIFE_OF_HUNGER.get(), EternalStarlight.id("hunger_state"), (stack, level, entity, i) -> Math.min(2f, (stack.getOrDefault(ESDataComponents.HUNGER_LEVEL.get(), 0f) + 1f) * 1.5f) / 2f);
        BlockEntityRenderers.register(ESDBlockEntityTypes.STARLIGHT_STOVE.get(), StoveRenderer::new);
    }
}
