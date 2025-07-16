package bluesteel42.combinedworldgen.world.gen;

import bluesteel42.combinedworldgen.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.List;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_COARSE_DIRT_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LOOSE_DIRT_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.FOREST,  BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST, BiomeKeys.TAIGA, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.JUNGLE, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.MEADOW, BiomeKeys.CHERRY_GROVE)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.EXTRA_DIRT_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.RIVER, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.COLD_OCEAN)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.WET_ORE_MUD_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.DESERT, BiomeKeys.BEACH, BiomeKeys.STONY_SHORE)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.DESERT_ORE_SAND_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BADLANDS_ORE_RED_SAND_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.FROZEN_PEAKS, BiomeKeys.FROZEN_RIVER, BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.ICE_SPIKES)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.FROZEN_ORE_ICE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_SLOPES, BiomeKeys.SNOWY_BEACH, BiomeKeys.GROVE, BiomeKeys.STONY_PEAKS, BiomeKeys.JAGGED_PEAKS)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.SNOWY_ORE_SNOW_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(List.of(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA)), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.OLD_GROWTH_ORE_MOSSY_COBBLESTONE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.OLD_GROWTH_ORE_MOSS_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PALE_GARDEN), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.PALE_GARDEN_ORE_MOSS_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.MUSHROOM_ORE_BROWN_MUSHROOM_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.MUSHROOM_ORE_RED_MUSHROOM_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.OLD_GROWTH_BIRCH_MOSS_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.OLD_GROWTH_BIRCH_MOSS_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.CHERRY_GROVE), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_PATCH_CHERRY_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_WHITE_WATER_LILIES_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_PINK_WATER_LILIES_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_BLUE_WATER_LILIES_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_PURPLE_WATER_LILIES_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_SURFACE_MUD_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_MUD_DISK_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SWAMP_MUD_DISK_PLACED_KEY);

    }
}
