package com.minor2cch.eternalstarlightdelight.block.entity;

import com.minor2cch.eternalstarlightdelight.mixin.SkilletBlockEntityAccessor;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import com.minor2cch.eternalstarlightdelight.registry.ESDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

public class DeepSilverSkilletBlockEntity extends SkilletBlockEntity {
    public DeepSilverSkilletBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        ((SkilletBlockEntityAccessor)(this)).setSkilletStack(new ItemStack(ESDItems.DEEPSILVER_SKILLET.get()));
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ESDBlockEntityTypes.DEEPSILVER_SKILLET.get();
    }
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) {
        return this.getType().isValid(blockState);
    }
}
