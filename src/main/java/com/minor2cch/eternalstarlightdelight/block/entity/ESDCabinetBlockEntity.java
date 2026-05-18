package com.minor2cch.eternalstarlightdelight.block.entity;

import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;

public class ESDCabinetBlockEntity extends CabinetBlockEntity {
    public ESDCabinetBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ESDBlockEntityTypes.CUSTOM_CABINET.get();
    }
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) {
        return this.getType().isValid(blockState);
    }
}
