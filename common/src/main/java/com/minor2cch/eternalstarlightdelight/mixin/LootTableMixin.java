package com.minor2cch.eternalstarlightdelight.mixin;

import com.minor2cch.eternalstarlightdelight.ESDUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootTable.class)
public class LootTableMixin {
    @Inject(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;",
            at = @At("RETURN"), cancellable = true)
    private void onGenerateLoot(LootContext context, CallbackInfoReturnable<ObjectArrayList<ItemStack>> cir) {

        // 採掘に使ったツール

        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        if(ESDUtils.canSmeltTool(tool)){
            ObjectArrayList<ItemStack> drops = cir.getReturnValue();
            ObjectArrayList<ItemStack> newDrops = new ObjectArrayList<>();

            for (ItemStack stack : drops) {
                if (ESDUtils.canSmeltStack(context.getLevel(), stack)) {
                    newDrops.add(ESDUtils.smeltStack(context.getLevel(), stack));
                } else {
                    newDrops.add(stack);
                }
            }
            cir.setReturnValue(newDrops);
        }
    }
}
