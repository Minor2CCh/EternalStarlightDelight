package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.registry.ModAdvancements;
import vectorwing.farmersdelight.common.utility.ItemUtils;

import java.util.List;
import java.util.Optional;

@Mixin(CuttingBoardBlockEntity.class)
public abstract class CuttingBoardBlockEntityMixin {
    @Shadow
    protected abstract Optional<RecipeHolder<CuttingBoardRecipe>> getMatchingRecipe(ItemStack toolStack, @Nullable Player player);
    @Inject(at = @At("HEAD"), method = "processStoredItemUsingTool", cancellable = true)
    private void smeltCutting(ItemStack toolStack, Player player, CallbackInfoReturnable<Boolean> cir){
        CuttingBoardBlockEntity be =  (CuttingBoardBlockEntity)(Object)this;
        Level level = be.getLevel();
        if (level == null) return;

        if (be.isItemCarvingBoard()) return;
        if(ESDUtils.canSmeltTool(toolStack)){
            Optional<RecipeHolder<CuttingBoardRecipe>> matchingRecipe = getMatchingRecipe(toolStack, player);

            matchingRecipe.ifPresent(recipe -> {
                List<ItemStack> results = recipe.value().rollResults(level.random, EnchantmentHelper.getItemEnchantmentLevel(be.getLevel().holderLookup(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), toolStack));
                for (ItemStack resultStack : results) {
                    Direction direction = be.getBlockState().getValue(CuttingBoardBlock.FACING).getCounterClockWise();
                    ItemStack copyStack = resultStack.copy();
                    if(!level.isClientSide){
                        if(ESDUtils.canSmeltStack((ServerLevel) level, copyStack)){
                            copyStack = ESDUtils.smeltStack((ServerLevel) level, copyStack);
                        }
                    }
                    ItemUtils.spawnItemEntity(level, copyStack,
                            be.getBlockPos().getX() + 0.5 + (direction.getStepX() * 0.2), be.getBlockPos().getY() + 0.2, be.getBlockPos().getZ() + 0.5 + (direction.getStepZ() * 0.2),
                            direction.getStepX() * 0.2F, 0.0F, direction.getStepZ() * 0.2F);
                }
                if (!level.isClientSide) {
                    toolStack.hurtAndBreak(1, (ServerLevel) level, (ServerPlayer) player, (item) -> {
                    });
                }

                be.playProcessingSound(recipe.value().getSoundEvent().orElse(null), toolStack, be.getStoredItem());
                be.removeItem();
                if (player instanceof ServerPlayer) {
                    ModAdvancements.USE_CUTTING_BOARD.get().trigger((ServerPlayer) player);
                }
            });
            cir.cancel();
            cir.setReturnValue(matchingRecipe.isPresent());
        }

    }
}
