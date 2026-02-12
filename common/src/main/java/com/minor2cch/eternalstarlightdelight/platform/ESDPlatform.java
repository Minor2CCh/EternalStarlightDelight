package com.minor2cch.eternalstarlightdelight.platform;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriFunction;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface ESDPlatform {
    ESDPlatform INSTANCE = Util.make(() -> {
        final ServiceLoader<ESDPlatform> loader = ServiceLoader.load(ESDPlatform.class);
        final Iterator<ESDPlatform> iterator = loader.iterator();
        if (!iterator.hasNext()) {
            throw new RuntimeException("Platform instance not found!");
        } else {
            final ESDPlatform platform = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException("More than one platform instance was found!");
            }
            return platform;
        }
    });
    enum ModLoader{
        NEOFORGE,
        FABRIC
    }
    interface QuadConsumer<A, B, C, D> {
        void accept(A a, B b, C c, D d);
    }
    interface QuintConsumer<A, B, C, D, E> {
        void accept(A a, B b, C c, D d, E e);
    }
    List<ResourceLocation> REMOVE_RECIPE_LIST = new ArrayList<>();
    List<Map.Entry<TagKey<Item>, Item>> TAG_INJECT_ITEM_LIST = new ArrayList<>();
    List<Map.Entry<TagKey<Item>, TagKey<Item>>> TAG_INJECT_TAG_LIST = new ArrayList<>();
    ModLoader getModLoader();
    <T extends Block> Supplier<T> blockRegister(String id, Supplier<T> block);
    <T extends Item> Supplier<T> itemRegister(String id, Supplier<T> item);
    Supplier<CreativeModeTab> creativeModeTabRegister(String id, Supplier<ItemStack> stack, BiConsumer<CreativeModeTab.ItemDisplayParameters, CreativeModeTab.Output> createTab);
    Holder<MobEffect> mobEffectRegister(String id, Supplier<MobEffect> effect);
    Path getConfigPath();
    boolean isModLoaded(String id);
    <T extends BlockEntity>Supplier<BlockEntityType<T>> blockEntityRegister(String id, Supplier<BlockEntityType<T>> type);
    <T extends BlockEntity>BlockEntityType<T> getBlockEntityType(BiFunction<BlockPos, BlockState, T> factory, Set<Block> validBlocks);
    <T> void modifyStewItemComponentEntry(Supplier<Item> item, DataComponentType<T> data, T obj);
    <T> void modifyItemComponentEntry(Supplier<Item> item, DataComponentType<T> data, T obj);
    <T> void removeItemComponentEntry(Supplier<Item> item, DataComponentType<T> data);
    default void removeRecipe(ResourceLocation recipeId){
        REMOVE_RECIPE_LIST.add(recipeId);
    }
    default void injectTag(TagKey<Item> baseTag, Item item){
        TAG_INJECT_ITEM_LIST.addLast(Map.entry(baseTag, item));
    }
    default void injectTag(TagKey<Item> baseTag, TagKey<Item> injectedTag){
        TAG_INJECT_TAG_LIST.addLast(Map.entry(baseTag, injectedTag));
    }
    void useItemCallBack(TriFunction<Player, Level, InteractionHand, InteractionResultHolder<ItemStack>> function);
    void compostItemRegister(Item item, float value);
    InteractionResultHolder<ItemStack> deepsilverSkilletUsing(Level level, Player player, InteractionHand hand);
    void delayedInit(Runnable runnable);
    BiFunction<BlockPos, BlockState, ? extends BlockEntity> instanceDeepSilverCookingPotBlockEntity();
    Supplier<BlockEntityType<? extends BlockEntity>> registerDeepSilverCookingPot(String id);
    ItemStack getMealFromItem(ItemStack cookingPotStack);
    BlockEntityType<?> getDeepSilverCookingPotBlockEntity();
    Supplier<SoundEvent> soundTypeRegister(String id);
    TagKey<Item> getWaterBucketTag();
    <T extends RecipeSerializer<?>> Supplier<T> recipeSerializerRegister(String id, Supplier<T> recipeSerializer);
    void allowDamageEventRegister(TriFunction<LivingEntity, DamageSource, Float, Boolean> function);
}
