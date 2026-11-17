package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.registry.ESFoods;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import com.minor2cch.eternalstarlightdelight.item.ESDDrinkableItem;
import com.minor2cch.eternalstarlightdelight.registry.ESDFoods;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import com.minor2cch.eternalstarlightdelight.util.ESDTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Objects;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private void extraEatFungusAndFlower(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        Player player = (Player) (Object) this;
        if(ESDConfigLoader.getConfig().getEatableMushroomColonies() && ESAccessoryUtil.getActiveAccessoriesOnArmors(player).contains(ESItems.FUNGUS_AMULET.get()) && food.is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET) && food.is(ModTags.Items.MUSHROOM_COLONIES) && Objects.equals(foodProperties, ESFoods.FUNGUS.get())){
            ESDUtils.getExtraColonyEffect(food).ifPresent((effect) -> player.addEffect(effect.effect()));
            cir.setReturnValue(player.eat(level, food, ESDFoods.FUNGUS_COLONY.get()));
            cir.cancel();
        }else if(ESAccessoryUtil.getActiveAccessoriesOnArmors(player).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get())){
            if (food.is(ESDTags.Items.CONSUMABLE_WHEN_WEARING_STARLIGHT_FLOWER_HEADDRESS) && !food.is(ESTags.Items.CONSUMABLE_WHEN_WEARING_FUNGUS_AMULET) && Objects.equals(foodProperties, ESDFoods.SMALL_FLOWERS.get())){
                if((food.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof FlowerBlock flowerBlock)){
                    for(SuspiciousStewEffects.Entry effect : flowerBlock.getSuspiciousEffects().effects()){
                        player.addEffect(effect.createEffectInstance());
                        if(food.is(ESDTags.Items.EXTRA_STARLIGHT_BLESSING_FLOWERS)){
                            player.addEffect(ESDUtils.getExtraStarlightBlessingEffect());
                        }
                    }
                }
            }

        }

    }
    @Redirect(
            method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
            )
    )
    private void redirectBurpSound(
            Level level,
            Player player,
            double x, double y, double z,
            SoundEvent sound,
            SoundSource source,
            float volume,
            float pitch,
            Level level0,
            ItemStack stack,
            FoodProperties food
    ) {
        if (sound == SoundEvents.PLAYER_BURP) {
            if(stack.getItem() instanceof ESDDrinkableItem){
                return;
            }
        }

        level.playSound(player, x, y, z, sound, source, volume, pitch);
    }
}
