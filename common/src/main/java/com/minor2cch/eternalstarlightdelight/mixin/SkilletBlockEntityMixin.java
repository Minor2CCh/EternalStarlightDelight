package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverSkilletBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;


@Mixin(SkilletBlockEntity.class)
public class SkilletBlockEntityMixin {
    @Shadow
    private int cookingTime;
    @Inject(at = @At("HEAD"), method = "cookAndOutputItems")
    private void injectSkilletBlockEntityCooking(ItemStack cookingStack, Level level, CallbackInfo ci){
        if((Object)(this) instanceof DeepSilverSkilletBlockEntity){
            if(ESDUtils.isESItem(cookingStack)){
                cookingTime++;
            }
        }
    }
}
