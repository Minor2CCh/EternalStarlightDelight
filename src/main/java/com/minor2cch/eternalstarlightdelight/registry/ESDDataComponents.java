package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.Supplier;

public final class ESDDataComponents {
    private ESDDataComponents(){}
    public static final Supplier<DataComponentType<Boolean>> IS_FRESH = registerDataComponent("is_fresh", () -> DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build());
    @SuppressWarnings("all")
    private static <T extends DataComponentType<?>> Supplier<T> registerDataComponent(String id, Supplier<T> data){
        return ESDPlatform.INSTANCE.dataComponentRegister(id, data);
    }
    public static void init(){
    }

}
