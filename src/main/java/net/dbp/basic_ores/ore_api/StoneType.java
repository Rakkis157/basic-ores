package net.dbp.basic_ores.ore_api;

import net.minecraft.block.Block;

public class StoneType {
    public String name;
    public Block block;
    public String textureLocation;

    public StoneType(String name, Block block, String textureLocation){
        this.name = name;
        this.block = block;
        this.textureLocation = textureLocation;
    }
}
