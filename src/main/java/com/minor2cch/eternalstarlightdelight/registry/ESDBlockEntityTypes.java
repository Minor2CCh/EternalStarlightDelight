package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.block.entity.*;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public final class ESDBlockEntityTypes {
    private ESDBlockEntityTypes() {}
    public static final Supplier<BlockEntityType<ESDCabinetBlockEntity>> CUSTOM_CABINET = registerBlockEntity("custom_cabinet", () -> CustomBlockEntityTypeBuilder.of(ESDCabinetBlockEntity::new,
            ESDBlocks.LUNAR_CABINET.get(),
            ESDBlocks.NORTHLAND_CABINET.get(),
            ESDBlocks.BANYIN_CABINET.get(),
            ESDBlocks.SCARLET_CABINET.get(),
            ESDBlocks.TORREYA_CABINET.get(),
            ESDBlocks.JINGLESTEM_CABINET.get(),
            ESDBlocks.CRADLEWOOD_CABINET.get()
    ).build());
    public static final Supplier<BlockEntityType<DeepSilverSkilletBlockEntity>> DEEPSILVER_SKILLET = registerBlockEntity("deepsilver_skillet", () -> CustomBlockEntityTypeBuilder.of(DeepSilverSkilletBlockEntity::new,
            ESDBlocks.DEEPSILVER_SKILLET.get()
    ).build());
    public static final Supplier<BlockEntityType<StarlightStoveBlockEntity>> STARLIGHT_STOVE = registerBlockEntity("starlight_stove", () -> CustomBlockEntityTypeBuilder.of(StarlightStoveBlockEntity::new,
            ESDBlocks.STARLIGHT_STOVE.get()
    ).build());
    public static void init(){

    }
    public static BlockEntityType<?> getDeepSilverCookingPot(){         //これだけは共通のブランチだと継承元のimplementsの影響で失敗する
        return ESDPlatform.INSTANCE.getDeepSilverCookingPotBlockEntity();
    }

    private static <T extends BlockEntity>Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> type){
        return ESDPlatform.INSTANCE.blockEntityRegister(id, type);
    }

}
