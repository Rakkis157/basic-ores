package net.fabricmc.example;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.JTag;

import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.GenerationStep;

import org.lwjgl.system.CallbackI.J;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create("basic-ores:resource-pack");
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String modid = "basic-ores";
	public static final String[] basicoreitems = {"ingot", "nugget"};
	public static final String[] vanillaoreitems = {"plate", "gear", "dust"};
	public static final String[] basicoreblocks = {"ore", "block"};
	public static final String[] basicoretags = {"ingot_to_nugget", "ore_to_ingot"};
	public static final String[] largetools = {"hammer", "excavator"};
	public static final String[] tools = {"axe", "hoe", "pickaxe", "shovel", "sword", "shears"};
	public static final HashSet<String> shears = new HashSet<>();

	@Override
	public void onInitialize() {
		Mat nickel = new Mat("nickel").addItemParts(vanillaoreitems, basicoreitems).addBlockPart(basicoreblocks).addTag(basicoretags);
		Mat tin = new Mat("tin").addItemParts(vanillaoreitems, basicoreitems, tools).addTag(basicoretags).addBlockPart(basicoreblocks);
		Mat tungsten = new Mat("tungsten").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart(basicoreblocks);
		Mat titanium = new Mat("titanium").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart(basicoreblocks);
		Mat ruby = new Mat("ruby").addItemPart("gem", "dust").addBlockPart(basicoreblocks);
		Mat sapphire = new Mat("sapphire").addItemPart("gem", "dust").addBlockPart(basicoreblocks);
		Mat galena = new Mat("galena").addBlockPart("ore");
		Mat lead = new Mat("lead").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat silver = new Mat("silver").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat platinum = new Mat("platinum").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat gold = new Mat("gold").addItemParts(vanillaoreitems);
		Mat copper = new Mat("copper").addItemParts(vanillaoreitems);
		Mat iron = new Mat("iron").addItemParts(vanillaoreitems, largetools);
		Mat bronze = new Mat("bronze").addItemParts(vanillaoreitems, basicoreitems, tools, largetools).addTag(basicoretags).addBlockPart("block");
		Mat brass = new Mat("brass").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat wroughtiron = new Mat("wroughtiron").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat cobalt = new Mat("cobalt").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat chromium = new Mat("chromium").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat invar = new Mat("invar").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat electrum = new Mat("electrum").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat aluminium = new Mat("aluminium").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat steel = new Mat("steel").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat tungstensteel = new Mat("tungstensteel").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat zinc = new Mat("zinc").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat osmium = new Mat("osmium").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		Mat iridium = new Mat("iridium").addItemParts(vanillaoreitems, basicoreitems).addTag(basicoretags).addBlockPart("block");
		
		registerMat(nickel);
		registerMat(tin);
		registerMat(tungsten);
		registerMat(titanium);
		registerMat(ruby);
		registerMat(sapphire);
		registerMat(galena);
		registerMat(lead);
		registerMat(silver);
		registerMat(platinum);
		registerMat(gold);
		registerMat(copper);
		registerMat(iron);
		registerMat(bronze);
		registerMat(brass);
		registerMat(wroughtiron);
		registerMat(cobalt);
		registerMat(chromium);
		registerMat(invar);
		registerMat(electrum);
		registerMat(aluminium);
		registerMat(steel);
		registerMat(tungstensteel);
		registerMat(zinc);
		registerMat(osmium);
		registerMat(iridium);

		registerOre("nickel_ore_overworld", BiomeSelectors.foundInOverworld(), OreConfiguredFeatures.STONE_ORE_REPLACEABLES, nickel.blockPartsBlocks.get("ore"), 9, 20, -12, 64);
		registerOre("tin_ore_nether", BiomeSelectors.foundInTheNether(), OreConfiguredFeatures.NETHERRACK, tin.blockPartsBlocks.get("ore"), 9, 40, 10, 112);
		addTags(shears, "shears", "fabric:items/");
	}

	public void registerMat(Mat mat){
		for (Map.Entry<String, Item> set : mat.itemParts.entrySet()) {
			registerItem(set.getValue(), set.getKey()+"_"+mat.name);
		}

		for (Map.Entry<String, Item> set : mat.blockPartsItems.entrySet()) {
			registerBlock(set.getValue(), mat.blockPartsBlocks.get(set.getKey()), set.getKey()+"_"+mat.name);
		}

		if (mat.tags.contains("ingot_to_nugget")){
			RESOURCE_PACK.addRecipe(new Identifier("arrp", mat.name+"_ingot_to_nugget"), JRecipe.shapeless(JIngredients.ingredients().add(JIngredient.ingredient().item(mat.itemParts.get("ingot"))), JResult.itemStack(mat.itemParts.get("nugget"), 9)));
		}

		if (mat.tags.contains("ore_to_ingot")){
			RESOURCE_PACK.addRecipe(new Identifier("arrp", mat.name+"_ore_to_ingot"), JRecipe.smelting(JIngredient.ingredient().item(mat.blockPartsItems.get("ore")), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
		}

		if (mat.itemParts.containsKey("shears")){
			shears.add(mat.name);
		}
	}

	public void addTags(HashSet<String> hashset, String tag, String namespace){
		JTag jtag = new JTag();
		for (String string : hashset){
			jtag.add(new Identifier(modid+":"+tag+"_"+string));
		}

		RESOURCE_PACK.addTag(new Identifier(namespace+tag), jtag);
	}

	public void registerOre(String name, Predicate<BiomeSelectionContext> predicate, RuleTest replace, Block block, Integer vein_size, Integer veins_per_chunk, Integer min_height, Integer max_height){
		ConfiguredFeature<?, ?> ORE_FEATURE = Feature.ORE.configure(new OreFeatureConfig(replace,block.getDefaultState(), vein_size));
		PlacedFeature ORE_FEATURE_PLACED = ORE_FEATURE.withPlacement(CountPlacementModifier.of(veins_per_chunk), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(min_height), YOffset.fixed(max_height)));	
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,	new Identifier(modid, name), ORE_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(modid, name), ORE_FEATURE_PLACED);
		BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(modid, name)));
	}

	public void registerItem(Item item, String name){
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
		RESOURCE_PACK.addModel(JModel.model("item/generated").textures(new JTextures().layer0(modid+":item/"+name)), new Identifier(modid, "item/"+name));
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
	}

	public void registerBlock(Item item, Block block, String name){
		Registry.register(Registry.BLOCK, new Identifier(modid, name), block);
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
		RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(new Identifier(modid+":block/"+name)))), new Identifier(modid, name));
		RESOURCE_PACK.addModel(JModel.model().parent("block/cube_all").textures(new JTextures().var("all",modid+":block/"+name)), new Identifier(modid, "block/"+name));
		RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/"+name), new Identifier(modid, "item/"+name));
	}
}
