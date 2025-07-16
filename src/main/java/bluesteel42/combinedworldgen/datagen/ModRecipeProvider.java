package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            public void offerDyeFromPlantRecipes(ItemConvertible dye, ItemConvertible plant) {
                offerShapelessRecipe(dye, plant, getItemPath(dye), 1);
            }
            public void offerPumpkinRecipes(ItemConvertible pumpkin, ItemConvertible carvedPumpkin, ItemConvertible jackOLantern, ItemConvertible seeds) {
                createShaped(RecipeCategory.BUILDING_BLOCKS, jackOLantern, 1)
                        .pattern("A")
                        .pattern("B")
                        .input('A', carvedPumpkin)
                        .input('B', Blocks.TORCH)
                        .criterion(hasItem(carvedPumpkin), conditionsFromItem(carvedPumpkin))
                        .offerTo(exporter);

//                createShapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
//                        .input(pumpkin)
//                        .input(Items.SUGAR)
//                        .input(ItemTags.EGGS)
//                        .group(getItemPath(Items.PUMPKIN_PIE))
//                        .criterion(hasItem(pumpkin), conditionsFromItem(pumpkin))
//                        .criterion(hasItem(carvedPumpkin), conditionsFromItem(carvedPumpkin))
//                        .offerTo(exporter);

                createShapeless(RecipeCategory.MISC, seeds, 4)
                        .input(pumpkin)
                        .criterion(hasItem(pumpkin), conditionsFromItem(pumpkin))
                        .offerTo(exporter);

            }
            @Override
            public void generate() {
                offerDyeFromPlantRecipes(Items.PURPLE_DYE, ModBlocks.VIOLET);
                offerDyeFromPlantRecipes(Items.WHITE_DYE, ModBlocks.WHITE_WATER_LILY);
                offerDyeFromPlantRecipes(Items.BLUE_DYE, ModBlocks.BLUE_WATER_LILY);
                offerDyeFromPlantRecipes(Items.PINK_DYE, ModBlocks.PINK_WATER_LILY);
                offerDyeFromPlantRecipes(Items.PURPLE_DYE, ModBlocks.PURPLE_WATER_LILY);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOOSE_DIRT, 4)
                        .pattern("DL")
                        .pattern("LD")
                        .input('D', Blocks.DIRT)
                        .input('L', Blocks.LEAF_LITTER)
                        .criterion(hasItem(Blocks.LEAF_LITTER), conditionsFromItem(Blocks.LEAF_LITTER))
                        .offerTo(exporter);

                offerPumpkinRecipes(ModBlocks.WHITE_PUMPKIN, ModBlocks.CARVED_WHITE_PUMPKIN, ModBlocks.WHITE_JACK_O_LANTERN, ModItems.WHITE_PUMPKIN_SEEDS);
                offerPumpkinRecipes(ModBlocks.GREEN_PUMPKIN, ModBlocks.CARVED_GREEN_PUMPKIN, ModBlocks.GREEN_JACK_O_LANTERN, ModItems.GREEN_PUMPKIN_SEEDS);

            }
        };
    }

    @Override
    public String getName() {
        return CombinedWorldgen.MOD_ID + " recipes";
    }
}
