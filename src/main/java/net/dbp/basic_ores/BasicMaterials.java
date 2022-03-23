package net.dbp.basic_ores;

import java.util.*;
import net.minecraft.item.*;
import net.devtech.arrp.json.loot.*;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.util.Identifier;
import net.devtech.arrp.json.tags.JTag;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class BasicMaterials {
    public static final String[] vanilla = {"plate", "gear", "dust"};
	public static final String[] metal = {"ingot", "nugget", "raw_ore", "plate", "gear", "dust", "wire", "can", "large_plate", "tube"};
	public static final String[] gem = {"gem", "dust"};
	public static final String[] ore = {"ore", "ore_gravel", "block"};
	public static final String[] block = {"block"};
	public static String[] tools = {
		"axe", "hoe", "pickaxe", "shovel", "sword", "shear", "shield", "bow", "fishingrod", "hammer", "excavator", "helmet", "chestplate", "legging", "boot",
		"spanner", "paxel", "dagger", "spear", "broadsword", "rapier", "haladie", "waraxe", "katana", "boomerang"
	};
	public static final HashSet<String> shears = new HashSet<>();
	public static final HashSet<String> Mattags = new HashSet<>();
	public static final HashSet<String> pickblocks = new HashSet<>();
	
    //vanilla
	static BasicMaterial gold = new BasicMaterial("gold", 0xe7ca53).addItemParts(vanilla);
	static BasicMaterial copper = new BasicMaterial("copper", 0xc78621).addItemParts(vanilla, tools).setMagicNumber(2, 1);
	static BasicMaterial iron = new BasicMaterial("iron", 0xE0E0E0).addItemParts(vanilla).addItemPart("shield", "bow", "fishingrod", "hammer", "excavator");
	static BasicMaterial emerald = new BasicMaterial("emerald", 0x08dd7b).setMagicNumber(3, 3).addItemPart("dust").addItemParts(tools);
	//basic
	static BasicMaterial nickel = new BasicMaterial("nickel", 0x464D19).setMagicNumber(2, 2).addItemParts(metal, tools).addBlockPart(ore);
	static BasicMaterial galena = new BasicMaterial("galena", 0xFFFFFF).addBlockPart("ore");
	static BasicMaterial lead = new BasicMaterial("lead", 0x544773).addItemParts(metal).addBlockParts(block);
	static BasicMaterial silver = new BasicMaterial("silver", 0x9cbddc).addItemParts(metal).addBlockParts(block);
	static BasicMaterial platinum = new BasicMaterial("platinum", 0x70b6f7).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial sheldonite = new BasicMaterial("sheldonite", 0x181b07).addBlockPart(ore);
	static BasicMaterial invar = new BasicMaterial("invar", 0xcebe7c).setMagicNumber(2, 3).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial electrum = new BasicMaterial("electrum", 0xf3d248).addItemParts(metal).addBlockParts(block);
	//basic2
	static BasicMaterial tin = new BasicMaterial("tin", 0xE0E0FF).addItemParts(metal).addBlockPart(ore);
	static BasicMaterial cassiterite = new BasicMaterial("cassiterite", 0xE0E0FF).addBlockPart(ore);
	static BasicMaterial bronze = new BasicMaterial("bronze", 0xc69114).setMagicNumber(2, 2).addItemParts(metal).addBlockParts(block);
	static BasicMaterial steel = new BasicMaterial("steel", 0x424c55).setMagicNumber(2, 3).addItemParts(metal).addBlockParts(block);
	static BasicMaterial aluminium = new BasicMaterial("aluminium", 0xbad4ec).addItemParts(metal).addBlockParts(block);
	//power
	static BasicMaterial nikolite = new BasicMaterial("nikolite", 0x1273de).addItemPart("dust").addBlockPart("ore");
	static BasicMaterial ruby = new BasicMaterial("ruby", 0xbb4a1d).setMagicNumber(2, 3).addItemParts(gem, tools).addBlockPart(ore);
	static BasicMaterial sapphire = new BasicMaterial("sapphire", 0x1b55b8).setMagicNumber(2, 3).addItemParts(gem, tools).addBlockPart(ore);
	static BasicMaterial olivine = new BasicMaterial("olivine", 0x08dd7b).setMagicNumber(3, 3).addItemParts(gem).addBlockPart(ore);
	static BasicMaterial redalloy = new BasicMaterial("redalloy", 0x000000).addItemParts(metal).addBlockParts(ore);
	//advanced
	static BasicMaterial tungsten = new BasicMaterial("tungsten", 0x181b07).addItemParts(metal).addBlockPart(ore);
	static BasicMaterial titanium = new BasicMaterial("titanium", 0xc4b4ed).addItemParts(metal).addBlockPart(ore);
	static BasicMaterial sphalerite = new BasicMaterial("sphalerite", 0x181b07).addBlockPart(ore);
	static BasicMaterial zinc = new BasicMaterial("zinc", 0xbba69f).addItemParts(metal).addBlockParts(block);
	static BasicMaterial brass = new BasicMaterial("brass", 0xdba31e).addItemParts(metal).addBlockParts(block);
	static BasicMaterial aluminiumbrass = new BasicMaterial("aluminiumbrass", 0xdba31e).addItemParts(metal).addBlockParts(block);
	static BasicMaterial cobalt = new BasicMaterial("cobalt", 0x505080).setMagicNumber(3, 5).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial wroughtiron = new BasicMaterial("wroughtiron", 0xceaa9f).addItemParts(metal).addBlockParts(block);
	static BasicMaterial chromium = new BasicMaterial("chromium", 0xf4c4b5).addItemParts(metal).addBlockParts(block);
	static BasicMaterial tungstensteel = new BasicMaterial("tungstensteel", 0x274562).setMagicNumber(4, 4).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial osmium = new BasicMaterial("osmium", 0x93bbe8).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial iridium = new BasicMaterial("iridium", 0xFFFFFF).addItemParts(metal, tools).addBlockParts(block);
	static BasicMaterial magnesium = new BasicMaterial("magnesium", 0x000000).addItemParts(metal).addBlockParts(ore);
	static BasicMaterial cupronickel = new BasicMaterial("cupronickel", 0x000000).addItemParts(metal).addBlockParts(ore);
	//nuclear
	static BasicMaterial uranium = new BasicMaterial("uranium", 0x000000).addItemParts(metal).addBlockParts(ore);
	static BasicMaterial thorium = new BasicMaterial("thorium", 0x000000).addItemParts(metal);
	static BasicMaterial plutonium = new BasicMaterial("plutonium", 0x000000).addItemParts(metal);
	//classic foundation
	static BasicMaterial signalium = new BasicMaterial("signalium", 0x000000).addItemParts(metal);
	static BasicMaterial lumium = new BasicMaterial("lumium", 0x000000).addItemParts(metal);
	static BasicMaterial enderium = new BasicMaterial("enderium", 0x000000).addItemParts(metal);
	static BasicMaterial pyrotheum = new BasicMaterial("pyrotheum", 0x000000).addItemParts(metal);
	static BasicMaterial cryotheum = new BasicMaterial("cryotheum", 0x000000).addItemParts(metal);
	static BasicMaterial aerotheum = new BasicMaterial("aerotheum", 0x000000).addItemParts(metal);
	static BasicMaterial petrotheum = new BasicMaterial("petrotheum", 0x000000).addItemParts(metal);
	static BasicMaterial fluxedelectrum = new BasicMaterial("fluxedelectrum", 0x000000).addItemParts(metal);
	static BasicMaterial flux = new BasicMaterial("flux", 0x000000).addItemParts(metal);
	//classic io
	static BasicMaterial electricalsteel = new BasicMaterial("electricalsteel", 0x000000).addItemParts(metal);
	static BasicMaterial energetic = new BasicMaterial("energetic", 0x000000).addItemParts(metal);
	static BasicMaterial vibrant = new BasicMaterial("vibrant", 0x000000).addItemParts(metal);
	static BasicMaterial conductiveiron = new BasicMaterial("conductiveiron", 0x000000).addItemParts(metal);
	static BasicMaterial pulsatingiron = new BasicMaterial("pulsatingiron", 0x000000).addItemParts(metal);
	static BasicMaterial darksteel = new BasicMaterial("darksteel", 0x000000).addItemParts(metal);
	static BasicMaterial soularium = new BasicMaterial("soularium", 0x000000).addItemParts(metal);
	static BasicMaterial darksoularium = new BasicMaterial("darksoularium", 0x000000).addItemParts(metal);
    
    public static void registerMats(){
        registerMat(nickel);
		registerMat(tin);
		registerMat(cassiterite);
		registerMat(tungsten);
		registerMat(titanium);
		registerMat(sphalerite);
		registerMat(ruby);
		registerMat(sapphire);
		registerMat(olivine);
		registerMat(redalloy);
		registerMat(emerald);
		registerMat(galena);
		registerMat(lead);
		registerMat(silver);
		registerMat(platinum);
		registerMat(sheldonite);
		registerMat(gold);
		registerMat(copper);
		registerMat(iron);
		registerMat(bronze);
		registerMat(brass);
		registerMat(aluminiumbrass);
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
		registerMat(cupronickel);
		registerMat(uranium);
		registerMat(thorium);
		registerMat(plutonium);
		registerMat(signalium);
		registerMat(lumium);
		registerMat(enderium );
		registerMat(pyrotheum);
		registerMat(cryotheum);
		registerMat(aerotheum);
		registerMat(petrotheum);
		registerMat(fluxedelectrum);
		registerMat(flux);
		registerMat(electricalsteel);
		registerMat(energetic);
		registerMat(vibrant);
		registerMat(conductiveiron);
		registerMat(pulsatingiron);
		registerMat(darksteel);
		registerMat(soularium);
		registerMat(darksoularium);

		BasicJson.addTags(pickblocks, "minecraft:blocks/mineable/pickaxe");
		BasicJson.addTags(pickblocks, "minecraft:blocks/needs_stone_tool");
		if (Basic.bclibCompat) BasicJson.addTags(shears, "fabric:items/shears");
		if (Basic.moreTagsCompat) BasicJson.addTags(shears, "c:items/shears");
    }

    public static void registerMat(BasicMaterial Mat){
		for (Map.Entry<String, Item> set : Mat.itemParts.entrySet()) {
			Basic.registerItem(set.getValue(), set.getKey()+"_"+Mat.name);
			switch (set.getKey()) {
				case "bow": BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/bow", set.getKey()); break;
				case "shield": BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "fabricshieldlib:item/fabric_shield", set.getKey()); break;
				default: BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/generated", set.getKey()); break;
			}
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Mat.color, set.getValue());
			//Main.RESOURCE_PACK.addTag(new Identifier("c:items/"+Mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(Main.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+Mat.name), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
		}

		for (Map.Entry<String, Item> set : Mat.blockPartsItems.entrySet()) {
			Basic.registerBlock(set.getValue(), Mat.blockPartsBlocks.get(set.getKey()), set.getKey()+"_"+Mat.name);

			if (set.getKey() == "ore" || set.getKey() == "ore_gravel")
				BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, "block/cube_all", set.getKey()+"_"+Mat.name);
			else
				BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, "block/leaves", set.getKey());
			ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> Mat.color, Mat.blockPartsBlocks.get(set.getKey()));
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Mat.color, set.getValue());
			//Main.RESOURCE_PACK.addTag(new Identifier("c:items/"+Mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(Main.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+Mat.name), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addLootTable(new Identifier(Basic.modid+":"+"blocks/"+set.getKey()+"_"+Mat.name), JLootTable.loot("minecraft:block").pool(new JPool().rolls(1).entry(new JEntry().type("minecraft:item").name(Basic.modid+":"+set.getKey()+"_"+Mat.name).condition("minecraft:survives_explosion"))));
			pickblocks.add(set.getKey()+"_"+Mat.name);
		}

		if(Mat.itemParts.containsKey("ingot")){
			if (Mat.itemParts.containsKey("nugget")){
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "ingot_to_nugget"+"/"+Mat.name), JRecipe.shapeless(JIngredients.ingredients().add(JIngredient.ingredient().item(Mat.itemParts.get("ingot"))), JResult.itemStack(Mat.itemParts.get("nugget"), 9)));
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "nugget_to_ingot"+"/"+Mat.name), JRecipe.shaped(JPattern.pattern("NNN", "NNN", "NNN"), JKeys.keys().key("N", JIngredient.ingredient().item(Mat.itemParts.get("nugget"))), JResult.itemStack(Mat.itemParts.get("ingot"), 1)));
			}

			if (Mat.blockPartsItems.containsKey("ore")){
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "ore_to_ingot"+"/"+Mat.name), JRecipe.smelting(JIngredient.ingredient().item(Mat.blockPartsItems.get("ore")), JResult.itemStack(Mat.itemParts.get("ingot"), 1)));
			}

			if (Mat.itemParts.containsKey("hammer")) BasicJson.createToolRecipe(Mat.name, "hammer", BasicJson.hammerShape, "large_plates", Mat.itemParts.get("hammer"), Items.STICK);
			if (Mat.itemParts.containsKey("excavator")) BasicJson.createToolRecipe(Mat.name, "excavator", BasicJson.excavatorShape, "large_plates", Mat.itemParts.get("excavator"), Items.STICK);
			if (Mat.itemParts.containsKey("pickaxe")) BasicJson.createToolRecipe(Mat.name, "pickaxe", BasicJson.pickaxeShape, "ingots", "plates", Mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if(Mat.itemParts.containsKey("gem")){
			if (Mat.itemParts.containsKey("hammer")) BasicJson.createToolRecipe(Mat.name, "hammer", BasicJson.hammerShape, "blocks", Mat.itemParts.get("hammer"), Items.STICK);
			if (Mat.itemParts.containsKey("excavator")) BasicJson.createToolRecipe(Mat.name, "excavator", BasicJson.excavatorShape, "blocks", Mat.itemParts.get("excavator"), Items.STICK);
			if (Mat.itemParts.containsKey("pickaxe")) BasicJson.createToolRecipe(Mat.name, "pickaxe", BasicJson.pickaxeShape, "gems", "gems", Mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if (Mat.itemParts.containsKey("shear")){
			shears.add("shear_"+Mat.name);
		}
	}

}
