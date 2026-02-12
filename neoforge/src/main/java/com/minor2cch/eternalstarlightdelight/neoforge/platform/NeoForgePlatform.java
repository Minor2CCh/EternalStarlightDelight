package com.minor2cch.eternalstarlightdelight.neoforge.platform;

import com.minor2cch.eternalstarlightdelight.EternalStarlightDelight;
import com.minor2cch.eternalstarlightdelight.item.DeepSilverSkilletItem;
import com.minor2cch.eternalstarlightdelight.mixin.SkilletItemInvoker;
import com.minor2cch.eternalstarlightdelight.neoforge.block.entity.DeepSilverCookingPotBlockEntityNeoForge;
import com.minor2cch.eternalstarlightdelight.neoforge.mixin.CookingPotBlockEntityAccessor;
import com.minor2cch.eternalstarlightdelight.neoforge.registry.ESDBlockEntityTypesNeoForge;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlocks;
import com.minor2cch.eternalstarlightdelight.registry.ESDCreativeTabs;
import com.google.auto.service.AutoService;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
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

@EventBusSubscriber(modid = EternalStarlightDelight.MOD_ID)
@AutoService(ESDPlatform.class)
public class NeoForgePlatform implements ESDPlatform {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, EternalStarlightDelight.MOD_ID);
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, EternalStarlightDelight.MOD_ID);
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentPatch.Builder>>> ITEM_BUILDER_LIST = new ArrayList<>();
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentPatch.Builder>>> STEW_ITEM_BUILDER_LIST = new ArrayList<>();
    private static final List<Map.Entry<Supplier<Item>, Consumer<DataComponentPatch.Builder>>> ITEM_REMOVE_BUILDER_LIST = new ArrayList<>();
    private static final List<TriFunction<Player, Level, InteractionHand, InteractionResultHolder<ItemStack>>> ITEM_USE_FUNCTIONS = new ArrayList<>();
    private static final List<TriFunction<LivingEntity, DamageSource, Float, Boolean>> ALLOW_DAMAGE_FUNCTIONS = new ArrayList<>();
    public static final HashMap<Item, Float> COMPOST_TRUE_MAP = new HashMap<>();
    private static final List<Runnable> DELAY_RUNNABLE_LIST = new ArrayList<>();

    @Override
    public ModLoader getModLoader() {
        return ModLoader.NEOFORGE;
    }

    @Override
    public <T extends Block> Supplier<T> blockRegister(String id, Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    @Override
    public <T extends Item> Supplier<T> itemRegister(String id, Supplier<T> item) {
        return ITEMS.register(id, item);
    }

    @Override
    public Supplier<CreativeModeTab> creativeModeTabRegister(String id, Supplier<ItemStack> stack, BiConsumer<CreativeModeTab.ItemDisplayParameters, CreativeModeTab.Output> createTab) {
        return CREATIVE_MODE_TABS.register(id, () -> ESDCreativeTabs.createBuilder(CreativeModeTab.builder(), id, stack, createTab));
    }

    @Override
    public Holder<MobEffect> mobEffectRegister(String id, Supplier<MobEffect> effect) {
        return MOB_EFFECTS.register(id, effect);
    }


    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    @Override
    public <T extends BlockEntity>Supplier<BlockEntityType<T>> blockEntityRegister(String id, Supplier<BlockEntityType<T>> type) {
        return BLOCK_ENTITIES.register(id, type);
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
        ITEM_REMOVE_BUILDER_LIST.addLast(Map.entry(item, (builder) -> builder.remove(data)));

    }

    @Override
    public void useItemCallBack(TriFunction<Player, Level, InteractionHand, InteractionResultHolder<ItemStack>> function) {
        ITEM_USE_FUNCTIONS.add(function);
    }

    @Override
    public void compostItemRegister(Item item, float value) {
        COMPOST_TRUE_MAP.put(item, value);
    }

    @Override
    public InteractionResultHolder<ItemStack> deepsilverSkilletUsing(Level level, Player player, InteractionHand hand) {
        ItemStack skilletStack = player.getItemInHand(hand);
        if (SkilletItemInvoker.invokePlayerNeatHeatSourceCheck(player, level)) {
            InteractionHand otherHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
            ItemStack cookingStack = player.getItemInHand(otherHand);

            if (!skilletStack.getOrDefault(ModDataComponents.SKILLET_INGREDIENT, ItemStackWrapper.EMPTY).getStack().isEmpty()) {
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
                skilletStack.set(ModDataComponents.SKILLET_INGREDIENT, new ItemStackWrapper(cookingStackUnit));
                int cookingTime = DeepSilverSkilletItem.getSkilletCookingTime(level, skilletStack, cookingStack, recipe.get().value().getCookingTime());
                skilletStack.set(ModDataComponents.COOKING_TIME_LENGTH, cookingTime);
                player.startUsingItem(hand);
                player.setItemInHand(otherHand, cookingStackCopy);
                return InteractionResultHolder.consume(skilletStack);
            } else {
                player.displayClientMessage(TextUtils.getTranslation("item.skillet.how_to_cook"), true);
            }
        }
        return InteractionResultHolder.pass(skilletStack);
    }

    public static void registryInit(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
    }
    @SubscribeEvent
    public static void onModifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        if (DatagenModLoader.isRunningDataGen()) {
            return;
        }
        if (Configuration.ENABLE_STACKABLE_SOUP_ITEMS.get()) {
            STEW_ITEM_BUILDER_LIST.forEach((entry) ->
                    event.modify(entry.getKey().get(), entry.getValue()));

        }
        ITEM_BUILDER_LIST.forEach((entry) ->
                event.modify(entry.getKey().get(), entry.getValue()));
        ITEM_REMOVE_BUILDER_LIST.forEach((entry) ->
                event.modify(entry.getKey().get(), entry.getValue()));
    }
    @SubscribeEvent
    public static void rightClickEvent(PlayerInteractEvent.RightClickItem event){
        for(TriFunction<Player, Level, InteractionHand, InteractionResultHolder<ItemStack>> function : ITEM_USE_FUNCTIONS){
            InteractionResultHolder<ItemStack> result = function.apply(event.getEntity(), event.getLevel(),event.getHand());
            if(result.getResult() == InteractionResult.SUCCESS || result.getResult() == InteractionResult.CONSUME){
                event.setCanceled(true);
                event.setCancellationResult(result.getResult());
                break;
            }
        }

    }
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DELAY_RUNNABLE_LIST.forEach(Runnable::run);
        });
    }
    @Override
    public void delayedInit(Runnable runnable){
        DELAY_RUNNABLE_LIST.add(runnable);
    }
    @Override
    public BiFunction<BlockPos, BlockState, ? extends BlockEntity> instanceDeepSilverCookingPotBlockEntity() {
        return DeepSilverCookingPotBlockEntityNeoForge::new;
    }
    @SuppressWarnings("all")
    @Override
    public Supplier<BlockEntityType<? extends BlockEntity>> registerDeepSilverCookingPot(String id) {
        return BLOCK_ENTITIES.register(id, () -> BlockEntityType.Builder.of(DeepSilverCookingPotBlockEntityNeoForge::new,
                ESDBlocks.DEEPSILVER_COOKING_POT.get()).build(null));
    }

    @Override
    public ItemStack getMealFromItem(ItemStack cookingPotStack) {
        return cookingPotStack.getOrDefault(ModDataComponents.MEAL.get(), ItemStackWrapper.EMPTY).getStack();
    }

    @Override
    public BlockEntityType<?> getDeepSilverCookingPotBlockEntity() {
        return ESDBlockEntityTypesNeoForge.DEEPSILVER_COOKING_POT.get();
    }

    @Override
    public Supplier<SoundEvent> soundTypeRegister(String id) {
        ResourceLocation soundId = EternalStarlightDelight.of(id);
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(soundId));
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ESDBlockEntityTypesNeoForge.DEEPSILVER_COOKING_POT.get(),
                (be, context) -> {
                    if (context == Direction.UP) {
                        return ((CookingPotBlockEntityAccessor)be).getInputHandler();
                    }
                    return ((CookingPotBlockEntityAccessor)be).getOutputHandler();
                }
        );
    }
    public TagKey<Item> getWaterBucketTag(){
        return Tags.Items.BUCKETS_WATER;
    }

    @Override
    public <T extends RecipeSerializer<?>> Supplier<T> recipeSerializerRegister(String id, Supplier<T> recipeSerializer) {
        return RECIPE_SERIALIZERS.register(id, recipeSerializer);
    }
    @Override
    public void allowDamageEventRegister(TriFunction<LivingEntity, DamageSource, Float, Boolean> function) {
        ALLOW_DAMAGE_FUNCTIONS.add(function);
    }
    @SubscribeEvent
    public static void allowDamageEvent(LivingIncomingDamageEvent event){
        for(TriFunction<LivingEntity, DamageSource, Float, Boolean> function : ALLOW_DAMAGE_FUNCTIONS){
            boolean result = function.apply(event.getEntity(), event.getSource(), event.getAmount());
            if(!result){
                event.setAmount(0);
                event.setCanceled(true);
                break;
            }
        }
    }
}
