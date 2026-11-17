package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.llamalad7.mixinextras.sugar.Local;
import com.minor2cch.eternalstarlightdelight.registry.ESDFoods;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.util.ESDTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
@Mixin(Item.class)
@SuppressWarnings("deprecation")
public abstract class ItemMixin {
    @ModifyVariable(method = "use", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private FoodProperties modifyFoodProperties(FoodProperties value, @Local(argsOnly = true) Player player) {
        Item item = ((Item) (Object) this);
        if (value == null) {
            if (ESAccessoryUtil.getActiveAccessoriesOnArmors(player).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get())){
                if (item.builtInRegistryHolder().is(ESDTags.Items.CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS) && !item.builtInRegistryHolder().is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET)){
                    return ESDFoods.SMALL_FLOWERS.get();
                }
            }
        }
        return value;
    }

    @ModifyVariable(method = {"finishUsingItem", "getUseDuration"}, at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private FoodProperties modifyFoodProperties(FoodProperties value, @Local(argsOnly = true) LivingEntity entity) {
        Item item = ((Item) (Object) this);
        if (value == null) {
            if (ESAccessoryUtil.getActiveAccessoriesOnArmors(entity).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get()) && !item.builtInRegistryHolder().is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET)){
                if (item.builtInRegistryHolder().is(ESDTags.Items.CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS)){
                    return ESDFoods.SMALL_FLOWERS.get();
                }
            }
        }
        return value;
    }
}
