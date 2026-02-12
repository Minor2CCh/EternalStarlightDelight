package com.minor2cch.eternalstarlightdelight.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractContainerMenu.class)
public interface AbstractContainerMenuInvoker {
    @Invoker("stillValid")
    static boolean invokeStillValid(ContainerLevelAccess containerLevelAccess, Player player, Block block) {
        throw new AssertionError();
    }

}
