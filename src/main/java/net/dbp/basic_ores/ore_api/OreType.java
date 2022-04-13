package net.dbp.basic_ores.ore_api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class OreType {
    public String name;
    public Item item;
    public int color;
    public HashMap<String, Block> oreBlocks = new HashMap<>();
    public HashMap<String, Item> oreItems = new HashMap<>();

    public OreType(String name, Item item, int color){
        this.name = name;
        this.item = item;
        this.color = color;
    }
}
