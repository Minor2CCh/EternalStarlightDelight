package com.minor2cch.eternalstarlightdelight;

import cn.leolezury.eternalstarlight.common.EternalStarlight;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public final class ESDUtils {
    private ESDUtils() {}
    public static boolean isESItem(ItemStack stack){
        ResourceLocation rl = ResourceLocation.tryParse(stack.getItemHolder().getRegisteredName());
        if(rl == null){
            return false;
        }
        String name = rl.getNamespace();
        return Objects.equals(name, EternalStarlight.ID) || Objects.equals(name, EternalStarlightDelight.MOD_ID);
    }
    @SuppressWarnings("all")
    public static boolean completeAdvancement(ServerPlayer player, ResourceLocation advancementId){
        if(player.getServer() != null){
            AdvancementHolder advancement = player.getServer().getAdvancements().get(advancementId);
            if (advancement != null) {
                AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                if (!progress.isDone()) {
                    for (String criterion : progress.getRemainingCriteria()) {
                        player.getAdvancements().award(advancement, criterion);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
