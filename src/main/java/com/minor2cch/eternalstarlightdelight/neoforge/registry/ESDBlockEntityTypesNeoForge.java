package com.minor2cch.eternalstarlightdelight.neoforge.registry;

import com.minor2cch.eternalstarlightdelight.block.entity.CustomBlockEntityTypeBuilder;
import com.minor2cch.eternalstarlightdelight.neoforge.block.entity.DeepSilverCookingPotBlockEntityNeoForge;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public final class ESDBlockEntityTypesNeoForge {
    private ESDBlockEntityTypesNeoForge() {}
    public static final Supplier<BlockEntityType<DeepSilverCookingPotBlockEntityNeoForge>> DEEPSILVER_COOKING_POT = registerBlockEntity("deepsilver_cooking_pot", () -> CustomBlockEntityTypeBuilder.of(DeepSilverCookingPotBlockEntityNeoForge::new,
            ESDBlocks.DEEPSILVER_COOKING_POT.get()
    ).build());
    public static void init(){

    }
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type){
        return ESDPlatform.INSTANCE.blockEntityRegister(id, type);
    }
}
