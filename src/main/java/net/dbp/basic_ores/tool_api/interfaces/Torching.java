package net.dbp.basic_ores.tool_api.interfaces;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public interface Torching {
    public default ActionResult torch(ItemUsageContext context){
		PlayerEntity player = context.getPlayer();
		ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
		World world = context.getWorld();
		BlockPos pos = itemPlacementContext.getBlockPos();
        BlockState state = getTorchedState(itemPlacementContext);
        if (canTorch(itemPlacementContext)){
			if (context.getWorld().setBlockState(itemPlacementContext.getBlockPos(), state, 11)) {
				BlockSoundGroup blockSoundGroup = state.getSoundGroup();
				world.playSound(player, pos, blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0F) / 2.0F, blockSoundGroup.getPitch() * 0.8F);
				if (player == null || !player.getAbilities().creativeMode) {
					removeTorch(itemPlacementContext);
				}
			}
		}
        return ActionResult.SUCCESS;
    }

    public default BlockState getTorchedState(ItemPlacementContext context) {
		BlockState state = null;
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		Direction[] directions = context.getPlacementDirections();

		for (int i = 0; i < directions.length; i++) {
			Direction direction = directions[i];
			if (direction != Direction.UP) {
				BlockState state1 = direction == Direction.DOWN ? Blocks.TORCH.getPlacementState(context): Blocks.WALL_TORCH.getPlacementState(context);
				if (state1 != null && state1.canPlaceAt(world, pos)) {
					state = state1;
					break;
				}
			}
		}
		return state != null && world.canPlace(state, pos, ShapeContext.absent()) ? state : null;
	}

    public default boolean canTorch(ItemPlacementContext context){
        PlayerEntity player = context.getPlayer();
        for (int i = 0; i < player.getInventory().size(); i++) {
			if (player.getInventory().getStack(i).getItem() == Items.TORCH && getTorchedState(context) != null) {
                return true;
            }
        }
        return false;
    }

    public default void removeTorch(ItemPlacementContext context){
        PlayerEntity player = context.getPlayer();
        for (int i = 0; i < player.getInventory().size(); i++) {
			if (player.getInventory().getStack(i).getItem() == Items.TORCH && getTorchedState(context) != null) {
                player.getInventory().getStack(i).decrement(1);
            }
        }
    }
}