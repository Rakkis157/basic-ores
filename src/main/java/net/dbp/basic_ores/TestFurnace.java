package net.dbp.basic_ores;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestFurnace extends AbstractFurnaceBlock{
    protected TestFurnace(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestFurnaceBlockEntity(pos, state);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TestFurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            // Optional: increment player's stat
            // player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
}