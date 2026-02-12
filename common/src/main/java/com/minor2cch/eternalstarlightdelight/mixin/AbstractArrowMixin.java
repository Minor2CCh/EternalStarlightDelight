package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.entity.projectile.ThrownBoomerang;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    @Inject(at = @At("HEAD"), method = "onHitBlock")
    private void useCuttingBoard(BlockHitResult blockHitResult, CallbackInfo ci){
        if(ESDConfigLoader.getConfig().getBoomerangUsableKnife()){
            if(((Object)(this)) instanceof ThrownBoomerang boomerang){
                ItemStack stack = boomerang.getPickupItemStackOrigin();
                BlockPos pos = blockHitResult.getBlockPos();
                Level level = boomerang.level();
                BlockEntity be = level.getBlockEntity(pos);
                if(!level.isClientSide()){
                    if (be instanceof CuttingBoardBlockEntity cuttingBoardEntity) {
                        if (stack != null && !stack.isEmpty() && (stack.getDamageValue() < stack.getMaxDamage() - 1 || !stack.isDamageableItem())) {  //耐久値2以上
                            ItemStack boardStack = cuttingBoardEntity.getStoredItem().copy();
                            if(!boardStack.isEmpty()){
                                if(boomerang.getOwner() instanceof Player player){
                                    if (cuttingBoardEntity.processStoredItemUsingTool(stack, player)) {
                                        CuttingBoardBlock.spawnCuttingParticles(level, pos, boardStack, 5);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
