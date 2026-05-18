package com.minor2cch.eternalstarlightdelight.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

@Mixin(CookingPotMenu.class)
public class CookingPotMenuMixin {
    @Final
    @Shadow
    private ContainerLevelAccess canInteractWithCallable;
    @ModifyReturnValue(method = "stillValid", at = @At("RETURN"))
    private boolean stillValidExtend(boolean original, Player playerIn){
        if (!original) {
            return AbstractContainerMenuInvoker.invokeStillValid(canInteractWithCallable, playerIn, ESDBlocks.DEEPSILVER_COOKING_POT.get());
        }
        return true;

    }
}
