package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.registry.ESDataComponents;
import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.item.KnifeOfHungerItem;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ESDCreativeTabs {
    private ESDCreativeTabs() {}
    public static final Supplier<CreativeModeTab> TAB_TEST = ESDPlatform.INSTANCE.creativeModeTabRegister(
            "tab",
            () -> new ItemStack(ESDItems.FILLETING_OF_STARS.get()),
            (parameters, output) ->{
                // tools
                output.accept(ESDItems.FILLETING_OF_STARS.get());
                output.accept(ESDItems.THERMAL_SPRINGSTONE_KNIFE.get());
                output.accept(ESDItems.DEEPSILVER_KNIFE.get());
                output.accept(ESDItems.MALARITE_KNIFE.get());
                output.accept(ESDItems.AMARAMBER_KNIFE.get());
                output.accept(ESDItems.GLACITE_KNIFE.get());
                output.accept(ESDItems.STARLIT_DIAMOND_KNIFE.get());
                output.accept(ESDItems.STARFIRE_KNIFE.get());
                output.accept(ESDItems.FLOWGLAZE_KNIFE.get());
                output.accept(ESDItems.KNIFE_OF_HUNGER.get());
                ItemStack hungerStack = new ItemStack(ESDItems.KNIFE_OF_HUNGER.get());
                hungerStack.set(ESDataComponents.HUNGER_LEVEL.get(), -1.0F);
                hungerStack.set(DataComponents.ATTRIBUTE_MODIFIERS, KnifeOfHungerItem.PENALTY_ATTRIBUTE);
                output.accept(hungerStack);
                ItemStack saturatedStack = new ItemStack(ESDItems.KNIFE_OF_HUNGER.get());
                saturatedStack.set(ESDataComponents.HUNGER_LEVEL.get(), 1.0F);
                saturatedStack.set(DataComponents.ATTRIBUTE_MODIFIERS, KnifeOfHungerItem.BONUS_ATTRIBUTE);
                output.accept(saturatedStack);
                output.accept(ESDItems.PUNGENCY_FRUIT_KNIFE.get());
                output.accept(ESDItems.UNREALIUM_KNIFE.get());
                output.accept(ESDItems.GLISTERING_KNIFE.get());
                // cooking blocks
                output.accept(ESDItems.DEEPSILVER_SKILLET.get());
                output.accept(ESDItems.DEEPSILVER_COOKING_POT.get());
                output.accept(ESDItems.STARLIGHT_STOVE.get());
                // cabinets
                output.accept(ESDItems.LUNAR_CABINET.get());
                output.accept(ESDItems.NORTHLAND_CABINET.get());
                output.accept(ESDItems.BANYIN_CABINET.get());
                output.accept(ESDItems.SCARLET_CABINET.get());
                output.accept(ESDItems.TORREYA_CABINET.get());
                output.accept(ESDItems.JINGLESTEM_CABINET.get());
                output.accept(ESDItems.CRADLEWOOD_CABINET.get());
                // crates
                output.accept(ESDItems.LUNAR_BERRY_CRATE.get());
                output.accept(ESDItems.ABYSSAL_FRUIT_CRATE.get());
                output.accept(ESDItems.PUNGENCY_FRUIT_CRATE.get());
                output.accept(ESDItems.SILVER_PUNGENCY_FRUIT_CRATE.get());
                // colonies
                output.accept(ESDItems.BOULDERSHROOM_COLONY.get());
                output.accept(ESDItems.GLOWING_MUSHROOM_COLONY.get());
                output.accept(ESDItems.MARIMOLD_COLONY.get());
                // other building blocks
                output.accept(ESDItems.FROZEN_TUBE_BALE.get());
                // foods
                output.accept(ESDItems.DRIED_ROOKFISH.get());
                output.accept(ESDItems.PUNGENCY_FRUIT_SLICE.get());
                output.accept(ESDItems.STARLIGHT_SOUP.get());
                output.accept(ESDItems.LUMINARIS_SLICE.get());
                output.accept(ESDItems.COOKED_LUMINARIS_SLICE.get());
                output.accept(ESDItems.LUMINOFISH_SLICE.get());
                output.accept(ESDItems.COOKED_LUMINOFISH_SLICE.get());
                output.accept(ESDItems.ABYSSAL_JUICE.get());
                output.accept(ESDItems.AURORA_DEER_STEAK_CUTS.get());
                output.accept(ESDItems.COOKED_AURORA_DEER_STEAK_CUTS.get());
                output.accept(ESDItems.RATLIN_MEAT_CUTS.get());
                output.accept(ESDItems.COOKED_RATLIN_MEAT_CUTS.get());
                output.accept(ESDItems.SHADOW_SNAIL_MEAT_CUTS.get());
                output.accept(ESDItems.COOKED_SHADOW_SNAIL_MEAT_CUTS.get());
                output.accept(ESDItems.ETHER_BOTTLE.get());
                output.accept(ESDItems.LUMINOFISH_ROLL.get());
                output.accept(ESDItems.LUMINARIS_ROLL.get());
                output.accept(ESDItems.SHADOW_SNAIL_PIE_SLICE.get());
                output.accept(ESDItems.AETHERSENT_FLAVOR_CANDY.get());
                output.accept(ESDItems.STARCORE_FLAVOR_CANDY.get());
                output.accept(ESDItems.STARFIRE_FRIED_EGG.get());
                output.accept(ESDItems.CRINOA_DOUGH.get());
                output.accept(ESDItems.CRINOA_BREAD.get());
                output.accept(ESDItems.MARIMOLD_STEW.get());
                output.accept(ESDItems.STARLIGHT_HAMBURGER.get());
                output.accept(ESDItems.AURORA_DEER_DICE_STEAK_PASTA.get());
                output.accept(ESDItems.LUNARIS_SHERBET.get());
                output.accept(ESDItems.AMARAMBER_POPSICLE.get());
                output.accept(ESDItems.STARMINA_NOODLE_SOUP.get());
                output.accept(ESDItems.AURORA_DEER_JERKY.get());
                output.accept(ESDItems.AURORA_DEER_STEAK_SPECIAL_LUNCH.get());
                output.accept(ESDItems.SEEKER_TENTACLE_CUTS.get());
                output.accept(ESDItems.COOKED_SEEKER_TENTACLE_CUTS.get());
                output.accept(ESDItems.DRIED_SEEKER_TENTACLE_CUTS.get());
                output.accept(ESDItems.LUNAR_BERRY_PIE.get());
                output.accept(ESDItems.LUNAR_BERRY_PIE_SLICE.get());
                output.accept(ESDItems.STARLIT_SALAD.get());
                // accessories
                output.accept(ESDItems.THERMAL_SPRINGBLADE_STRAP.get());
                output.accept(ESDItems.STARFIRE_FLOWER_STRAP.get());
            }

    );
    public static CreativeModeTab createBuilder(CreativeModeTab.Builder builder, String id, Supplier<ItemStack> iconStack, BiConsumer<CreativeModeTab.ItemDisplayParameters, CreativeModeTab.Output> createTab){
        return builder
                .title(Component.translatable((String.format("itemGroup.%s.%s", EternalStarlightDelight.MOD_ID, id))))
                .icon(iconStack)
                .displayItems(createTab::accept).build();
    }
    public static void init(){
    }
}
