package com.minor2cch.eternalstarlightdelight.item;

import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;


public class DeepSilverSkilletItem extends SkilletItem {
    public DeepSilverSkilletItem(Block block, Properties properties) {
        super(block, properties.durability(ESItemTiers.DEEPSILVER.getUses()));
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return ESItemTiers.DEEPSILVER.getRepairIngredient().test(repair);
    }
    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if(entity instanceof ServerPlayer){
            ESDUtils.completeAdvancement((ServerPlayer)entity, ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "main/use_skillet"));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
