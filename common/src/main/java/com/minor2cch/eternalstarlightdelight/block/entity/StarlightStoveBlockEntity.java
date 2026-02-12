package com.minor2cch.eternalstarlightdelight.block.entity;

import cn.leolezury.eternalstarlight.common.registry.ESMobEffects;
import com.minor2cch.eternalstarlightdelight.block.StarlightStoveBlock;
import com.minor2cch.eternalstarlightdelight.registry.ESDBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;

public class StarlightStoveBlockEntity extends StoveBlockEntity {
    public StarlightStoveBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ESDBlockEntityTypes.STARLIGHT_STOVE.get();
    }
    @Override
    public boolean isValidBlockState(@NotNull BlockState blockState) {
        return this.getType().isValid(blockState);
    }

    public static void cookingTick(Level level, BlockPos pos, BlockState state, StoveBlockEntity stove) {
        StoveBlockEntity.cookingTick(level, pos, state, stove);
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
}
