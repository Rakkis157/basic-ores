package net.fabricmc.example;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JIngredients;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import java.util.Map;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create("basic-ores:resource-pack");
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String modid = "basic-ores";

	@Override
	public void onInitialize() {
		Mat nickel = new Mat("nickel");
		nickel.addItemPart("ingot", "nugget", "plate", "gear");
		nickel.addBlockPart("ore", "block");
		nickel.addTag("ingot_to_nugget", "ore_to_ingot");
		Mat lead = new Mat("lead");
		lead.addItemPart("ingot", "nugget", "plate", "gear");
		lead.addTag("ingot_to_nugget", "ore_to_ingot");
		lead.addBlockPart("ore", "block");


		registerMat(nickel);
		registerMat(lead);


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
			RESOURCE_PACK.addRecipe(new Identifier("arrp", mat.name+"_ingot_to_nugget"), JRecipe.smelting(JIngredient.ingredient().item(mat.blockPartsItems.get("ore")), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
		}
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
