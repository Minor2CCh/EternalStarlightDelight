package com.minor2cch.eternalstarlightdelight.item;

import cn.leolezury.eternalstarlight.common.data.ESDamageTypes;
import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import cn.leolezury.eternalstarlight.common.registry.ESDataComponents;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

public class KnifeOfHungerItem extends KnifeItem {
    public static final ItemAttributeModifiers DEFAULT_ATTRIBUTE = KnifeItem.createAttributes(ESItemTiers.TOOTH_OF_HUNGER, 0, -2.0f);
    public static final ItemAttributeModifiers BONUS_ATTRIBUTE = KnifeItem.createAttributes(ESItemTiers.TOOTH_OF_HUNGER, 1, -1.5f);
    public static final ItemAttributeModifiers PENALTY_ATTRIBUTE = KnifeItem.createAttributes(ESItemTiers.TOOTH_OF_HUNGER, -1, -2.5f);
    public KnifeOfHungerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    @Override
    public void postHurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity attacker) {
        ESItems.DAGGER_OF_HUNGER.get().postHurtEnemy(stack, entity, attacker);
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        return ESItems.DAGGER_OF_HUNGER.get().use(level, player, hand);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, Entity entity, int slot, boolean bl) {
        if (entity.tickCount % 1200 == 0) {
            float hungerLevel = Mth.clamp(stack.getOrDefault(ESDataComponents.HUNGER_LEVEL.get(), 0f), -1, 1);
            float newHungerLevel = Math.max(-1, hungerLevel - 0.00005f * 1200);
            stack.applyComponentsAndValidate(DataComponentPatch.builder().set(ESDataComponents.HUNGER_LEVEL.get(), newHungerLevel).build());
            if (hungerLevel == -1) {
                entity.hurt(ESDamageTypes.getDamageSource(level, ESDamageTypes.DAGGER_OF_HUNGER), 3);
                newHungerLevel = Math.min(1, newHungerLevel + 0.05f);
                stack.applyComponentsAndValidate(DataComponentPatch.builder().set(ESDataComponents.HUNGER_LEVEL.get(), newHungerLevel).build());
            }
        }
        if (entity.tickCount % 600 == 0) {
            float hungerLevel = Mth.clamp(stack.getOrDefault(ESDataComponents.HUNGER_LEVEL.get(), 0f), -1, 1);
            int state = Math.min(2, (int) ((hungerLevel + 1f) * 1.5f));
            ItemAttributeModifiers modifiers = switch (state) {
                case 0 -> PENALTY_ATTRIBUTE;
                case 2 -> BONUS_ATTRIBUTE;
                default -> DEFAULT_ATTRIBUTE;
            };
            stack.applyComponentsAndValidate(DataComponentPatch.builder().set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers).build());
        }
        super.inventoryTick(stack, level, entity, slot, bl);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        ESItems.DAGGER_OF_HUNGER.get().appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return ESItems.DAGGER_OF_HUNGER.get().isBarVisible(stack);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return ESItems.DAGGER_OF_HUNGER.get().getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ESItems.DAGGER_OF_HUNGER.get().getBarColor(stack);
    }
}
