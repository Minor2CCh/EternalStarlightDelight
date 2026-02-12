package com.minor2cch.eternalstarlightdelight.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import vectorwing.farmersdelight.common.item.SkilletItem;

@Mixin(SkilletItem.class)
public interface SkilletItemInvoker {
    @Invoker("isPlayerNearHeatSource")
    static boolean invokePlayerNeatHeatSourceCheck(Player player, LevelReader level) {
        throw new AssertionError();
    }
}
