package com.minor2cch.eternalstarlightdelight.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.block.entity.StarlightStoveBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;

@Mixin(AbstractStoveBlockEntity.class)
public abstract class AbstractStoveBlockEntityMixin {

    @WrapOperation(
            method = "placeFood",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/AbstractCookingRecipe;getCookingTime()I"))
    private int wrapCampfireTime(AbstractCookingRecipe instance, Operation<Integer> original) {
        if(((AbstractStoveBlockEntity)(Object)this) instanceof StarlightStoveBlockEntity be){
            Level level = be.getLevel();
            if(level != null){
                ItemStack resultStack = instance.getResultItem(level.registryAccess());
                if(ESDUtils.isESItem(resultStack)){
                    return Math.max(original.call(instance) / 2, 1);
                }
            }
        }
        return original.call(instance);
    }
}
