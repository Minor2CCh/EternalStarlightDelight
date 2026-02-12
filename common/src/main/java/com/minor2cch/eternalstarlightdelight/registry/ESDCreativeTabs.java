package com.minor2cch.eternalstarlightdelight.registry;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
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
                output.accept(ESDItems.DEEPSILVER_SKILLET.get());
                output.accept(ESDItems.DEEPSILVER_COOKING_POT.get());
                output.accept(ESDItems.LUNAR_CABINET.get());
                output.accept(ESDItems.NORTHLAND_CABINET.get());
                output.accept(ESDItems.BANYIN_CABINET.get());
                output.accept(ESDItems.SCARLET_CABINET.get());
                output.accept(ESDItems.TORREYA_CABINET.get());
                output.accept(ESDItems.JINGLESTEM_CABINET.get());
                output.accept(ESDItems.CRADLEWOOD_CABINET.get());
                output.accept(ESDItems.LUNAR_BERRY_CRATE.get());
                output.accept(ESDItems.ABYSSAL_FRUIT_CRATE.get());
                output.accept(ESDItems.PUNGENCY_FRUIT_CRATE.get());
                output.accept(ESDItems.BOULDERSHROOM_COLONY.get());
                output.accept(ESDItems.GLOWING_MUSHROOM_COLONY.get());
                output.accept(ESDItems.MARIMOLD_COLONY.get());
                output.accept(ESDItems.STARLIGHT_STOVE.get());
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
                output.accept(ESDItems.FROZEN_TUBE_BALE.get());
                output.accept(ESDItems.AMARAMBER_POPSICLE.get());
                output.accept(ESDItems.STARMINA_NOODLE_SOUP.get());
                output.accept(ESDItems.AURORA_DEER_JERKY.get());
                output.accept(ESDItems.AURORA_DEER_STEAK_SPECIAL_LUNCH.get());
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
