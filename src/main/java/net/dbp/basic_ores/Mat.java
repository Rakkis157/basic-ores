package net.dbp.basic_ores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;

import draylar.magna.item.*;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import ru.bclib.items.tool.*;



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
					this.itemParts.put(part, new BaseAxeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "hoe") {
					this.itemParts.put(part, new BaseHoeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "pickaxe") {
					this.itemParts.put(part, new BasePickaxeItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shovel") {
					this.itemParts.put(part, new BaseShovelItem(ToolMaterials.IRON, 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "sword") {
					this.itemParts.put(part, new BaseSwordItem(ToolMaterials.IRON, 1, -2.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "greatsword") {
					this.itemParts.put(part, new BaseSwordItem(ToolMaterials.IRON, 2, -3.5f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shears") {
					this.itemParts.put(part, new BaseShearsItem(new Settings().group(ItemGroup.MISC).maxDamage(200)));
				} else if (part == "fishingrod") {
					this.itemParts.put(part, new FishingRodItem(new Settings().group(ItemGroup.MISC).maxDamage(200)));
				} else if (part == "bow") {
					this.itemParts.put(part, new BowItem(new Settings().group(ItemGroup.MISC).maxDamage(200)));
				} else if (part == "shield") {
					this.itemParts.put(part, new FabricShieldItem(new Settings().maxDamage(200).group(ItemGroup.MISC), 10, 13, itemParts.get("ingot")));
				} else if (part == "helmet") {
					this.itemParts.put(part, new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "chestplate") {
					this.itemParts.put(part, new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "leggings") {
					this.itemParts.put(part, new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "boots") {
					this.itemParts.put(part, new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.MISC)));
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
