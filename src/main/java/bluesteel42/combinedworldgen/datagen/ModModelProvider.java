package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import bluesteel42.combinedworldgen.block.ModBlocks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    public void generateWaterLilyModels(BlockStateModelGenerator blockStateModelGenerator, Block lily) {
        blockStateModelGenerator.registerItemModel(lily.asItem());
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(lily, BlockStateModelGenerator.modelWithYRotation(BlockStateModelGenerator.createModelVariant(ModelIds.getBlockModelId(lily)))));
    }

    private static void registerPumpkinModels(BlockStateModelGenerator blockStateModelGenerator, Block pumpkin, Block carvedPumpkin, Block jackOLantern, Block pumpkinStem, Block attachedPumpkinStem) {
        blockStateModelGenerator.registerGourd(pumpkinStem, attachedPumpkinStem);
        TextureMap textureMap = TextureMap.sideEnd(pumpkin);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(pumpkin, BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockModelId(pumpkin))));
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(carvedPumpkin, textureMap);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(jackOLantern, textureMap);

        // Build the model ID (e.g. yourmod:block/white_pumpkin)
        Identifier modelId = ModelIds.getBlockModelId(pumpkin);

        // Create model JSON manually
        JsonObject modelJson = new JsonObject();
        modelJson.addProperty("parent", "minecraft:block/cube_column");

        JsonObject textures = new JsonObject();
        textures.addProperty("end", CombinedWorldgen.MOD_ID + ":" + ModelIds.getBlockSubModelId(pumpkin, "_top").getPath());
        textures.addProperty("side", CombinedWorldgen.MOD_ID + ":" + ModelIds.getBlockSubModelId(pumpkin, "_side").getPath());
        modelJson.add("textures", textures);

        // Add "display" section
        JsonObject display = new JsonObject();
        JsonObject firstPerson = new JsonObject();
        firstPerson.add("rotation", createJsonArray(0f, 135f, 0f));
        firstPerson.add("translation", createJsonArray(0f, 0f, 0f));
        firstPerson.add("scale", createJsonArray(0.4f, 0.4f, 0.4f));
        display.add("firstperson_righthand", firstPerson);
        modelJson.add("display", display);

        // Register the model manually
        blockStateModelGenerator.modelCollector.accept(modelId, () -> modelJson);
    }

    private static JsonArray createJsonArray(float x, float y, float z) {
        JsonArray array = new JsonArray();
        array.add(x);
        array.add(y);
        array.add(z);
        return array;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOOSE_DIRT);

        generateWaterLilyModels(blockStateModelGenerator, ModBlocks.WHITE_WATER_LILY);
        generateWaterLilyModels(blockStateModelGenerator, ModBlocks.BLUE_WATER_LILY);
        generateWaterLilyModels(blockStateModelGenerator, ModBlocks.PINK_WATER_LILY);
        generateWaterLilyModels(blockStateModelGenerator, ModBlocks.PURPLE_WATER_LILY);

        registerPumpkinModels(blockStateModelGenerator, ModBlocks.WHITE_PUMPKIN, ModBlocks.CARVED_WHITE_PUMPKIN, ModBlocks.WHITE_JACK_O_LANTERN, ModBlocks.WHITE_PUMPKIN_STEM, ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM);
        registerPumpkinModels(blockStateModelGenerator, ModBlocks.GREEN_PUMPKIN, ModBlocks.CARVED_GREEN_PUMPKIN, ModBlocks.GREEN_JACK_O_LANTERN, ModBlocks.GREEN_PUMPKIN_STEM, ModBlocks.ATTACHED_GREEN_PUMPKIN_STEM);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
