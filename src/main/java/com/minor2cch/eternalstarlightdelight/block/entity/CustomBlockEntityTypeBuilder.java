package com.minor2cch.eternalstarlightdelight.block.entity;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;
import java.util.function.BiFunction;

public class CustomBlockEntityTypeBuilder<T extends BlockEntity>{
    private final BiFunction<BlockPos, BlockState, T> factory;
    private final Set<Block> validBlocks;

    private CustomBlockEntityTypeBuilder(BiFunction<BlockPos, BlockState, T> factory, Set<Block> validBlocks) {
        this.factory = factory;
        this.validBlocks = validBlocks;
    }

    public static <T extends BlockEntity> CustomBlockEntityTypeBuilder<T> of(
            BiFunction<BlockPos, BlockState, T> factory, Block... blocks
    ) {
        return new CustomBlockEntityTypeBuilder<>(factory, Set.of(blocks));
    }

    public BlockEntityType<T> build() {
        // build(null) と同等の動作
        return ESDPlatform.INSTANCE.getBlockEntityType(this.factory, this.validBlocks);
    }
}
