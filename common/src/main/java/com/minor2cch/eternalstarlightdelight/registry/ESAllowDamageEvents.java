package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.data.ESDamageTypes;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;

public final class ESAllowDamageEvents {
    private ESAllowDamageEvents() {}
    public static void init(){
        ESDPlatform.INSTANCE.allowDamageEventRegister((entity, source, amount) -> {
            if(source.is(ESDamageTypes.STARFIRE)){
                if(entity.hasEffect(ESDMobEffects.STARFIRE_WARMTH)){
                    return false;
                }
            }

            return true;
        });
    }
}
