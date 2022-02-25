package net.dbp.basic_ores;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;

import draylar.gateofbabylon.item.*;
import draylar.magna.item.*;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import ru.bclib.items.tool.*;

public class Mat {
    public final HashMap<String, Item> itemParts = new HashMap<>();
	public final HashMap<String, Block> blockPartsBlocks = new HashMap<>();
	public final HashMap<String, Item> blockPartsItems = new HashMap<>();
	public final HashSet<String> tags = new HashSet<>();
	public final String name;
	public int color = 0xFFFFFF;
	public int magicNumber = 2;
	public int magicNumber2 = 1;
	public boolean isGeneric = false;

    public Mat(String name, int color) {
		this.name = name;
		this.color = color;
		this.isGeneric = true;
	}

	public Mat(String name){
		this.name = name;
		this.isGeneric = false;
	}

	public Ingredient getRepair() {
		if (itemParts.containsKey("ingot")){
			return Ingredient.ofItems(itemParts.get("ingot"));
		}else if(itemParts.containsKey("gem")){
			return Ingredient.ofItems(itemParts.get("gem"));
		}else{
			return null;
		}
	}

	public class ToolMat implements ToolMaterial{
		@Override
		public int getDurability() {
			return magicNumber2*magicNumber2*magicNumber2*60;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return magicNumber*3;
		}

		@Override
		public float getAttackDamage() {
			return magicNumber;
		}

		@Override
		public int getMiningLevel() {
			return magicNumber;
		}

		@Override
		public int getEnchantability() {
			return magicNumber2*18;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return getRepair();
		}

	}

	public class ArmorMat implements ArmorMaterial{
		@Override
		public int getDurability(EquipmentSlot slot) {
			int[] dura = new int[] {magicNumber2*12, magicNumber2*14, magicNumber2*16, magicNumber2*10};
			return dura[slot.getEntitySlotId()];
		}

		@Override
		public int getProtectionAmount(EquipmentSlot slot) {
			int[] prot = new int[] {magicNumber, magicNumber*2+1, magicNumber*2+2, magicNumber};
			return prot[slot.getEntitySlotId()];
		}

		@Override
		public int getEnchantability() {
			return magicNumber2*10;
		}

		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return getRepair();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public float getToughness() {
			if (magicNumber >= 3){
				return magicNumber;
			}else{
				return 0;
			}
		}

		@Override
		public float getKnockbackResistance() {
			if (magicNumber >= 3){
				return magicNumber/30;
			}else{
				return 0;
			}
		}
		
	}

	public Mat setMagicNumber(int magicNumber, int magicNumber2){
		this.magicNumber = magicNumber;
		this.magicNumber2 = magicNumber2;
		return this;
	}

    public Mat addItemParts(String[]... partArrays) {
		for (String[] parts : partArrays) {
			for (String part : parts) {
				if (part == "hammer"){
					this.itemParts.put(part, new HammerItem(new ToolMat(), 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "excavator") {
					this.itemParts.put(part, new ExcavatorItem(new ToolMat(), 1, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "axe") {
					this.itemParts.put(part, new BaseAxeItem(new ToolMat(), 6, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "hoe") {
					this.itemParts.put(part, new BaseHoeItem(new ToolMat(), 0, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "pickaxe") {
					this.itemParts.put(part, new BasePickaxeItem(new ToolMat(), 1, -2.8f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shovel") {
					this.itemParts.put(part, new BaseShovelItem(new ToolMat(), 1.5f, -3.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "sword") {
					this.itemParts.put(part, new BaseSwordItem(new ToolMat(), 3, -2.4f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "shears") {
					this.itemParts.put(part, new BaseShearsItem(new Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				} else if (part == "fishingrod") {
					this.itemParts.put(part, new FishingRodItem(new Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				} else if (part == "bow") {
					this.itemParts.put(part, new BowItem(new Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				} else if (part == "shield") {
					this.itemParts.put(part, new FabricShieldItem(new Settings().maxDamage(magicNumber2*magicNumber2*magicNumber2*60).group(ItemGroup.MISC), 10, 13, itemParts.get("ingot")));
				} else if (part == "helmet") {
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "chestplate") {
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "leggings") {
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "boots") {
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.MISC)));
				} else if (part == "dagger") {
					this.itemParts.put(part, new DaggerItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "spear") {
					this.itemParts.put(part, new SpearItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "broadsword") {
					this.itemParts.put(part, new BroadswordItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "rapier") {
					this.itemParts.put(part, new RapierItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "haladie") {
					this.itemParts.put(part, new HaladieItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "waraxe") {
					this.itemParts.put(part, new WaraxeItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "katana") {
					this.itemParts.put(part, new KatanaItem(new ToolMat(), 0.5f, -1.0f, new Settings().group(ItemGroup.MISC)));
				} else if (part == "boomerang") {
					this.itemParts.put(part, new BoomerangItem(new Settings().group(ItemGroup.MISC), new ToolMat()));
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
