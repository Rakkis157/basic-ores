package net.dbp.basic_ores.mixin;

import java.util.Map;
import java.util.function.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import com.mojang.datafixers.util.Pair;

@Mixin(HoeItem.class)
public interface hoehax {
    @Accessor("TILLING_ACTIONS") static Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> getTilled() { throw new AssertionError(); }
}
