package com.minor2cch.eternalstarlightdelight.neoforge.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverCookingPotInterface;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;

@Mixin(CookingPotBlockEntity.class)
public class CookingPotBlockEntityMixinNeoForge {
    @Shadow
    private int cookTime;
    @Final
    @Shadow
    private ItemStackHandler inventory;
    @Inject(at = @At("HEAD"), method = "processCooking")
    private void injectSkilletBlockEntityCooking(RecipeHolder<CookingPotRecipe> recipe, CookingPotBlockEntity cookingPot, CallbackInfoReturnable<Boolean> cir){
        if(this instanceof DeepSilverCookingPotInterface potInterface){
            Level level = potInterface.getLevel();
            ItemStack resultStack = recipe.value().assemble(new RecipeWrapper(this.inventory), level.registryAccess());
            if(ESDUtils.isESItem(resultStack)){
                cookTime++;
            }
        }


    }
}
