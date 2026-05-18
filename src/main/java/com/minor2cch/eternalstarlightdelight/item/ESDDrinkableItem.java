package com.minor2cch.eternalstarlightdelight.item;

import com.minor2cch.eternalstarlightdelight.registry.ESDSoundEvents;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.DrinkableItem;

@SuppressWarnings("unused")
public class ESDDrinkableItem extends DrinkableItem {
    public ESDDrinkableItem(Properties properties) {
        super(properties);
    }

    public ESDDrinkableItem(Properties properties, boolean hasFoodEffectTooltip) {
        super(properties, hasFoodEffectTooltip);
    }

    public ESDDrinkableItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }
    @Override
    public @NotNull SoundEvent getEatingSound() {
        return ESDSoundEvents.SILENT.get();
    }

}
