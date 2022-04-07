package net.dbp.basic_ores.tool_api.mixin;

import java.util.Map;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.block.*;
import net.minecraft.item.ShovelItem;

@Mixin(ShovelItem.class)
public interface ShovelMixin {
    @Accessor("PATH_STATES") static Map<Block, BlockState> getPathed() { throw new AssertionError(); }
}
