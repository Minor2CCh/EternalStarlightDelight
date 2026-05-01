package com.minor2cch.eternalstarlightdelight.mixin;

import cn.leolezury.eternalstarlight.common.entity.projectile.ThrownBoomerang;
import com.minor2cch.eternalstarlightdelight.config.ESDConfigLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {
    @Inject(at = @At("HEAD"), method = "onHitBlock")
    private void useCuttingBoard(BlockHitResult blockHitResult, CallbackInfo ci){
        if(ESDConfigLoader.getConfig().getBoomerangUsableKnife()){
            if(((Object)(this)) instanceof ThrownBoomerang boomerang){
                ItemStack stack = boomerang.getPickupItemStackOrigin();
                BlockPos pos = blockHitResult.getBlockPos();
                Level level = boomerang.level();
                BlockEntity be = level.getBlockEntity(pos);
                Block block = level.getBlockState(pos).getBlock();
                BlockState state = level.getBlockState(pos);
                if(!level.isClientSide()){
                    if (be instanceof CuttingBoardBlockEntity cuttingBoardEntity) {
                        if (stack != null && !stack.isEmpty() && (stack.getDamageValue() < stack.getMaxDamage() - 1 || !stack.isDamageableItem())) {  //耐久値2以上
                            ItemStack boardStack = cuttingBoardEntity.getStoredItem().copy();
                            if(!boardStack.isEmpty()){
                                if(boomerang.getOwner() instanceof Player player){
                                    if (cuttingBoardEntity.processStoredItemUsingTool(stack, player)) {
                                        cuttingBoardEntity.spawnCuttingParticles((ServerLevel) level, pos, boardStack);
                                    }
                                }
                            }
                        }
                    } else if(block instanceof PieBlock pieBlock){
                        int bites = state.getValue(PieBlock.BITES);
                        if (bites < pieBlock.getMaxBites() - 1) {
                            level.setBlock(pos, state.setValue(PieBlock.BITES, bites + 1), 3);
                        } else {
                            level.removeBlock(pos, false);
                        }

                        Direction direction = boomerang.getDirection();
                        Direction direction2 = direction.getAxis() == Direction.Axis.Y ? boomerang.getDirection().getOpposite() : direction;
                        ItemUtils.spawnItemEntity(level, pieBlock.getPieSliceItem(), pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5,
                                direction2.getStepX() * 0.15, 0.05, direction2.getStepZ() * 0.15);
                        level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
                        if (level instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 3, 0.1, 0.1, 0.1, 0.001D);
                        }
                    } else if (state.is(ModTags.Blocks.DROPS_CAKE_SLICE)) {
                        level.setBlock(pos, Blocks.CAKE.defaultBlockState().setValue(CakeBlock.BITES, 1), 3);
                        Block.dropResources(state, level, pos);
                        ItemUtils.spawnItemEntity(level, new ItemStack(ModItems.CAKE_SLICE.get()),
                                pos.getX(), pos.getY() + 0.2, pos.getZ() + 0.5,
                                -0.05, 0, 0);
                        level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
                    } else if (block == Blocks.CAKE) {
                        int bites = state.getValue(CakeBlock.BITES);
                        if (bites < 6) {
                            level.setBlock(pos, state.setValue(CakeBlock.BITES, bites + 1), 3);
                        } else {
                            level.removeBlock(pos, false);
                        }
                        ItemUtils.spawnItemEntity(level, new ItemStack(ModItems.CAKE_SLICE.get()),
                                pos.getX() + (bites * 0.1), pos.getY() + 0.2, pos.getZ() + 0.5,
                                -0.05, 0, 0);
                        level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
                    }
                }
            }

        }
    }
}
