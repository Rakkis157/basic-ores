package net.dbp.basic_ores.tool_api;

import java.util.Optional;
import net.dbp.basic_ores.tool_api.interfaces.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class Paxel extends MiningToolItem implements Tilling, Pathing, Stripping, Torching{
    public Paxel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state){
        if (state.isIn(BlockTags.PICKAXE_MINEABLE) || state.isIn(BlockTags.AXE_MINEABLE) || state.isIn(BlockTags.SHOVEL_MINEABLE))
            return this.miningSpeed;
        else
            return 1.0f;
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
        if (!playerEntity.isSneaking() && (optional.isPresent() || optional2.isPresent() || optional3.isPresent())){
            return strip(context);
        } else if (playerEntity.isSneaking() && (getTilledPair(context) != null)){
            return till(context);
        } else if (getPathedState(context) != null){
            return path(context);
        } else if (canTorch(new ItemPlacementContext(context))){
            return torch(context);
        }
        return ActionResult.PASS;
    }
}