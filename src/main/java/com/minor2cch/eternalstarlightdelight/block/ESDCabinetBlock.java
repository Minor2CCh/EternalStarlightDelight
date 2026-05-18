package com.minor2cch.eternalstarlightdelight.block;

import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import javax.annotation.Nullable;

public class ESDCabinetBlock extends CabinetBlock {
    public ESDCabinetBlock(Properties properties) {
        super(properties);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return ESDBlockEntityTypes.CUSTOM_CABINET.get().create(pos, state);
    }
}
