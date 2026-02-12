package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import cn.leolezury.eternalstarlight.common.registry.ESItems;
import com.minor2cch.eternalstarlightdelight.block.*;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.function.Supplier;

public final class ESDBlocks {
    private ESDBlocks() {}
    public static final Supplier<Block> LUNAR_CABINET = registerBlock("lunar_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> NORTHLAND_CABINET = registerBlock("northland_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> BANYIN_CABINET = registerBlock("banyin_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> SCARLET_CABINET = registerBlock("scarlet_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> TORREYA_CABINET = registerBlock("torreya_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> JINGLESTEM_CABINET = registerBlock("jinglestem_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> CRADLEWOOD_CABINET = registerBlock("cradlewood_cabinet", () -> new ESDCabinetBlock(Block.Properties.ofFullCopy(Blocks.BARREL)));
    public static final Supplier<Block> LUNAR_BERRY_CRATE = registerBlock("lunar_berry_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(state -> 15)));
    public static final Supplier<Block> ABYSSAL_FRUIT_CRATE = registerBlock("abyssal_fruit_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> PUNGENCY_FRUIT_CRATE = registerBlock("pungency_fruit_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLOSSOM_OF_STARS_CRATE = registerBlock("blossom_of_stars_crate",
            () -> new Block(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BOULDERSHROOM_COLONY = registerBlock("bouldershroom_colony",
            () -> new BouldershroomColonyBlock(ESItems.BOULDERSHROOM.asHolder(), Block.Properties.ofFullCopy(ESBlocks.BOULDERSHROOM.get())));
    public static final Supplier<Block> GLOWING_MUSHROOM_COLONY = registerBlock("glowing_mushroom_colony",
            () -> new MushroomColonyBlock(ESItems.GLOWING_MUSHROOM.asHolder(), Block.Properties.ofFullCopy(ESBlocks.GLOWING_MUSHROOM.get())));
    public static final Supplier<Block> MARIMOLD_COLONY = registerBlock("marimold_colony",
            () -> new MarimoldColonyBlock(ESItems.MARIMOLD.asHolder(), Block.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WET_GRASS)
                    .pushReaction(PushReaction.DESTROY)
                    .mapColor(MapColor.COLOR_YELLOW)
                    .randomTicks()
                    .lightLevel(state -> 12)));
    public static final Supplier<Block> DEEPSILVER_SKILLET = registerBlock("deepsilver_skillet",
            () -> new DeepSilverSkilletBlock(Block.Properties.ofFullCopy(ModBlocks.SKILLET.get())));
    public static final Supplier<Block> STARLIGHT_STOVE = registerBlock("starlight_stove",
            () -> new StarlightStoveBlock(Block.Properties.ofFullCopy(ModBlocks.STOVE.get())));
    public static final Supplier<Block> DEEPSILVER_COOKING_POT = registerBlock("deepsilver_cooking_pot",
            () -> new DeepSilverCookingPotBlock(Block.Properties.ofFullCopy(ModBlocks.COOKING_POT.get())));
    public static final Supplier<Block> FROZEN_TUBE_BALE = registerBlock("frozen_tube_bale",
            () -> new RotatedPillarBlock(Block.Properties.of().sound(SoundType.GLASS).mapColor(MapColor.ICE).strength(1.5F, 2.0F)));
    private static <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block){
        return ESDPlatform.INSTANCE.blockRegister(id, block);
    }
    public static void init(){
    }
}
