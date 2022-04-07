package net.dbp.basic_ores.ore_api;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;

public class StoneSelection {
    public static final RuleTest STONE = OreConfiguredFeatures.STONE_ORE_REPLACEABLES;
	public static final RuleTest DEEPSLATE = OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES;
	public static final RuleTest NETHERRACK = OreConfiguredFeatures.NETHERRACK;
	public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);
}
