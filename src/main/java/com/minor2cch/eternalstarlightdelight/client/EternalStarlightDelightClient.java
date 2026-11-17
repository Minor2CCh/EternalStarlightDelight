package com.minor2cch.eternalstarlightdelight.client;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDFoods;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.util.ESDTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.FlowerBlock;
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
                        extraEffectTooltip(possibleEffect.effect(), tooltipContext,  lines);
                    }
                    ESDUtils.getExtraColonyEffect(stack).ifPresent((effect) -> extraEffectTooltip(effect.effect(), tooltipContext,  lines));
                }
            }
            if(!tooltipFlag.isCreative() && ESAccessoryUtil.getActiveAccessoriesOnArmors(Minecraft.getInstance().player).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get())){
                if (stack.getItem() instanceof SuspiciousStewItem) {
                    SuspiciousStewEffects stewEffects = stack.getOrDefault(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY);

                        for (SuspiciousStewEffects.Entry stewEffect : stewEffects.effects()) {
                            MobEffectInstance instance = stewEffect.createEffectInstance();
                            extraEffectTooltip(instance, tooltipContext,  lines);
                        }
                }

            }
            if(ESAccessoryUtil.getActiveAccessoriesOnArmors(Minecraft.getInstance().player).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get())){
                if (stack.get(DataComponents.FOOD) == null && stack.is(ESDTags.Items.CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS) && !stack.is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET)) {
                    if((stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof FlowerBlock flowerBlock)){
                        lines.add(Component.translatable("tooltip.eternal_starlight_delight.accessory_consumable_flower").withStyle(ChatFormatting.GRAY));
                        for(SuspiciousStewEffects.Entry stewEffect : flowerBlock.getSuspiciousEffects().effects()){
                            MobEffectInstance instance = stewEffect.createEffectInstance();
                            extraEffectTooltip(instance, tooltipContext,  lines);

                        }
                        if(stack.is(ESDTags.Items.EXTRA_STARLIGHT_BLESSING_FLOWERS)){
                            MobEffectInstance instance = ESDUtils.getExtraStarlightBlessingEffect();
                            extraEffectTooltip(instance, tooltipContext,  lines);
                        }
                    }
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
    public static void extraEffectTooltip(MobEffectInstance instance, Item.TooltipContext tooltipContext, List<Component> lines){
        MobEffect effect = instance.getEffect().value();
        effect.createModifiers(instance.getAmplifier(), (attributeHolder, attributeModifier) -> {
        });
        MutableComponent mutableComponent = Component.translatable(instance.getDescriptionId());
        if (instance.getAmplifier() > 0) {
            mutableComponent = Component.translatable("potion.withAmplifier", mutableComponent, Component.translatable("potion.potency." + instance.getAmplifier()));
        }
        if (instance.getDuration() > 20) {
            mutableComponent = Component.translatable("potion.withDuration", mutableComponent, MobEffectUtil.formatDuration(instance, 1.0F, tooltipContext.tickRate()));
        }
        lines.add(mutableComponent.withStyle(effect.getCategory().getTooltipFormatting()));
    }
}
