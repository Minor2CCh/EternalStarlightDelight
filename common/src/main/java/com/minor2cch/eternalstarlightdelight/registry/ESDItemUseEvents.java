package com.minor2cch.eternalstarlightdelight.registry;

import cn.leolezury.eternalstarlight.common.registry.ESFluids;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class ESDItemUseEvents {
    private ESDItemUseEvents() {}
    public static void init() {
        ESDPlatform.INSTANCE.useItemCallBack((player, level, hand) -> {
            Vec3 vec3 = player.getEyePosition();
            Vec3 vec32 = vec3.add(player.calculateViewVector(player.getXRot(), player.getYRot()).scale(player.blockInteractionRange()));
            BlockHitResult blockHitResult = level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, ClipContext.Fluid.SOURCE_ONLY, player));
            ItemStack stack = player.getItemInHand(hand);
            if(stack.getItem() == Items.GLASS_BOTTLE){
                if (blockHitResult.getType() == HitResult.Type.MISS) {
                    return InteractionResultHolder.pass(stack);
                } else {
                    if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                        BlockPos blockPos = blockHitResult.getBlockPos();
                        if (!level.mayInteract(player, blockPos)) {
                            return InteractionResultHolder.pass(stack);
                        }

                        if (level.getFluidState(blockPos).is(ESFluids.ETHER_STILL.get())) {
                            level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                            level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                            player.awardStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));
                            ItemStack stack2 = ItemUtils.createFilledResult(stack, player, new ItemStack(ESDItems.ETHER_BOTTLE.get()));
                            player.setItemInHand(hand, stack2);
                            return InteractionResultHolder.sidedSuccess(
                                    stack2, level.isClientSide());
                        }
                    }
                }
            }

            return InteractionResultHolder.pass(stack);
        });
    }
}
