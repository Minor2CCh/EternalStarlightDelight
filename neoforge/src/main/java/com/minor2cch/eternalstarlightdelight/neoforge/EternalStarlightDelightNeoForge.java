package com.minor2cch.eternalstarlightdelight.neoforge;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.neoforge.platform.NeoForgePlatform;
import com.minor2cch.eternalstarlightdelight.neoforge.registry.ESDBlockEntityTypesNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(EternalStarlightDelight.MOD_ID)
public final class EternalStarlightDelightNeoForge {
    public EternalStarlightDelightNeoForge(IEventBus modEventBus) {
        modEventBus.addListener(NeoForgePlatform::init);
        // Run our common setup.
        EternalStarlightDelight.init();
        ESDBlockEntityTypesNeoForge.init();
        NeoForgePlatform.registryInit(modEventBus);
    }
}
