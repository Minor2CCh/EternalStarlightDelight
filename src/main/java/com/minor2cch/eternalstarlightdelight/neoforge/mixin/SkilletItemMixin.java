package com.minor2cch.eternalstarlightdelight.neoforge.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.item.DeepSilverSkilletItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(SkilletItem.class)
public class SkilletItemMixin {
    @Redirect(
            method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;set(Ljava/util/function/Supplier;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    @SuppressWarnings("unchecked")
    private <T> @Nullable T esItemFastCooking(
            ItemStack instance, Supplier<? extends DataComponentType<? super T>> dataComponentType, @Nullable T object
    ) {
        if(((Object)this) instanceof DeepSilverSkilletItem){
            if(Objects.equals(dataComponentType, ModDataComponents.COOKING_TIME_LENGTH)){
                if(object instanceof Integer cookingTime){
                    ItemStack cookingStack = instance.getOrDefault(ModDataComponents.SKILLET_INGREDIENT, new ItemStackWrapper(ItemStack.EMPTY)).getStack();
                    if(ESDUtils.isESItem(cookingStack)){
                        return (T) instance.set(ModDataComponents.COOKING_TIME_LENGTH, cookingTime / 2);
                    }
                }
            }

        }
        return instance.set(dataComponentType, object);
    }
}
