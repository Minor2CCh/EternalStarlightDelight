package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverSkilletBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.utility.ItemUtils;


@Mixin(SkilletBlockEntity.class)
public abstract class SkilletBlockEntityMixin {
    @Shadow
    private int cookingTime;

    @Shadow
    public abstract ItemStack getSkilletAsItem();

    @Inject(at = @At("HEAD"), method = "cookAndOutputItems")
    private void injectSkilletBlockEntityCooking(ItemStack cookingStack, Level level, CallbackInfo ci){
        if((Object)(this) instanceof DeepSilverSkilletBlockEntity){
            if(ESDUtils.isESItem(cookingStack)){
                cookingTime++;
            }
        }
    }
    @Redirect(
            method = "cookAndOutputItems(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lvectorwing/farmersdelight/common/utility/ItemUtils;spawnItemEntity(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;DDDDDD)V"
            )
    )
    private void extraCookingForSkillet(
            Level level, ItemStack stack, double x, double y, double z, double xMotion, double yMotion, double zMotion
    ) {
        if(ESDUtils.canMakeFreshTool(getSkilletAsItem())){
            ESDUtils.setMakeFresh(stack);
        }
        ItemUtils.spawnItemEntity(level, stack, x, y, z, xMotion, yMotion, zMotion);
    }
}
