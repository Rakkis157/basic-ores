package net.dbp.basic_ores.ore_api;

public class OreGeneration {
    public String name;
    public OreType oretype;
    public int max;
    public int min;
    public int weight;
    public int size;
    public float discard;

    public OreGeneration(String name, OreType oretype, int min, int max, int weight, int size, float discard){
        this.name = name;
        this.oretype = oretype;
        this.min = min;
        this.max = max;
        this.weight = weight;
        this.size = size;
        this.discard = discard;
    }
}
