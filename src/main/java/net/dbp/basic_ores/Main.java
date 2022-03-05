package net.dbp.basic_ores;

import net.devtech.arrp.api.*;
import net.devtech.arrp.json.blockstate.*;
import net.devtech.arrp.json.models.*;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.*;
import net.devtech.arrp.json.loot.JEntry;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.loot.JPool;
import net.devtech.arrp.json.loot.JLootTable.*;

import java.util.*;
import java.util.function.Predicate;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.*;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unimi.dsi.fastutil.Hash;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

public class Main implements ModInitializer {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create("basic-ores:resource-pack");
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String modid = "basic-ores";
	public static final boolean babylonCompat = false;
	public static final String[] vanillametal = {"ingot", "nugget", "raw_ore"};
	public static final String[] modmetal = {"plate", "gear", "dust"};
	public static final String[] metal = ArrayUtils.addAll(vanillametal, modmetal);
	public static final String[] gem = {"gem", "dust"};
	public static final String[] vanillablocks = {"ore", "block"};
	public static final String[] vanillatools = {"axe", "hoe", "pickaxe", "shovel", "sword"};
	public static final String[] modtools = {"shear", "shield", "bow", "fishingrod", "hammer", "excavator"};
	public static final String[] gobtools = {"dagger", "spear", "broadsword", "rapier", "haladie", "waraxe", "katana", "boomerang"};
	public static String[] tools = ArrayUtils.addAll(vanillatools, modtools);
	public static final String[] armor = {"helmet", "chestplate", "legging", "boot"};
	public static final HashSet<String> shears = new HashSet<>();
	public static final HashSet<String> mattags = new HashSet<>();
	public static final HashSet<String> pickblocks = new HashSet<>();

	@Override
	public void onInitialize() {
		if (babylonCompat){
			tools = ArrayUtils.addAll(vanillatools, ArrayUtils.addAll(modtools, gobtools));
		}
		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3495eb, Items.IRON_INGOT);
		AutoConfig.register(BasicConfig.class, Toml4jConfigSerializer::new);
		Mat nickel = new Mat("nickel", 0x464D19).setMagicNumber(2, 2).addItemParts(metal, tools, armor).addBlockPart(vanillablocks);
		Mat tin = new Mat("tin", 0xE0E0FF).addItemParts(metal).addBlockPart(vanillablocks);
		Mat tungsten = new Mat("tungsten", 0x181b07).addItemParts(metal).addBlockPart(vanillablocks);
		Mat titanium = new Mat("titanium", 0xc4b4ed).addItemParts(metal).addBlockPart(vanillablocks);
		Mat ruby = new Mat("ruby", 0xbb4a1d).setMagicNumber(2, 3).addItemParts(gem, tools, armor).addBlockPart(vanillablocks);
		Mat sapphire = new Mat("sapphire", 0x1b55b8).setMagicNumber(2, 3).addItemParts(gem, tools, armor).addBlockPart(vanillablocks);
		Mat peridot = new Mat("peridot", 0x08dd7b).setMagicNumber(3, 3).addItemParts(gem, tools, armor).addBlockPart(vanillablocks);
		Mat galena = new Mat("galena").addBlockPart("ore");
		Mat nikolite = new Mat("nikolite", 0x1273de).addItemPart("dust").addBlockPart("ore");
		Mat lead = new Mat("lead", 0x544773).addItemParts(metal).addBlockPart("block");
		Mat silver = new Mat("silver", 0x9cbddc).addItemParts(metal).addBlockPart("block");
		Mat platinum = new Mat("platinum", 0x70b6f7).setMagicNumber(3, 2).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat gold = new Mat("gold", 0xe7ca53).addItemParts(modmetal, modtools);
		Mat copper = new Mat("copper", 0xc78621).addItemParts(modmetal, tools).setMagicNumber(2, 1);
		Mat iron = new Mat("iron", 0xE0E0E0).addItemPart("plate", "gear", "dust", "shield", "bow", "fishingrod", "hammer", "excavator");
		Mat bronze = new Mat("bronze", 0xc69114).setMagicNumber(2, 2).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat brass = new Mat("brass", 0xdba31e).addItemParts(metal).addBlockPart("block");
		Mat wroughtiron = new Mat("wroughtiron", 0xceaa9f).addItemParts(metal).addBlockPart("block");
		Mat cobalt = new Mat("cobalt", 0x505080).setMagicNumber(3, 5).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat chromium = new Mat("chromium", 0xf4c4b5).addItemParts(metal).addBlockPart("block");
		Mat invar = new Mat("invar", 0xcebe7c).setMagicNumber(2, 3).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat electrum = new Mat("electrum", 0xf3d248).addItemParts(metal).addBlockPart("block");
		Mat aluminium = new Mat("aluminium", 0xbad4ec).addItemParts(metal).addBlockPart("block");
		Mat steel = new Mat("steel", 0x424c55).setMagicNumber(2, 3).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat tungstensteel = new Mat("tungstensteel", 0x274562).setMagicNumber(4, 4).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat zinc = new Mat("zinc", 0xbba69f).addItemParts(metal).addBlockPart("block");
		Mat osmium = new Mat("osmium", 0x93bbe8).setMagicNumber(3, 2).addItemParts(metal, tools, armor).addBlockPart("block");
		Mat iridium = new Mat("iridium", 0xFFFFFF).addItemParts(metal).addBlockPart("block");
		Mat magnesium = new Mat("magnesium", 0x000000).addItemParts(metal).addBlockPart("block", "ore");
		
		registerMat(nickel);
		registerMat(tin);
		registerMat(tungsten);
		registerMat(titanium);
		registerMat(ruby);
		registerMat(sapphire);
		registerMat(peridot);
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
		registerMat(nikolite);
		registerMat(magnesium);

		registerOre("nickel_ore_overworld", BiomeSelectors.foundInOverworld(), OreConfiguredFeatures.STONE_ORE_REPLACEABLES, nickel.blockPartsBlocks.get("ore"), 9, 20, -12, 64);
		registerOre("tin_ore_nether", BiomeSelectors.foundInTheNether(), OreConfiguredFeatures.NETHERRACK, tin.blockPartsBlocks.get("ore"), 9, 40, 10, 112);
		addTags(shears, "fabric:items/shears");
		addTags(pickblocks, "minecraft:blocks/mineable/pickaxe");
		addTags(pickblocks, "minecraft:blocks/needs_stone_tool");
	}

	public void registerMat(Mat mat){
		for (Map.Entry<String, Item> set : mat.itemParts.entrySet()) {
			registerItem(set.getValue(), set.getKey()+"_"+mat.name);
			if (mat.isGeneric == false){
				if (set.getKey() == "bow"){
					registerItemModel(set.getKey()+"_"+mat.name, "item/bow", set.getKey()+"_"+mat.name);
				}else if(set.getKey() == "shield"){
					registerItemModel(set.getKey()+"_"+mat.name, "fabricshieldlib:item/fabric_shield", set.getKey()+"_"+mat.name);
				}else{
					registerItemModel(set.getKey()+"_"+mat.name, "item/generated", set.getKey()+"_"+mat.name);
				}
			}else{
				if (set.getKey() == "bow"){
					registerItemModel(set.getKey()+"_"+mat.name, "item/bow", set.getKey());
				}else if(set.getKey() == "shield"){
					registerItemModel(set.getKey()+"_"+mat.name, "fabricshieldlib:item/fabric_shield", set.getKey());
				}else{
					registerItemModel(set.getKey()+"_"+mat.name, "item/generated", set.getKey());
				}
			}
				
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.color, set.getValue());
			RESOURCE_PACK.addTag(new Identifier("c:items/"+mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
		}

		for (Map.Entry<String, Item> set : mat.blockPartsItems.entrySet()) {
			registerBlock(set.getValue(), mat.blockPartsBlocks.get(set.getKey()), set.getKey()+"_"+mat.name);

			if (mat.isGeneric == false)
				registerBlockModel(set.getKey()+"_"+mat.name, "block/cube_all", set.getKey()+"_"+mat.name);
			else
				registerBlockModel(set.getKey()+"_"+mat.name, "block/leaves", set.getKey());
			ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> mat.color, mat.blockPartsBlocks.get(set.getKey()));
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.color, set.getValue());
			RESOURCE_PACK.addTag(new Identifier("c:items/"+mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
			RESOURCE_PACK.addLootTable(new Identifier(modid+":"+"blocks/"+set.getKey()+"_"+mat.name), JLootTable.loot("minecraft:block").pool(new JPool().rolls(1).entry(new JEntry().type("minecraft:item").name(modid+":"+set.getKey()+"_"+mat.name).condition("minecraft:survives_explosion"))));
			pickblocks.add(set.getKey()+"_"+mat.name);
		}

		if(mat.itemParts.containsKey("ingot")){
			if (mat.itemParts.containsKey("nugget")){
				RESOURCE_PACK.addRecipe(new Identifier(modid, mat.name+"_ingot_to_nugget"), JRecipe.shapeless(JIngredients.ingredients().add(JIngredient.ingredient().item(mat.itemParts.get("ingot"))), JResult.itemStack(mat.itemParts.get("nugget"), 9)));
				RESOURCE_PACK.addRecipe(new Identifier(modid, mat.name+"_nugget_to_ingot"), JRecipe.shaped(JPattern.pattern("NNN", "NNN", "NNN"), JKeys.keys().key("N", JIngredient.ingredient().item(mat.itemParts.get("nugget"))), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
			}

			if (mat.blockPartsItems.containsKey("ore")){
				RESOURCE_PACK.addRecipe(new Identifier(modid, mat.name+"_ore_to_ingot"), JRecipe.smelting(JIngredient.ingredient().item(mat.blockPartsItems.get("ore")), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
			}

			if (mat.blockPartsItems.containsKey("block")){
				if (mat.itemParts.containsKey("excavator")){
					RESOURCE_PACK.addRecipe(new Identifier("arrp", mat.name+"_excavator"), 
					JRecipe.shaped(JPattern.pattern(" B ", "BSB", " S "), JKeys.keys()
						.key("b", JIngredient.ingredient().item(mat.blockPartsItems.get("block")))
						.key("s", JIngredient.ingredient().item(Items.STICK)),
						JResult.itemStack(mat.itemParts.get("excavator"), 1)));
				}
			}
		}

		if (mat.itemParts.containsKey("shear")){
			shears.add("shears_"+mat.name);
		}
	}

	public void addTags(HashSet<String> hashset, String namespace){
		JTag jtag = new JTag();
		for (String string : hashset){
			jtag.add(new Identifier(modid+":"+string));
		}

		RESOURCE_PACK.addTag(new Identifier(namespace), jtag);
	}

	public void registerOre(String name, Predicate<BiomeSelectionContext> predicate, RuleTest replace, Block block, Integer vein_size, Integer veins_per_chunk, Integer min_height, Integer max_height){
//		ConfiguredFeature<?, ?> ORE_FEATURE = Feature.ORE.configure(new OreFeatureConfig(replace,block.getDefaultState(), vein_size));
//		ConfiguredFeature<OreFeatureConfig, ?> ORE_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(replace,block.getDefaultState(), vein_size));
//		PlacedFeature ORE_FEATURE_PLACED = ORE_FEATURE.withPlacement(CountPlacementModifier.of(veins_per_chunk), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(min_height), YOffset.fixed(max_height)));	
//		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,	new Identifier(modid, name), ORE_FEATURE);
//		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(modid, name), ORE_FEATURE_PLACED);
//		BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(modid, name)));
	}

	public void registerItem(Item item, String name){
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}

	public void registerItemModel(String modelName, String modelBase, String textureName){
		RESOURCE_PACK.addModel(JModel.model(modelBase).textures(new JTextures().layer0(modid+":item/"+textureName)), new Identifier(modid, "item/"+modelName));
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
	}

	public void registerBlock(Item item, Block block, String name){
		Registry.register(Registry.BLOCK, new Identifier(modid, name), block);
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}

	public void registerBlockModel(String modelName, String modelBase, String textureName){
		RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(new Identifier(modid+":block/"+modelName)))), new Identifier(modid, modelName));
		RESOURCE_PACK.addModel(JModel.model().parent(modelBase).textures(new JTextures().var("all",modid+":block/"+textureName)), new Identifier(modid, "block/"+modelName));
		RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/"+modelName), new Identifier(modid, "item/"+modelName));
	}
}
