package com.minor2cch.eternalstarlightdelight.block;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import com.minor2cch.eternalstarlightdelight.block.entity.DeepSilverCookingPotInterface;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CookingPotBlock;

import javax.annotation.Nullable;

public class DeepSilverCookingPotBlock extends CookingPotBlock {
    public DeepSilverCookingPotBlock(Properties properties) {
        super(properties);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ESDBlockEntityTypes.getDeepSilverCookingPot().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {

        if (level.isClientSide) {
            return createTickerHelper(blockEntity, ESDBlockEntityTypes.getDeepSilverCookingPot(), DeepSilverCookingPotInterface::animationTick);
        }
        return createTickerHelper(blockEntity, ESDBlockEntityTypes.getDeepSilverCookingPot(), DeepSilverCookingPotInterface::cookingTick);
    }
    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @org.jetbrains.annotations.Nullable LivingEntity livingEntity, @NotNull ItemStack itemStack) {
        if(livingEntity instanceof ServerPlayer){
            ESDUtils.completeAdvancement((ServerPlayer)livingEntity, ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "main/place_cooking_pot"));
        }
    }
}
