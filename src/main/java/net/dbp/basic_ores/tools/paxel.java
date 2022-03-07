package net.dbp.basic_ores.tools;

import java.util.Optional;
import java.util.function.*;
import net.dbp.basic_ores.mixin.*;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class paxel extends MiningToolItem{
    public paxel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state){
        if (state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.AXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE))
            return this.miningSpeed;
        else
            return 1.0f;
    }

    ActionResult doBlockstate(ItemUsageContext context, World world, PlayerEntity playerEntity, Optional<BlockState> blockState, SoundEvent sound){
        world.playSound(playerEntity, context.getBlockPos(), sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
        if (playerEntity instanceof ServerPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, context.getBlockPos(), context.getStack());
        world.setBlockState(context.getBlockPos(), (BlockState)blockState.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
        if (playerEntity != null) context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
        return ActionResult.success(world.isClient);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> optional = this.getStrippedState(blockState);
        Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> optional3 = Optional.ofNullable((Block)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock())).map(block -> block.getStateWithProperties(blockState));
        if (optional.isPresent()) {
            doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_STRIP);
        } else if (optional2.isPresent()) {
            world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
            doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_SCRAPE);
        } else if (optional3.isPresent()) {
            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
            doBlockstate(context, world, playerEntity, optional, SoundEvents.ITEM_AXE_WAX_OFF);
        }
        if (playerEntity.isSneaking()){
            Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = hoehax.getTilled().get(world.getBlockState(blockPos).getBlock());
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
        } else {
            if (context.getSide() != Direction.DOWN) {
                BlockState blockState2 = shovelhax.getPathed().get(blockState.getBlock());
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
        }
        return ActionResult.PASS;
    }

    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(axehax.getStripped().get(state.getBlock())).map(block -> (BlockState)block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }
}