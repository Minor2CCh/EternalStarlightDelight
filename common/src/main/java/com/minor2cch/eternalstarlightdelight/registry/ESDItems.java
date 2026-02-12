package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.item.combat.ESItemTiers;
import com.minor2cch.eternalstarlightdelight.item.*;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.common.registry.ModItems.*;

public final class ESDItems {
    private ESDItems() {}
    public static final Supplier<Item> FILLETING_OF_STARS = registerItem("filleting_of_stars", () -> new AethersentKnifeItem(ESItemTiers.AETHERSENT, ModItems.knifeItem(ESItemTiers.AETHERSENT)));
    public static final Supplier<Item> THERMAL_SPRINGSTONE_KNIFE = registerItem("thermal_springstone_knife", () -> new KnifeItem(ESItemTiers.THERMAL_SPRINGSTONE, ModItems.knifeItem(ESItemTiers.THERMAL_SPRINGSTONE)));
    public static final Supplier<Item> DEEPSILVER_KNIFE = registerItem("deepsilver_knife", () -> new KnifeItem(ESItemTiers.DEEPSILVER, ModItems.knifeItem(ESItemTiers.DEEPSILVER)));
    public static final Supplier<Item> MALARITE_KNIFE = registerItem("malarite_knife", () -> new KnifeItem(ESItemTiers.MALARITE, ModItems.knifeItem(ESItemTiers.MALARITE)));
    public static final Supplier<Item> AMARAMBER_KNIFE = registerItem("amaramber_knife", () -> new KnifeItem(ESItemTiers.AMARAMBER, ModItems.knifeItem(ESItemTiers.AMARAMBER)));
    public static final Supplier<Item> GLACITE_KNIFE = registerItem("glacite_knife", () -> new KnifeItem(ESItemTiers.GLACITE, ModItems.knifeItem(ESItemTiers.GLACITE)));
    public static final Supplier<Item> STARLIT_DIAMOND_KNIFE = registerItem("starlit_diamond_knife", () -> new KnifeItem(ESItemTiers.STARLIT_DIAMOND, ModItems.knifeItem(ESItemTiers.STARLIT_DIAMOND)));
    public static final Supplier<Item> STARFIRE_KNIFE = registerItem("starfire_knife", () -> new KnifeItem(ESItemTiers.STARFIRE, ModItems.knifeItem(ESItemTiers.STARFIRE)));
    public static final Supplier<Item> FLOWGLAZE_KNIFE = registerItem("flowglaze_knife", () -> new KnifeItem(ESItemTiers.FLOWGLAZE, ModItems.knifeItem(ESItemTiers.FLOWGLAZE)));
    public static final Supplier<Item> KNIFE_OF_HUNGER = registerItem("knife_of_hunger", () -> new KnifeOfHungerItem(ESItemTiers.TOOTH_OF_HUNGER, ModItems.knifeItem(ESItemTiers.TOOTH_OF_HUNGER).rarity(Rarity.RARE).attributes(KnifeOfHungerItem.DEFAULT_ATTRIBUTE)));
    public static final Supplier<Item> LUNAR_CABINET = registerItem("lunar_cabinet", () -> new FuelBlockItem(ESDBlocks.LUNAR_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> NORTHLAND_CABINET = registerItem("northland_cabinet", () -> new FuelBlockItem(ESDBlocks.NORTHLAND_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> BANYIN_CABINET = registerItem("banyin_cabinet", () -> new FuelBlockItem(ESDBlocks.BANYIN_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> SCARLET_CABINET = registerItem("scarlet_cabinet", () -> new FuelBlockItem(ESDBlocks.SCARLET_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> TORREYA_CABINET = registerItem("torreya_cabinet", () -> new FuelBlockItem(ESDBlocks.TORREYA_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> JINGLESTEM_CABINET = registerItem("jinglestem_cabinet", () -> new FuelBlockItem(ESDBlocks.JINGLESTEM_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> CRADLEWOOD_CABINET = registerItem("cradlewood_cabinet", () -> new FuelBlockItem(ESDBlocks.CRADLEWOOD_CABINET.get(), basicItem(), 300));
    public static final Supplier<Item> DEEPSILVER_SKILLET = registerItem("deepsilver_skillet",
            () -> new DeepSilverSkilletItem(ESDBlocks.DEEPSILVER_SKILLET.get(), basicItem().stacksTo(1).attributes(SkilletItem.createAttributes(ESItemTiers.DEEPSILVER, 5.0F, -3.1F))));
    public static final Supplier<Item> LUNAR_BERRY_CRATE = registerItem("lunar_berry_crate",
            () -> new BlockItem(ESDBlocks.LUNAR_BERRY_CRATE.get(), basicItem()));
    public static final Supplier<Item> ABYSSAL_FRUIT_CRATE = registerItem("abyssal_fruit_crate",
            () -> new BlockItem(ESDBlocks.ABYSSAL_FRUIT_CRATE.get(), basicItem()));
    public static final Supplier<Item> PUNGENCY_FRUIT_CRATE = registerItem("pungency_fruit_crate",
            () -> new BlockItem(ESDBlocks.PUNGENCY_FRUIT_CRATE.get(), basicItem()));
    public static final Supplier<Item> BLOSSOM_OF_STARS_CRATE = registerItem("blossom_of_stars_crate",
            () -> new BlockItem(ESDBlocks.BLOSSOM_OF_STARS_CRATE.get(), basicItem().rarity(Rarity.RARE)));
    public static final Supplier<Item> BOULDERSHROOM_COLONY = registerItem("bouldershroom_colony",
            () -> new MushroomColonyItem(ESDBlocks.BOULDERSHROOM_COLONY.get(), basicItem()));
    public static final Supplier<Item> GLOWING_MUSHROOM_COLONY = registerItem("glowing_mushroom_colony",
            () -> new MushroomColonyItem(ESDBlocks.GLOWING_MUSHROOM_COLONY.get(), basicItem()));
    public static final Supplier<Item> MARIMOLD_COLONY = registerItem("marimold_colony",
            () -> new MushroomColonyItem(ESDBlocks.MARIMOLD_COLONY.get(), basicItem()));
    public static final Supplier<Item> STARLIGHT_STOVE = registerItem("starlight_stove",
            () -> new BlockItem(ESDBlocks.STARLIGHT_STOVE.get(), basicItem()));
    public static final Supplier<Item> DEEPSILVER_COOKING_POT = registerItem("deepsilver_cooking_pot",
            () -> new DeepSilverCookingPotItem(ESDBlocks.DEEPSILVER_COOKING_POT.get(), basicItem().stacksTo(1)));



    //foods
    public static final Supplier<Item> DRIED_ROOKFISH = registerItem("dried_rookfish",
            () -> new ConsumableItem(basicItem().food(ESDFoods.DRIED_ROOKFISH.get()), true));
    public static final Supplier<Item> PUNGENCY_FRUIT_SLICE = registerItem("pungency_fruit_slice",
            () -> new Item(basicItem().food(ESDFoods.PUNGENCY_FRUIT_SLICE.get())));
    public static final Supplier<Item> STARLIGHT_SOUP = registerItem("starlight_soup",
            () -> new ConsumableItem(bowlFoodItem(ESDFoods.STARLIGHT_SOUP.get()), true, false));
    public static final Supplier<Item> LUMINARIS_SLICE = registerItem("luminaris_slice",
            () -> new Item(basicItem().food(FoodValues.SALMON_SLICE)));
    public static final Supplier<Item> COOKED_LUMINARIS_SLICE = registerItem("cooked_luminaris_slice",
            () -> new Item(basicItem().food(FoodValues.COOKED_SALMON_SLICE)));
    public static final Supplier<Item> LUMINOFISH_SLICE = registerItem("luminofish_slice",
            () -> new Item(basicItem().food(FoodValues.SALMON_SLICE)));
    public static final Supplier<Item> COOKED_LUMINOFISH_SLICE = registerItem("cooked_luminofish_slice",
            () -> new Item(basicItem().food(FoodValues.COOKED_SALMON_SLICE)));
    public static final Supplier<Item> AURORA_DEER_STEAK_CUTS = registerItem("aurora_deer_steak_cuts",
            () -> new Item(basicItem().food(FoodValues.MINCED_BEEF)));
    public static final Supplier<Item> COOKED_AURORA_DEER_STEAK_CUTS = registerItem("cooked_aurora_deer_steak_cuts",
            () -> new Item(basicItem().food(FoodValues.BEEF_PATTY)));
    public static final Supplier<Item> SHADOW_SNAIL_MEAT_CUTS = registerItem("shadow_snail_meat_cuts",
            () -> new Item(basicItem().food(FoodValues.CHICKEN_CUTS)));
    public static final Supplier<Item> COOKED_SHADOW_SNAIL_MEAT_CUTS = registerItem("cooked_shadow_snail_meat_cuts",
            () -> new Item(basicItem().food(FoodValues.COOKED_COD_SLICE)));
    public static final Supplier<Item> RATLIN_MEAT_CUTS = registerItem("ratlin_meat_cuts",
            () -> new Item(basicItem().food(FoodValues.MUTTON_CHOPS)));
    public static final Supplier<Item> COOKED_RATLIN_MEAT_CUTS = registerItem("cooked_ratlin_meat_cuts",
            () -> new Item(basicItem().food(FoodValues.COOKED_MUTTON_CHOPS)));
    public static final Supplier<Item> LUMINOFISH_ROLL = registerItem("luminofish_roll",
            () -> new Item(basicItem().food(FoodValues.SALMON_ROLL)));
    public static final Supplier<Item> LUMINARIS_ROLL = registerItem("luminaris_roll",
            () -> new Item(basicItem().food(FoodValues.COD_ROLL)));
    public static final Supplier<Item> SHADOW_SNAIL_PIE_SLICE = registerItem("shadow_snail_pie_slice",
            () -> new Item(basicItem().food(ESDFoods.SHADOW_SNAIL_PIE_SLICE.get())));

    public static final Supplier<Item> ABYSSAL_JUICE = registerItem("abyssal_juice",
            () -> new ESDDrinkableItem(drinkItem().food(ESDFoods.ABYSSAL_JUICE.get()), true, false));

    public static final Supplier<Item> ETHER_BOTTLE = registerItem("ether_bottle",
            () -> new EtherBottleItem(drinkItem().food(ESDFoods.ETHER_BOTTLE.get()), true, false));
    public static final Supplier<Item> AETHERSENT_FLAVOR_CANDY = registerItem("aethersent_flavor_candy",
            () -> new ConsumableItem(basicItem().food(ESDFoods.AETHERSENT_FLAVOR_CANDY.get()), true, false));
    public static final Supplier<Item> STARCORE_FLAVOR_CANDY = registerItem("starcore_flavor_candy",
            () -> new ConsumableItem(basicItem().food(ESDFoods.STARCORE_FLAVOR_CANDY.get()), true, false));
    public static final Supplier<Item> STARFIRE_FRIED_EGG = registerItem("starfire_fried_egg",
            () -> new ConsumableItem(basicItem().food(ESDFoods.STARFIRE_FRIED_EGG.get()), true, false));
    public static final Supplier<Item> CRINOA_DOUGH = registerItem("crinoa_dough",
            () -> new Item(basicItem().food(ESDFoods.CRINOA_DOUGH.get())));
    public static final Supplier<Item> CRINOA_BREAD = registerItem("crinoa_bread",
            () -> new Item(basicItem().food(ESDFoods.CRINOA_BREAD.get())));
    public static final Supplier<Item> MARIMOLD_STEW = registerItem("marimold_stew",
            () -> new ConsumableItem(bowlFoodItem(ESDFoods.MARIMOLD_STEW.get()), true, false));
    public static final Supplier<Item> STARLIGHT_HAMBURGER = registerItem("starlight_hamburger",
            () -> new Item(basicItem().food(ESDFoods.STARLIGHT_HAMBURGER.get())));
    public static final Supplier<Item> AURORA_DEER_DICE_STEAK_PASTA = registerItem("aurora_deer_dice_steak_pasta",
            () -> new ConsumableItem(bowlFoodItem(ESDFoods.AURORA_DEER_DICE_STEAK_PASTA.get()), true, false));
    public static final Supplier<Item> LUNARIS_SHERBET = registerItem("lunaris_sherbet",
            () -> new ConsumableItem(foodItem(ESDFoods.LUNARIS_SHERBET.get()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), true, false));
    public static final Supplier<Item> FROZEN_TUBE_BALE = registerItem("frozen_tube_bale",
            () -> new BlockItem(ESDBlocks.FROZEN_TUBE_BALE.get(), basicItem()));
    public static final Supplier<Item> AMARAMBER_POPSICLE = registerItem("amaramber_popsicle",
            () -> new ConsumableItem(basicItem().food(ESDFoods.AMARAMBER_POPSICLE.get()), true, false));
    public static final Supplier<Item> STARMINA_NOODLE_SOUP = registerItem("starmina_noodle_soup",
            () -> new ConsumableItem(bowlFoodItem(ESDFoods.STARMINA_NOODLE_SOUP.get()), true, false));
    public static final Supplier<Item> AURORA_DEER_JERKY = registerItem("aurora_deer_jerky",
            () -> new ConsumableItem(foodItem(ESDFoods.AURORA_DEER_JERKY.get()), true, false));
    public static final Supplier<Item> AURORA_DEER_STEAK_SPECIAL_LUNCH = registerItem("aurora_deer_steak_special_lunch",
            () -> new ConsumableItem(bowlFoodItem(ESDFoods.AURORA_DEER_STEAK_SPECIAL_LUNCH.get()), true, false));
    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item){
        return ESDPlatform.INSTANCE.itemRegister(id, item);
    }
    public static void init(){
    }
}
