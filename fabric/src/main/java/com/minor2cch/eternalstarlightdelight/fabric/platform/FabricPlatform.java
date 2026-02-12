package com.minor2cch.eternalstarlightdelight.fabric.platform;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.fabric.block.entity.DeepSilverCookingPotBlockEntityFabric;
import com.minor2cch.eternalstarlightdelight.fabric.registey.ESDBlockEntityTypesFabric;
import com.minor2cch.eternalstarlightdelight.item.DeepSilverSkilletItem;
import com.minor2cch.eternalstarlightdelight.mixin.SkilletItemInvoker;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import com.minor2cch.eternalstarlightdelight.registry.ESDCreativeTabs;
import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriFunction;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

@AutoService(ESDPlatform.class)
public class FabricPlatform implements ESDPlatform {
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentMap.Builder>>> ITEM_BUILDER_LIST = new ArrayList<>();
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentMap.Builder>>> STEW_ITEM_BUILDER_LIST = new ArrayList<>();
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentMap.Builder>>> ITEM_REMOVE_BUILDER_LIST = new ArrayList<>();
    @Override
    public ModLoader getModLoader() {
        return ModLoader.FABRIC;
    }

    @Override
    public <T extends Block> Supplier<T> blockRegister(String id, Supplier<T> block) {
        T blockInfo = block.get();
        ResourceLocation blockID = EternalStarlightDelight.of(id);
        Registry.register(BuiltInRegistries.BLOCK, blockID, blockInfo);
        return () -> blockInfo;
    }

    @Override
    public <T extends Item> Supplier<T> itemRegister(String id, Supplier<T> item) {
        T itemInfo = item.get();
        ResourceLocation blockID = EternalStarlightDelight.of(id);
        Registry.register(BuiltInRegistries.ITEM, blockID, itemInfo);
        return () -> itemInfo;
    }

    @Override
    public Supplier<CreativeModeTab> creativeModeTabRegister(String id, Supplier<ItemStack> stack, BiConsumer<CreativeModeTab.ItemDisplayParameters, CreativeModeTab.Output> createTab) {
        CreativeModeTab tab = ESDCreativeTabs.createBuilder(FabricItemGroup.builder(), id, stack, createTab);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), EternalStarlightDelight.of(id));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return () -> tab;
    }

    @Override
    public Holder<MobEffect> mobEffectRegister(String id, Supplier<MobEffect> effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, EternalStarlightDelight.of(id), effect.get());
    }


    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> blockEntityRegister(String id, Supplier<BlockEntityType<T>> type) {
        BlockEntityType<T> beType = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, EternalStarlightDelight.of(id), type.get());
        return () -> beType;
    }
    @Override
    @SuppressWarnings("all")
    public <T extends BlockEntity>BlockEntityType<T> getBlockEntityType(BiFunction<BlockPos, BlockState, T> factory, Set<Block> validBlocks){
        return new BlockEntityType<>(
                factory::apply,
                validBlocks,
                null
        );
    }

    @Override
    public <T> void modifyStewItemComponentEntry(Supplier<Item> item, DataComponentType<T> data, T obj) {
        STEW_ITEM_BUILDER_LIST.addLast(Map.entry(item, (builder) -> builder.set(data, obj)));
    }
    @Override
    public <T> void modifyItemComponentEntry(Supplier<Item> item, DataComponentType<T> data, T obj) {
        ITEM_BUILDER_LIST.addLast(Map.entry(item, (builder) -> builder.set(data, obj)));
    }

    @Override
    public <T> void removeItemComponentEntry(Supplier<Item> item, DataComponentType<T> data) {
        ITEM_REMOVE_BUILDER_LIST.addLast(Map.entry(item, (builder) -> builder.set(data, null)));

    }

    @Override
    public void useItemCallBack(TriFunction<Player, Level, InteractionHand, InteractionResultHolder<ItemStack>> function) {
        UseItemCallback.EVENT.register(function::apply);
    }

    @Override
    public void compostItemRegister(Item item, float value) {
        ComposterBlock.COMPOSTABLES.put(item, value);
    }

    @Override
    public InteractionResultHolder<ItemStack> deepsilverSkilletUsing(Level level, Player player, InteractionHand hand) {
        ItemStack skilletStack = player.getItemInHand(hand);
        if (SkilletItemInvoker.invokePlayerNeatHeatSourceCheck(player, level)) {
            InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            ItemStack cookingStack = player.getItemInHand(otherHand);

            if (!skilletStack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY).getStack().isEmpty()) {
                player.startUsingItem(hand);
                return InteractionResultHolder.pass(skilletStack);
            }

            Optional<RecipeHolder<CampfireCookingRecipe>> recipe = SkilletItem.getCookingRecipe(cookingStack, level);
            if (recipe.isPresent()) {
                if (player.isUnderWater()) {
                    player.displayClientMessage(TextUtils.getTranslation("item.skillet.underwater"), true);
                    return InteractionResultHolder.pass(skilletStack);
                }
                ItemStack cookingStackCopy = cookingStack.copy();
                ItemStack cookingStackUnit = cookingStackCopy.split(1);
                skilletStack.set(ModDataComponents.SKILLET_INGREDIENT.get(), new ItemStackWrapper(cookingStackUnit));
                int cookingTime = DeepSilverSkilletItem.getSkilletCookingTime(level, skilletStack, cookingStack, recipe.get().value().getCookingTime());
                skilletStack.set(ModDataComponents.COOKING_TIME_LENGTH.get(), cookingTime);
                skilletStack.set(ModDataComponents.SKILLET_FLIPPED.get(), false);
                player.startUsingItem(hand);
                player.setItemInHand(otherHand, cookingStackCopy);
                return InteractionResultHolder.consume(skilletStack);
            } else {
                player.displayClientMessage(TextUtils.getTranslation("item.skillet.how_to_cook"), true);
            }
        }
        return InteractionResultHolder.pass(skilletStack);
    }

    public static void onModifyDefaultComponents(DefaultItemComponentEvents.ModifyContext context){
        if (Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get()) {
            STEW_ITEM_BUILDER_LIST.forEach((entry) -> context.modify(entry.getKey().get(), entry.getValue()));

        }
        ITEM_BUILDER_LIST.forEach((entry) -> context.modify(entry.getKey().get(), entry.getValue()));
        ITEM_REMOVE_BUILDER_LIST.forEach((entry) ->
                context.modify(entry.getKey().get(), entry.getValue()));
    }
    @Override
    public void delayedInit(Runnable runnable){
        runnable.run();
    }

    @Override
    public BiFunction<BlockPos, BlockState, ? extends BlockEntity> instanceDeepSilverCookingPotBlockEntity() {
        return DeepSilverCookingPotBlockEntityFabric::new;
    }
    public Supplier<BlockEntityType<? extends BlockEntity>> registerDeepSilverCookingPot(String id){
        BlockEntityType<?> be = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, EternalStarlightDelight.of(id), BlockEntityType.Builder.of(DeepSilverCookingPotBlockEntityFabric::new,
                ESDBlocks.DEEPSILVER_COOKING_POT.get()).build());
        return () -> be;
    }

    @Override
    public ItemStack getMealFromItem(ItemStack cookingPotStack) {
        return cookingPotStack.getOrDefault(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY).getStack();
    }

    @Override
    public BlockEntityType<?> getDeepSilverCookingPotBlockEntity() {
        return ESDBlockEntityTypesFabric.DEEPSILVER_COOKING_POT.get();
    }

    @Override
    public Supplier<SoundEvent> soundTypeRegister(String id) {
        ResourceLocation soundId = EternalStarlightDelight.of(id);
        SoundEvent sound = Registry.register(BuiltInRegistries.SOUND_EVENT, soundId, SoundEvent.createVariableRangeEvent(soundId));
        return () -> sound;
    }
    public TagKey<Item> getWaterBucketTag(){
        return ConventionalItemTags.WATER_BUCKETS;
    }

    @Override
    public <T extends RecipeSerializer<?>> Supplier<T> recipeSerializerRegister(String id, Supplier<T> recipeSerializer) {
        T recipeInfo = recipeSerializer.get();
        ResourceLocation recipeID = EternalStarlightDelight.of(id);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeID, recipeInfo);
        return () -> recipeInfo;
    }
    @Override
    public void allowDamageEventRegister(TriFunction<LivingEntity, DamageSource, Float, Boolean> function) {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(function::apply);
    }

}
