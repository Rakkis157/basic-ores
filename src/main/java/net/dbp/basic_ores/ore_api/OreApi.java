package net.dbp.basic_ores.ore_api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import io.github.feltmc.feltapi.api.ore_feature.v1.OreFeatures;
import net.dbp.basic_ores.Basic;
import net.dbp.basic_ores.BasicJson;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.World;
import net.minecraft.world.gen.*;

public class OreApi {
	public static HashMap<String, StoneType> stoneTypes = new HashMap<>();
	public static HashMap<String, OreType> oreTypes = new HashMap<>();
	public static HashMap<String, OreGeneration> oreGenerations = new HashMap<>();

	public static void registerOreApi(){
		registerStoneType("stone", Blocks.STONE);
		registerStoneType("sandstone", Blocks.SANDSTONE);
		registerStoneType("deepslate", Blocks.DEEPSLATE);
		registerStoneType("netherrack", Blocks.NETHERRACK);
		registerStoneType("endstone", Blocks.END_STONE);
		registerStoneType("gravel", Blocks.GRAVEL);
		registerStoneType("tuff", Blocks.TUFF);
		registerStoneType("sand", Blocks.SAND);
		registerStoneType("granite", Blocks.GRANITE);
		registerStoneType("diorite", Blocks.DIORITE);
		registerStoneType("andesite", Blocks.ANDESITE);
		registerStoneType("basalt", Blocks.BASALT);
		registerStoneType("smooth_basalt", Blocks.SMOOTH_BASALT);
		registerStoneType("calcite", Blocks.CALCITE);
		registerOre("copper", Items.RAW_COPPER, 0xc78621);
		registerGeneration("coppertest", "copper", -32, 32, 60, 20, 0.0f);
		registerOreBlocks();
		registerOreGenerations();
	}

	public static void registerOreBlocks(){
		for (Map.Entry<String, OreType> oretype : oreTypes.entrySet()){
			for (Map.Entry<String, StoneType> blocktype : stoneTypes.entrySet()){
				Block block = new Block(Block.Settings.of(Material.STONE).strength(4.0f));
				BlockItem item = new BlockItem(block, new Item.Settings());
				Basic.registerBlock(item, block, blocktype.getKey()+"_"+oretype.getKey());
				BasicJson.registerBlockModel(blocktype.getKey()+"_"+oretype.getKey(), Basic.modid+":block/ore", blocktype.getValue().textureLocation, "ore");
				ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> oretype.getValue().color, block);
				ColorProviderRegistry.ITEM.register((stack, tintIndex) -> oretype.getValue().color, item);
				BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
				oretype.getValue().oreBlocks.put(blocktype.getKey(), block);
				oretype.getValue().oreItems.put(blocktype.getKey(), item);
			}
		}
	}

    public static void registerOre(Identifier identifier, Predicate<BiomeSelectionContext> predicate, RuleTest replace, Block block, Integer vein_size, Integer veins_per_chunk, Integer min_height, Integer max_height){
		ConfiguredFeature<?, ?> ORE_FEATURE = new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(replace, block.getDefaultState(), vein_size));
		PlacedFeature ORE_FEATURE_PLACED = new PlacedFeature(RegistryEntry.of(ORE_FEATURE), Arrays.asList(CountPlacementModifier.of(veins_per_chunk), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(min_height), YOffset.fixed(max_height))));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,	identifier, ORE_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier, ORE_FEATURE_PLACED);
		BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, identifier));
	}

	public static void registerOreGenerations(){
		for (Map.Entry<String, OreGeneration> oregeneration : oreGenerations.entrySet()){
			OreFeatures.createOrePlacedFeature(Basic.modid, oregeneration.getKey(), (block, r) -> {
				BlockState ore = null;
				for (Map.Entry<String, StoneType> stonetype : stoneTypes.entrySet()){
					if (block == stonetype.getValue().block.getDefaultState()){
						ore = oregeneration.getValue().oretype.oreBlocks.get(stonetype.getKey()).getDefaultState();
					}
				}

        	    if (ore == null) return null;
        	    //return r.nextBoolean() ? ore : ore2;
				return ore;
        	}, oregeneration.getValue().min, oregeneration.getValue().max, 60, 20, 0.0f, List.of(World.OVERWORLD));
		}
	}

	public static void registerStoneType(String name, Block block, String texture){
		stoneTypes.put(name, new StoneType(name, block, texture));
	}

	public static void registerStoneType(String name, Block block){
		registerStoneType(name, block, "minecraft:block/"+name);
	}

	public static void registerOre(String name, Item item, int color){
		oreTypes.put(name, new OreType(name, item, color));
	}

	public static void registerGeneration(String name, String oretype, int min, int max, int weight, int size, float discard){
		oreGenerations.put(name, new OreGeneration(name, oreTypes.get(oretype), min, max, weight, size, discard));
	}
}
