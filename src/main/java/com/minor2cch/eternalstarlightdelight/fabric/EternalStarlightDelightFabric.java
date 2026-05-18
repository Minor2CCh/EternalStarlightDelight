package com.minor2cch.eternalstarlightdelight.fabric;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.fabric.event.LootModifyEvent;
import com.minor2cch.eternalstarlightdelight.fabric.platform.FabricPlatform;
import com.minor2cch.eternalstarlightdelight.fabric.registey.ESDBlockEntityTypesFabric;
import com.minor2cch.eternalstarlightdelight.fabric.worldgen.ESDFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

public final class EternalStarlightDelightFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        EternalStarlightDelight.init();
        DefaultItemComponentEvents.MODIFY.register(FabricPlatform::onModifyDefaultComponents);
        ESDBlockEntityTypesFabric.init();
        LootModifyEvent.init();
        ESDFeatures.init();

    }
}
