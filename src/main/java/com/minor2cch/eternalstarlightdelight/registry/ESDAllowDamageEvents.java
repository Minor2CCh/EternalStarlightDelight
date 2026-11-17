package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.data.ESDamageTypes;
import cn.leolezury.eternalstarlight.common.util.ESAccessoryUtil;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.util.ESDTags;

public final class ESDAllowDamageEvents {
    private ESDAllowDamageEvents() {}
    public static void init(){
        ESDPlatform.INSTANCE.allowDamageEventRegister((entity, source, amount) -> {
            if(source.is(ESDamageTypes.STARFIRE)){
                if(entity.hasEffect(ESDMobEffects.STARFIRE_WARMTH)){
                    return false;
                }
            }
            if(ESAccessoryUtil.getActiveAccessoriesOnArmors(entity).contains(ESDItems.STARLIGHT_FLOWER_HEADDRESS.get()) && source.is(ESDTags.DamageTypes.STARLIGHT_FLOWER_HEADDRESS_IMMUNE)){
                if(entity.hasEffect(ESDMobEffects.STARLIGHT_BLESSING)){
                    return false;
                }
            }

            return true;
        });
    }
}
