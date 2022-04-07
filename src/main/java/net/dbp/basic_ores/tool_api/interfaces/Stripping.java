package net.dbp.basic_ores.tool_api.interfaces;

import java.util.Optional;
import net.dbp.basic_ores.tool_api.mixin.AxeMixin;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public interface Stripping {
    public default ActionResult strip(ItemUsageContext context){
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> optional = this.getStrippedState(blockState);
        Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> optional3 = Optional.ofNullable((Block)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock())).map(block -> block.getStateWithProperties(blockState));
        if (optional.isPresent()) {
            return doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_STRIP);
        } else if (optional2.isPresent()) {
            world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
            return doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_SCRAPE);
        } else if (optional3.isPresent()) {
            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
            return doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_WAX_OFF);
        }
        return ActionResult.SUCCESS;
    }

    public default ActionResult doBlockstate(ItemUsageContext context, World world, PlayerEntity playerEntity, Optional<BlockState> blockState, SoundEvent sound){
        world.playSound(playerEntity, context.getBlockPos(), sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
        if (playerEntity instanceof ServerPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, context.getBlockPos(), context.getStack());
        world.setBlockState(context.getBlockPos(), (BlockState)blockState.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
        if (playerEntity != null) context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
        return ActionResult.success(world.isClient);
    }

    public default Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(AxeMixin.getStripped().get(state.getBlock())).map(block -> (BlockState)block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }
}