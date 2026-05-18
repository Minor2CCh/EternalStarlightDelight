package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public final class ESDSoundEvents {
    private ESDSoundEvents() {}
    public static final Supplier<SoundEvent> SILENT = register("silent");
    private static Supplier<SoundEvent> register(String id){
        return ESDPlatform.INSTANCE.soundTypeRegister(id);
    }
    public static void init(){
    }
}
