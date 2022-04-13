package net.dbp.basic_ores.tool_api.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;

@Mixin(AxeItem.class)
public interface AxeMixin {
    @Accessor("STRIPPED_BLOCKS") static Map<Block, Block> getStripped() { throw new AssertionError(); }
}
