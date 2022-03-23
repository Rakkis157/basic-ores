package net.dbp.basic_ores;

import com.mojang.serialization.Codec;

import net.dbp.basic_ores.Basic.GravelOreFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.Heightmap;

public class GravelOreFeature extends Feature<GravelOreFeatureConfig>{
    public GravelOreFeature(Codec<GravelOreFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<GravelOreFeatureConfig> context) {
        BlockPos topPos = context.getWorld().getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, context.getOrigin());
        Direction offset = Direction.NORTH;
     
        for (int y = 0; y < 15; y++) {
          offset = offset.rotateYClockwise();
          context.getWorld().setBlockState(topPos.up(y).offset(offset), Blocks.STONE.getDefaultState(), 3);
        }
     
        return true;
    }
}
