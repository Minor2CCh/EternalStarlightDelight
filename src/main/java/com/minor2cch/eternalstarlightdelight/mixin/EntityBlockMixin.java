package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.util.ESTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityBlock.class)
public interface EntityBlockMixin {
    @ModifyReturnValue(method = "getTicker(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntityType;)Lnet/minecraft/world/level/block/entity/BlockEntityTicker;", at = @At("RETURN")
            )
    @SuppressWarnings("unchecked")
    private <T extends BlockEntity> BlockEntityTicker<T> injectMendingMethod(BlockEntityTicker<T> original, Level level, BlockState blockState, BlockEntityType<T> blockEntityType){
        if(level.isClientSide || blockEntityType != BlockEntityType.DISPENSER || !ESDConfigLoader.getConfig().getMendableAmaramberToolsInDispenser()){
            return original;
        }

        return createTickerHelper_Dispenser(blockEntityType, (lv, blockPos, state, be) -> {
            if(original != null){
                original.tick(lv, blockPos, state, (T) be);
            }
            if(be instanceof DispenserBlockEntity){
                mendingAmaramberTools_inDispenser(lv, blockPos, state, be);
            }
        });


    }
    @Unique
    @SuppressWarnings("unused")
    private static void mendingAmaramberTools_inDispenser(Level level, BlockPos blockPos, BlockState blockState, BlockEntity be){
        if(be instanceof DispenserBlockEntity dispenserBlockEntity){
            if (!level.isClientSide && level.getGameTime() % 600 == 0) {
                for(int i=0;i<dispenserBlockEntity.getContainerSize();i++) {
                    ItemStack stack = dispenserBlockEntity.getItem(i);
                    if(stack.isDamaged() && (stack.is(ESTags.Items.MENDS_NATURALLY))){
                        stack.setDamageValue(Math.max(stack.getDamageValue() - 1, 0));
                        dispenserBlockEntity.setChanged();
                    }
                }
            }

        }

    }
    @Unique
    @Nullable
    @SuppressWarnings("unchecked")
    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper_Dispenser(
            BlockEntityType<A> blockEntityType, BlockEntityTicker<? super E> blockEntityTicker
    ) {
        return BlockEntityType.DISPENSER == blockEntityType ? (BlockEntityTicker<A>) blockEntityTicker : null;
    }
}
