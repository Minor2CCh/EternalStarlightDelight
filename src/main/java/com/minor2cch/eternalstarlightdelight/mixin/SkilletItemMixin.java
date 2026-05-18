package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(SkilletItem.class)
public class SkilletItemMixin {
    @Unique
    private ItemStack skilletItem$Mixin;
    @Unique
    private Level level$Mixin;
    @Unique
    private LivingEntity entity$Mixin;
    @Inject(at = @At("HEAD"), method = "finishUsingItem")
    public void setVariables(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir){
        skilletItem$Mixin = stack;
        level$Mixin = level;
        entity$Mixin = entity;
    }
    @Redirect(
            method = "finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"
            )
    )
    private void extraCookingForSkillet(
            @SuppressWarnings("all") Optional<RecipeHolder<CampfireCookingRecipe>> instance, Consumer<RecipeHolder<CampfireCookingRecipe>> action
    ) {
        if(ESDUtils.canMakeFreshTool(skilletItem$Mixin)){
            Player player = (Player) entity$Mixin;
            ItemStackWrapper storedStack = ESDPlatform.INSTANCE.getSkilletStackHandler(skilletItem$Mixin);
            if (!storedStack.getStack().isEmpty()) {
                ItemStack cookingStack = storedStack.getStack();
                instance.ifPresent((recipe) -> {
                    ItemStack resultStack = recipe.value().assemble(new SingleRecipeInput(cookingStack), level$Mixin.registryAccess()).copy();
                    ESDUtils.setMakeFresh(resultStack);
                    if (!player.getInventory().add(resultStack)) {
                        player.drop(resultStack, false);
                    }
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, skilletItem$Mixin);
                    }
                });
            }
        }else{
            instance.ifPresent(action);
        }
    }
}
