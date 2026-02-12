package com.minor2cch.eternalstarlightdelight.item;

import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.SkilletBlock;
import vectorwing.farmersdelight.common.item.SkilletItem;

import java.util.Optional;

public class DeepSilverSkilletItem extends SkilletItem {
    public DeepSilverSkilletItem(Block block, Properties properties) {
        super(block, properties.durability(ESItemTiers.DEEPSILVER.getUses()));
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ESDPlatform.INSTANCE.deepsilverSkilletUsing(level, player, hand);
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return ESItemTiers.DEEPSILVER.getRepairIngredient().test(repair);
    }
    public static int getSkilletCookingTime(Level level, ItemStack skilletStack, ItemStack cookingStack, int originalCookingTime) {
        Optional<Holder.Reference<Enchantment>> fireAspect = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(Enchantments.FIRE_ASPECT);
        if (fireAspect.isEmpty()) {
            return originalCookingTime / (ESDUtils.isESItem(cookingStack) ? 2 : 1);
        }
        int fireAspectLevel = fireAspect.map(enchantmentReference -> skilletStack.getEnchantments().getLevel(enchantmentReference)).orElse(0);
        return SkilletBlock.getSkilletCookingTime(originalCookingTime, fireAspectLevel) / (ESDUtils.isESItem(cookingStack) ? 2 : 1);
    }
    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if(entity instanceof ServerPlayer){
            ESDUtils.completeAdvancement((ServerPlayer)entity, ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "main/use_skillet"));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
