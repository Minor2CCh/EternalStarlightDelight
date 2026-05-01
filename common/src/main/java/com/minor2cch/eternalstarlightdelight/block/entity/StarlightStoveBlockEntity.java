package com.minor2cch.eternalstarlightdelight.block.entity;

import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import com.minor2cch.eternalstarlightdelight.block.StarlightStoveBlock;
import com.minor2cch.eternalstarlightdelight.platform.ESDPlatform;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.AbstractStoveBlockEntity;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

public class StarlightStoveBlockEntity extends AbstractStoveBlockEntity {
    public StarlightStoveBlockEntity(BlockPos pos, BlockState state) {
        super(ESDBlockEntityTypes.STARLIGHT_STOVE.get(), pos, state, RecipeType.CAMPFIRE_COOKING);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ESDBlockEntityTypes.STARLIGHT_STOVE.get();
    }
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) {
        return this.getType().isValid(blockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, StarlightStoveBlockEntity stove) {
        StoveBlockEntity.serverTick(level, pos, state, stove);
        if (state.getValue(BlockStateProperties.LIT)) {
            AABB box = AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(pos)).inflate(10);
            for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class, box)) {
                if (!(living instanceof Enemy) && !living.hasEffect(MobEffects.REGENERATION)) {
                    living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100));
                }
            }
            if (state.getValue(StarlightStoveBlock.STARFIRE)) {
                for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class, box)) {
                    if (living instanceof Enemy && !living.hasEffect(ESMobEffects.STARFIRE.asHolder())) {
                        living.addEffect(new MobEffectInstance(ESMobEffects.STARFIRE.asHolder(), 100));
                    }
                }
            }
        } else {
            if (state.getValue(StarlightStoveBlock.STARFIRE)) {
                level.setBlockAndUpdate(pos, state.setValue(StarlightStoveBlock.STARFIRE, false));
            }
        }

    }
    @SuppressWarnings("unused")
    public static void particleTick(Level level, BlockPos pos, BlockState state, StarlightStoveBlockEntity stoveEntity) {
        if (stoveEntity.isEmpty()) return;
        ESDPlatform.INSTANCE.smokeParticles(stoveEntity);
    }

    @Override
    protected int getInventorySlotCount() {
        return 6;
    }

    @Override
    public Vec2 getStoveItemOffset(int index) {
        final float X_OFFSET = 0.3F;
        final float Y_OFFSET = 0.2F;
        final Vec2[] OFFSETS = {
                new Vec2(X_OFFSET, Y_OFFSET),
                new Vec2(0.0F, Y_OFFSET),
                new Vec2(-X_OFFSET, Y_OFFSET),
                new Vec2(X_OFFSET, -Y_OFFSET),
                new Vec2(0.0F, -Y_OFFSET),
                new Vec2(-X_OFFSET, -Y_OFFSET),
        };
        return OFFSETS[index];
    }
}
