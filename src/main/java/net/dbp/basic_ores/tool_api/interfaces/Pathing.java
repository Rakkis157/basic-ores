package net.dbp.basic_ores.tool_api.interfaces;

import net.dbp.basic_ores.tool_api.mixin.ShovelMixin;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface Pathing {
    public default ActionResult path(ItemUsageContext context){
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        if (context.getSide() != Direction.DOWN) {
            BlockState blockState2 = getPathedState(context);
            BlockState blockState3 = null;
            if (blockState2 != null && world.getBlockState(blockPos.up()).isAir()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                blockState3 = blockState2;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT).booleanValue()) {
                if (!world.isClient()) world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0);
                CampfireBlock.extinguish(context.getPlayer(), world, blockPos, blockState);
                blockState3 = (BlockState)blockState.with(CampfireBlock.LIT, false);
            }
            if (blockState3 != null) {
                if (!world.isClient) {
                    world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    if (playerEntity != null) context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                }
                return ActionResult.success(world.isClient);
            }
            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }

    public default BlockState getPathedState(ItemUsageContext context){
        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos());
        return ShovelMixin.getPathed().get(blockState.getBlock());
    }
}
