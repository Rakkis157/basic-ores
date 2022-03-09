package net.dbp.basic_ores;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class TestFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    //Since we already now the BlockEntityType and RecipeType we don't need them in the constructor's parameters
    public TestFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(Main.TEST_FURNACE_BLOCK_ENTITY, pos, state, Main.TEST_RECIPE_TYPE);
    }
 
    @Override
    public Text getContainerName() {
        //you should use a translation key instead but this is easier
        return Text.of("test furnace");
    }
 
    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new TestFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected int getFuelTime(ItemStack fuel) {
        return 100;
    }
}