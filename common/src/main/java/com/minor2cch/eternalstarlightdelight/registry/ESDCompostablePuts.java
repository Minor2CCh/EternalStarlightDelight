package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;

public final class ESDCompostablePuts {
    private ESDCompostablePuts() {}
    public static void init() {
        ESDPlatform.INSTANCE.compostItemRegister(ESDItems.GLOWING_MUSHROOM_COLONY.get(), 1.0F);
        ESDPlatform.INSTANCE.compostItemRegister(ESDItems.BOULDERSHROOM_COLONY.get(), 1.0F);
        ESDPlatform.INSTANCE.compostItemRegister(ESDItems.MARIMOLD_COLONY.get(), 1.0F);
        ESDPlatform.INSTANCE.compostItemRegister(ESDItems.SHADOW_SNAIL_PIE_SLICE.get(), 0.85F);
        ESDPlatform.INSTANCE.compostItemRegister(ESDItems.PUNGENCY_FRUIT_SLICE.get(), 0.5F);
    }
}
