package com.minor2cch.eternalstarlightdelight.fabric.registey;

import com.minor2cch.eternalstarlightdelight.block.entity.CustomBlockEntityTypeBuilder;
import com.minor2cch.eternalstarlightdelight.fabric.block.entity.DeepSilverCookingPotBlockEntityFabric;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public final class ESDBlockEntityTypesFabric {
    private ESDBlockEntityTypesFabric() {}
    public static final Supplier<BlockEntityType<DeepSilverCookingPotBlockEntityFabric>> DEEPSILVER_COOKING_POT = registerBlockEntity("deepsilver_cooking_pot", () -> CustomBlockEntityTypeBuilder.of(DeepSilverCookingPotBlockEntityFabric::new,
            ESDBlocks.DEEPSILVER_COOKING_POT.get()
    ).build());
    public static void init(){
        ItemStorage.SIDED.registerForBlockEntity(DeepSilverCookingPotBlockEntityFabric::getStorage, DEEPSILVER_COOKING_POT.get());
    }
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type){
        return ESDPlatform.INSTANCE.blockEntityRegister(id, type);
    }
}
