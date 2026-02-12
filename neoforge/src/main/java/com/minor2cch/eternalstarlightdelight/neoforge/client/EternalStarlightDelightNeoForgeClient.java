package com.minor2cch.eternalstarlightdelight.neoforge.client;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.client.EternalStarlightDelightClient;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.client.renderer.*;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

@EventBusSubscriber(modid = EternalStarlightDelight.MOD_ID, value = Dist.CLIENT)
public class EternalStarlightDelightNeoForgeClient {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        EternalStarlightDelightClient.init();

        event.enqueueWork(() -> ItemProperties.register(ESDItems.DEEPSILVER_SKILLET.get(), ResourceLocation.withDefaultNamespace("cooking"),
                (stack, world, entity, s) -> stack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT, ItemStackWrapper.EMPTY).getStack().isEmpty() ? 0 : 1)
        );
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions()
        {
            final BlockEntityWithoutLevelRenderer renderer = new SkilletItemRenderer();

            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        }, ESDItems.DEEPSILVER_SKILLET.get());
    }
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ESDBlockEntityTypes.DEEPSILVER_SKILLET.get(), SkilletRenderer::new);
        event.registerBlockEntityRenderer(ESDBlockEntityTypes.STARLIGHT_STOVE.get(), StoveRenderer::new);
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        EternalStarlightDelightClient.TOOLTIP_FUNCTIONS.forEach((consumer) ->
                consumer.accept(event.getItemStack(), event.getContext(), event.getFlags(), event.getToolTip()));
    }
}
