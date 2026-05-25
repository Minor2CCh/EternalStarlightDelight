package com.minor2cch.eternalstarlightdelight.client;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDFoods;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.List;

public final class EternalStarlightDelightClient {
    private EternalStarlightDelightClient() {}
    public static final List<ESDPlatform.QuadConsumer<ItemStack, Item.TooltipContext, TooltipFlag, List<Component>>> TOOLTIP_FUNCTIONS = new ArrayList<>();

    public static void init() {
        TOOLTIP_FUNCTIONS.add((stack, tooltipContext, tooltipFlag, lines) -> {
            if(ESDConfigLoader.getConfig().getEatableMushroomColonies() && stack.is(ModTags.Items.MUSHROOM_COLONIES) && ESAccessoryUtil.getActiveAccessoriesOnArmors(Minecraft.getInstance().player).contains(ESItems.FUNGUS_AMULET.get()) && stack.is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET)){

                List<FoodProperties.PossibleEffect> effectList = ESDFoods.FUNGUS_COLONY.get().effects();

                if (!effectList.isEmpty()) {
                    lines.add(Component.translatable("tooltip.eternal_starlight_delight.accessory_consumable_colony").withStyle(ChatFormatting.GRAY));
                    for (FoodProperties.PossibleEffect possibleEffect : effectList) {
                        MobEffectInstance instance = possibleEffect.effect();
                        MutableComponent mutableComponent = Component.translatable(instance.getDescriptionId());
                        MobEffect effect = instance.getEffect().value();
                        effect.createModifiers(instance.getAmplifier(), (attributeHolder, attributeModifier) -> {
                        });

                        if (instance.getAmplifier() > 0) {
                            mutableComponent = Component.translatable("potion.withAmplifier", mutableComponent, Component.translatable("potion.potency." + instance.getAmplifier()));
                        }

                        if (instance.getDuration() > 20) {
                            mutableComponent = Component.translatable("potion.withDuration", mutableComponent, MobEffectUtil.formatDuration(instance, 1.0F, tooltipContext.tickRate()));
                        }

                        lines.add(mutableComponent.withStyle(effect.getCategory().getTooltipFormatting()));

                    }
                    ESDUtils.getExtraColonyEffect(stack).ifPresent((effect) -> {
                        MobEffectInstance instance = effect.effect();
                        MutableComponent mutableComponent = Component.translatable(instance.getDescriptionId());
                        MobEffect effect2 = instance.getEffect().value();
                        effect2.createModifiers(instance.getAmplifier(), (attributeHolder, attributeModifier) -> {
                        });

                        if (instance.getAmplifier() > 0) {
                            mutableComponent = Component.translatable("potion.withAmplifier", mutableComponent, Component.translatable("potion.potency." + instance.getAmplifier()));
                        }

                        if (instance.getDuration() > 20) {
                            mutableComponent = Component.translatable("potion.withDuration", mutableComponent, MobEffectUtil.formatDuration(instance, 1.0F, tooltipContext.tickRate()));
                        }

                        lines.add(mutableComponent.withStyle(effect2.getCategory().getTooltipFormatting()));
                    });
                }
            }
            if (stack.getItem() == ESItems.SHADOW_SNAIL_PIE.get()) {
                if(ESDConfigLoader.getConfig().getPlaceableShadowSnailPie()){
                    lines.add(Configuration.ENABLE_PUMPKIN_PIE_SNEAK_TO_PLACE.get() ? TextUtils.PLACEABLE_SNEAKING : TextUtils.PLACEABLE);
                }
            }
            if(stack.getItem() == ESDItems.DEEPSILVER_SKILLET.get()
                    || stack.getItem() == ESDItems.DEEPSILVER_COOKING_POT.get()
                    || stack.getItem() == ESDItems.STARLIGHT_STOVE.get()){

                lines.add(Component.translatable(stack.getDescriptionId()+".desc"));
            }else if(stack.getItem() == ESDItems.BLOSSOM_OF_STARS_CRATE.get()){
                lines.add(Component.translatable(stack.getDescriptionId()+".desc").withColor(0x5187c4).withStyle(ChatFormatting.ITALIC));
            }
            if(ESDUtils.isFreshFood(stack)){
                lines.add(Component.translatable("tooltip.eternal_starlight_delight.is_fresh").withColor(0xFF7D3D));
            }
        });
    }
}
