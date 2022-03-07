package net.dbp.basic_ores;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class TestRecipe extends AbstractCookingRecipe {
    //Same for the BlockEntity, we don't need some of the parameters in the constructor since we already now the type
    public TestRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Main.TEST_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }
 
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.BLACKSTONE);
    }
 
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Main.TEST_RECIPE_SERIALIZER;
    }
}