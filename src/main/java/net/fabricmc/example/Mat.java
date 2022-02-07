package net.fabricmc.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import draylar.magna.item.*;
import net.fabricmc.example.Tools.*;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.Item.Settings;
import ru.bclib.items.tool.BaseShearsItem;



public class Mat {
    public final HashMap<String, Item> itemParts = new HashMap<>();
	public final HashMap<String, Block> blockPartsBlocks = new HashMap<>();
	public final HashMap<String, Item> blockPartsItems = new HashMap<>();
	public final HashSet<String> tags = new HashSet<>();
	public final String name;

    public Mat(String name) {
		this.name = name;
	}

    public Mat addItemParts(String[]... partArrays) {
		for (String[] parts : partArrays) {
			for (String part : parts) {
				if (part == "hammer"){
					this.itemParts.put(part, new HammerItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "excavator") {
					this.itemParts.put(part, new ExcavatorItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "axe") {
					this.itemParts.put(part, new ModAxeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "hoe") {
					this.itemParts.put(part, new ModHoeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "pickaxe") {
					this.itemParts.put(part, new ModPickaxeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shovel") {
					this.itemParts.put(part, new ModShovelItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "sword") {
					this.itemParts.put(part, new ModSwordItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shears") {
					this.itemParts.put(part, new BaseShearsItem(new Settings().group(ItemGroup.MISC).maxDamage(200)));
				} else {
					this.itemParts.put(part, new Item(new Settings().group(ItemGroup.MISC)));
				}
			}
		}
		return this;
	}

	public Mat addItemPart(String... parts) {
		return addItemParts(parts);
	}

	public Mat addBlockParts(String[]... partArrays) {
		for (String[] parts : partArrays) {
			for (String part : parts) {
				this.blockPartsBlocks.put(part, new Block(Block.Settings.of(Material.STONE).strength(4.0f)));
				this.blockPartsItems.put(part, new BlockItem(this.blockPartsBlocks.get(part), new Settings().group(ItemGroup.MISC)));
			}
		}
		return this;
	}

	public Mat addBlockPart(String... parts) {
		return addBlockParts(parts);
	}


	public Mat addTags(String[]... tagArrays) {
		for (String[] tags : tagArrays) this.tags.addAll(Arrays.asList(tags));
		return this;
	}

	public Mat addTag(String... tags) {
		return addTags(tags);
	}
}
