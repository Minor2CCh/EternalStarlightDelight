package com.minor2cch.eternalstarlightdelight;

import cn.leolezury.eternalstarlight.common.block.AlloyFurnaceBlock;
import cn.leolezury.eternalstarlight.common.block.AlloyFurnaceCoolingItem;
import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import cn.leolezury.eternalstarlight.common.item.combat.EnergyBoomerangItem;
import cn.leolezury.eternalstarlight.common.registry.ESFoods;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.registry.ModEffects;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;


public final class EternalStarlightDelight {
    public static final String MOD_ID = "eternal_starlight_delight";
    public static final Logger LOGGER = LogManager.getLogger("Eternal Starlight's Delight");
    private EternalStarlightDelight() {}
    public static void init() {
        ESDConfigLoader.load();
        ESDBlocks.init();
        ESDItems.init();
        ESDCreativeTabs.init();
        ESDBlockEntityTypes.init();
        ESDMobEffects.init();
        ESDSoundEvents.init();
        ESDRecipeSerializers.init();
        ESAllowDamageEvents.init();
        ESDPlatform.INSTANCE.delayedInit(ESDCompostablePuts::init);
        ESDPlatform.INSTANCE.delayedInit(EternalStarlightDelight::modifyItemComponent);
        ESDPlatform.INSTANCE.delayedInit(EternalStarlightDelight::registerCoolingItem);
        ESDPlatform.INSTANCE.removeRecipe(ResourceLocation.fromNamespaceAndPath("fces","torreya_log"));//Farmer's Cutting: Eternal Starlightがある場合は上書き
        ESDPlatform.INSTANCE.delayedInit(EternalStarlightDelight::extraTag);
        ESDItemUseEvents.init();
    }
    public static ResourceLocation of(String id){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
    private static void modifyItemComponent(){
        if(ESDConfigLoader.getConfig().getOverrideList().getESDSoupStackOverride()){
            ESDPlatform.INSTANCE.modifyStewItemComponentEntry(ESItems.BOULDERSHROOM_STEW, DataComponents.MAX_STACK_SIZE, 16);
            ESDPlatform.INSTANCE.modifyStewItemComponentEntry(ESItems.PUNGENCY_STEW, DataComponents.MAX_STACK_SIZE, 16);
            ESDPlatform.INSTANCE.modifyStewItemComponentEntry(ESItems.CRINOA_PORRIDGE, DataComponents.MAX_STACK_SIZE, 16);
        }
        if(ESDConfigLoader.getConfig().getOverrideList().getExtraESDSoupEffect()){
            ESDPlatform.INSTANCE.modifyItemComponentEntry(ESItems.CRINOA_PORRIDGE, DataComponents.FOOD, new FoodProperties.Builder().nutrition(ESFoods.CRINOA_PORRIDGE.get().nutrition()).saturationModifier(ESFoods.CRINOA_PORRIDGE.get().saturation() / Math.max(ESFoods.CRINOA_PORRIDGE.get().nutrition(), 1) / 2).effect(new MobEffectInstance(ModEffects.COMFORT, 3600, 0, false, false, false), 1.0F).usingConvertsTo(Items.BOWL).build());
        }
        ESDPlatform.INSTANCE.modifyItemComponentEntry(ESDItems.DEEPSILVER_SKILLET, DataComponents.MAX_DAMAGE, ESItemTiers.DEEPSILVER.getUses());//SkilletItemを継承する場合、後付で変える必要がある
        ESDPlatform.INSTANCE.removeItemComponentEntry(ESDItems.KNIFE_OF_HUNGER, DataComponents.MAX_DAMAGE);//耐久値はない扱い
        //ESDPlatform.INSTANCE.modifyItemComponentEntry(() -> Items.GLISTERING_MELON_SLICE, DataComponents.FOOD, new FoodProperties.Builder().nutrition(6).saturationModifier(1.2F).build());

        if(ESDConfigLoader.getConfig().getBoomerangUsableKnife()){
            ESDPlatform.INSTANCE.modifyItemComponentEntry(ESItems.ENERGY_BOOMERANG, DataComponents.TOOL, ((EnergyBoomerangItem)(ESItems.ENERGY_BOOMERANG.get())).getTier().createToolProperties(ModTags.MINEABLE_WITH_KNIFE));
        }
    }
    private static void registerCoolingItem(){
        AlloyFurnaceBlock.registerCoolingItem(ESDItems.FROZEN_TUBE_BALE.get(), new AlloyFurnaceCoolingItem(16000, 4));
        AlloyFurnaceBlock.registerCoolingItem(ESDItems.GLACITE_KNIFE.get(), new AlloyFurnaceCoolingItem(300, 4));
    }
    private static void extraTag(){
        if(ESDConfigLoader.getConfig().getBoomerangUsableKnife()){
            ESDPlatform.INSTANCE.injectTag(CommonTags.TOOLS_KNIFE, ESItems.ENERGY_BOOMERANG.get());
            ESDPlatform.INSTANCE.injectTag(ModTags.KNIVES, ESItems.ENERGY_BOOMERANG.get());
        }
    }
}
