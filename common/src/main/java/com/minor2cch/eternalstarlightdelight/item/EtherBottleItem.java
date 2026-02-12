package com.minor2cch.eternalstarlightdelight.item;

import cn.leolezury.eternalstarlight.common.registry.ESBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class EtherBottleItem extends ESDDrinkableItem {
    public EtherBottleItem(Properties properties, boolean hasPotionEffectTooltip, boolean hasCustomTooltip) {
        super(properties, hasPotionEffectTooltip, hasCustomTooltip);
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        Vec3 vec3 = player.getEyePosition();
        Vec3 vec32 = vec3.add(player.calculateViewVector(player.getXRot(), player.getYRot()).scale(player.blockInteractionRange()));
        BlockHitResult blockHitResult = level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, ClipContext.Fluid.SOURCE_ONLY, player));
        ItemStack stack = player.getItemInHand(hand);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return super.use(level, player, hand);
        } else {
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (!level.mayInteract(player, blockPos)) {
                    return super.use(level, player, hand);
                }

                if ((level.getFluidState(blockPos).is(Fluids.WATER) || level.getFluidState(blockPos).is(Fluids.FLOWING_WATER) || level.getFluidState(blockPos).is(Fluids.LAVA) || level.getFluidState(blockPos).is(Fluids.FLOWING_LAVA)) && level.getBlockState(blockPos).canBeReplaced()) {
                    if(level.getFluidState(blockPos).is(Fluids.WATER) || level.getFluidState(blockPos).is(Fluids.FLOWING_WATER)){
                        level.setBlock(blockPos, ESBlocks.THIOQUARTZ_BLOCK.get().defaultBlockState(), 3);
                    }else{
                        level.setBlock(blockPos, ESBlocks.MOLTEN_STELLAGMITE.get().defaultBlockState(), 3);
                    }
                    for (int l = 0; l < 8; l++) {
                        level.addParticle(ParticleTypes.LARGE_SMOKE, blockPos.getX() + Math.random(), blockPos.getY() + 1.2, blockPos.getZ() + Math.random(), 0.0, 0.0, 0.0);
                    }
                    level.playSound(null, blockPos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    RandomSource randomSource = player.getRandom();
                    level.playSound(null, blockPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (randomSource.nextFloat() - randomSource.nextFloat()) * 0.8F);
                    level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    ItemStack stack2 = ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE));
                    return InteractionResultHolder.sidedSuccess(
                            stack2, level.isClientSide());
                }
            }
        }

        return super.use(level, player, hand);
    }
    @Override
    public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
    }
}
