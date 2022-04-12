package net.dbp.basic_ores.tool_api.interfaces;

import java.util.function.*;
import com.mojang.datafixers.util.Pair;
import net.dbp.basic_ores.tool_api.mixin.HoeMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface Tilling {
    public default ActionResult till(ItemUsageContext context){
        World world = context.getWorld();
        PlayerEntity playerEntity = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = getTilledPair(context);
        if (pair == null) return ActionResult.PASS;
        Predicate<ItemUsageContext> predicate = pair.getFirst();
        Consumer<ItemUsageContext> consumer = pair.getSecond();
        if (predicate.test(context)) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world.isClient) {
                consumer.accept(context);
                if (playerEntity != null) context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
            }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    public default Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> getTilledPair(ItemUsageContext context){
        return HoeMixin.getTilled().get(context.getWorld().getBlockState(context.getBlockPos()).getBlock());
    }
}
