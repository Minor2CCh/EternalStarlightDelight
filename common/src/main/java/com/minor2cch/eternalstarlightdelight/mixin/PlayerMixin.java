package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.item.ESDDrinkableItem;
import com.minor2cch.eternalstarlightdelight.item.KnifeOfHungerItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getWeaponItem()Lnet/minecraft/world/item/ItemStack;"))
    private void beforeAttack(Entity entity, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offhand = player.getOffhandItem();
        if (ItemStack.isSameItem(mainHand, offhand) && mainHand.getItem() instanceof KnifeOfHungerItem) {
            entity.invulnerableTime = 0;
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
