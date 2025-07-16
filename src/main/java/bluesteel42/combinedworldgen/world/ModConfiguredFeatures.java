package bluesteel42.combinedworldgen.world;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.world.tree.HangingMushroomTreeDecorator;
import bluesteel42.combinedworldgen.world.tree.NotchedTrunkBeehiveTreeDecorator;
import bluesteel42.combinedworldgen.world.tree.NotchedTrunkPlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLogsTreeDecorator;
import net.minecraft.world.gen.treedecorator.PlaceOnGroundTreeDecorator;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_COARSE_DIRT_KEY = registerKey("ore_coarse_dirt");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LOOSE_DIRT_KEY = registerKey("ore_loose_dirt");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_WHITE_PUMPKIN_KEY = registerKey("patch_white_pumpkin");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_GREEN_PUMPKIN_KEY = registerKey("patch_green_pumpkin");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PILE_GREEN_PUMPKIN_KEY = registerKey("pile_green_pumpkin");

    public static final RegistryKey<ConfiguredFeature<?, ?>> EXTRA_DIRT_ORE_KEY = registerKey("ore_extra_dirt");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WET_ORE_MUD_KEY = registerKey("ore_mud");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DESERT_ORE_SAND_KEY = registerKey("ore_sand");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BADLANDS_ORE_RED_SAND_KEY = registerKey("ore_red_sand");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FROZEN_ORE_ICE_KEY = registerKey("ore_ice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOWY_ORE_SNOW_KEY = registerKey("ore_snow");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_ORE_MOSSY_COBBLESTONE_KEY = registerKey("ore_mossy_cobblestone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_ORE_MOSS_KEY = registerKey("ore_moss");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALE_GARDEN_ORE_MOSS_KEY = registerKey("ore_pale_moss");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MUSHROOM_ORE_BROWN_MUSHROOM_KEY = registerKey("ore_brown_mushroom");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MUSHROOM_ORE_RED_MUSHROOM_KEY = registerKey("ore_red_mushroom");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_WHITE_WATER_LILIES_KEY = registerKey("swamp_white_water_lilies");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_PINK_WATER_LILIES_KEY = registerKey("swamp_pink_water_lilies");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_BLUE_WATER_LILIES_KEY = registerKey("swamp_blue_water_lilies");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_PURPLE_WATER_LILIES_KEY = registerKey("swamp_purple_water_lilies");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_SURFACE_MUD_KEY = registerKey("swamp_surface_mud");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_SURFACE_MUD_VEGETATION_KEY = registerKey("swamp_surface_mud_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_MUD_DISK_KEY = registerKey("swamp_mud_disk");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWAMP_FIREFLIES_KEY = registerKey("swamp_fireflies");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_PATCH_CHERRY_KEY = registerKey("flower_patch_cherry");

    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_BIRCH_VIOLET_KEY = registerKey("old_growth_birch_violet");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_BIRCH_MOSS_CARPET_KEY = registerKey("old_growth_birch_moss_carpet");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_BIRCH_MOSS_BLOCK_KEY = registerKey("old_growth_birch_moss_block");

    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPLING_BIRCH_KEY = registerKey("sapling_birch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPLING_BIRCH_BEES_005_KEY = registerKey("sapling_birch_bees_005");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_BIRCH_LEAF_LITTER_KEY = registerKey("forest_birch_leaf_litter");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_BIRCH_BEES_0002_KEY = registerKey("forest_birch_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_BIRCH_BEES_0002_LEAF_LITTER_KEY = registerKey("forest_birch_bees_0002_leaf_litter");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_SUPER_BIRCH_BEES_0002_KEY = registerKey("forest_super_birch_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FOREST_BIRCH_KEY = registerKey("forest_birch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> OLD_GROWTH_SUPER_BIRCH_BEES_0002_KEY = registerKey("old_growth_super_birch_bees_0002");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSSY_FALLEN_SUPER_BIRCH_TREE_KEY = registerKey("mossy_fallen_super_birch_tree");

    private static Pool.Builder<BlockState> flowerbed(Block flowerbed) {
        return segmentedBlock(flowerbed, 1, 4, FlowerbedBlock.FLOWER_AMOUNT, FlowerbedBlock.HORIZONTAL_FACING);
    }

    private static Pool.Builder<BlockState> segmentedBlock(Block block, int min, int max, IntProperty amountProperty, EnumProperty<Direction> facingProperty) {
        Pool.Builder<BlockState> builder = Pool.builder();

        for (int i = min; i <= max; i++) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                builder.add(block.getDefaultState().with(amountProperty, i).with(facingProperty, direction), 1);
            }
        }

        return builder;
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ORE_LOOSE_DIRT_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), ModBlocks.LOOSE_DIRT.getDefaultState(),33));
        register(context, ORE_COARSE_DIRT_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.COARSE_DIRT.getDefaultState(),33));

        register(context, EXTRA_DIRT_ORE_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.DIRT.getDefaultState(),33));
        register(context, WET_ORE_MUD_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.MUD.getDefaultState(),33));
        register(context, DESERT_ORE_SAND_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.SAND.getDefaultState(),33));
        register(context, BADLANDS_ORE_RED_SAND_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.RED_SAND.getDefaultState(),33));
        register(context, FROZEN_ORE_ICE_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.ICE.getDefaultState(),33));
        register(context, SNOWY_ORE_SNOW_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.SNOW.getDefaultState(),33));
        register(context, OLD_GROWTH_ORE_MOSSY_COBBLESTONE_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.MOSSY_COBBLESTONE.getDefaultState(),33));
        register(context, OLD_GROWTH_ORE_MOSS_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.MOSS_BLOCK.getDefaultState(),33));
        register(context, PALE_GARDEN_ORE_MOSS_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.PALE_MOSS_BLOCK.getDefaultState(),33));
        register(context, MUSHROOM_ORE_BROWN_MUSHROOM_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState(),33));
        register(context, MUSHROOM_ORE_RED_MUSHROOM_KEY, Feature.ORE, new OreFeatureConfig(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.RED_MUSHROOM_BLOCK.getDefaultState(),33));

        register(context, PILE_GREEN_PUMPKIN_KEY, Feature.BLOCK_PILE,
                new BlockPileFeatureConfig(
                        new WeightedBlockStateProvider(Pool.<BlockState>builder()
                                .add(ModBlocks.GREEN_PUMPKIN.getDefaultState(), 13)
                                .add(Blocks.PUMPKIN.getDefaultState(), 6)
                                .add(ModBlocks.GREEN_JACK_O_LANTERN.getDefaultState(), 1))
                )
        );
        register(context, PATCH_WHITE_PUMPKIN_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WHITE_PUMPKIN)), List.of(Blocks.GRASS_BLOCK, Blocks.PALE_MOSS_BLOCK, Blocks.SNOW_BLOCK)
                )
        );
        register(context, PATCH_GREEN_PUMPKIN_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(
                        Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.GREEN_PUMPKIN)), List.of(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK, Blocks.PODZOL, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS)
                )
        );

        register(context, SWAMP_WHITE_WATER_LILIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        15, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(
                                        Pool.<BlockState>builder()
                                                .add(ModBlocks.WHITE_WATER_LILY.getDefaultState(), 6)
                                                .add(ModBlocks.PINK_WATER_LILY.getDefaultState(), 1)
                                )
                        ))
                )
        );
        register(context, SWAMP_PINK_WATER_LILIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        15, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(
                                        Pool.<BlockState>builder()
                                                .add(ModBlocks.PINK_WATER_LILY.getDefaultState(), 6)
                                                .add(ModBlocks.WHITE_WATER_LILY.getDefaultState(), 1)
                                )
                        ))
                )
        );
        register(context, SWAMP_BLUE_WATER_LILIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        15, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(
                                        Pool.<BlockState>builder()
                                                .add(ModBlocks.BLUE_WATER_LILY.getDefaultState(), 6)
                                                .add(ModBlocks.PURPLE_WATER_LILY.getDefaultState(), 1)
                                )
                        ))
                )
        );
        register(context, SWAMP_PURPLE_WATER_LILIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        15, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(
                                new WeightedBlockStateProvider(
                                        Pool.<BlockState>builder()
                                                .add(ModBlocks.PURPLE_WATER_LILY.getDefaultState(), 6)
                                                .add(ModBlocks.BLUE_WATER_LILY.getDefaultState(), 1)
                                )
                        ))
                )
        );

        register(context, SWAMP_FIREFLIES_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.FIREFLY_BUSH.getDefaultState())));

        register(context, SWAMP_MUD_DISK_KEY, Feature.DISK,
                new DiskFeatureConfig(
                        PredicatedStateProvider.of(Blocks.MUD),
                        BlockPredicate.matchingBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)),
                        UniformIntProvider.create(3, 5),
                        2
                )
        );

        register(context,
                SWAMP_SURFACE_MUD_VEGETATION_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                Pool.<BlockState>builder()
                                        .add(Blocks.SHORT_GRASS.getDefaultState(), 10)
                                        .add(Blocks.TALL_GRASS.getDefaultState(), 6)
                        )
                )
        );

        register(context,
                SWAMP_SURFACE_MUD_KEY,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.DIRT,
                        BlockStateProvider.of(Blocks.MUD),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(SWAMP_SURFACE_MUD_VEGETATION_KEY)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.5F,
                        6,
                        0.2F,
                        UniformIntProvider.create(3, 5),
                        0.75F
                )
        );

        ConfiguredFeatures.register(context,
                FLOWER_PATCH_CHERRY_KEY,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        96,
                        6,
                        2,
                        PlacedFeatures.createEntry(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(
                                        new NoiseBlockStateProvider(
                                                2345L,
                                                new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0),
                                                0.020833334F,
                                                List.of(
                                                        Blocks.ALLIUM.getDefaultState(),
                                                        Blocks.AZURE_BLUET.getDefaultState(),
                                                        Blocks.WHITE_TULIP.getDefaultState(),
                                                        Blocks.PINK_TULIP.getDefaultState(),
                                                        Blocks.OXEYE_DAISY.getDefaultState(),
                                                        Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                                                        Blocks.LILAC.getDefaultState(),
                                                        Blocks.PEONY.getDefaultState(),
                                                        Blocks.ROSE_BUSH.getDefaultState()
                                                )
                                        )
                                )
                        )
                )
        );

        register(context,
                OLD_GROWTH_BIRCH_VIOLET_KEY,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(64, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(flowerbed(ModBlocks.VIOLET)))))
        );

        register(context,
                OLD_GROWTH_BIRCH_MOSS_CARPET_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                Pool.<BlockState>builder()
                                        .add(Blocks.MOSS_CARPET.getDefaultState(), 25)
                                        .add(Blocks.SHORT_GRASS.getDefaultState(), 25)
                                        .add(Blocks.BUSH.getDefaultState(), 15)
                                        .add(Blocks.TALL_GRASS.getDefaultState(), 10)
                        )
                )
        );

        register(context,
                OLD_GROWTH_BIRCH_MOSS_BLOCK_KEY,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.DIRT,
                        BlockStateProvider.of(Blocks.MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(OLD_GROWTH_BIRCH_MOSS_CARPET_KEY)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        5,
                        0.3F,
                        UniformIntProvider.create(2, 4),
                        0.75F
                )
        );

        PlaceOnGroundTreeDecorator placeOnGroundTreeDecorator = new PlaceOnGroundTreeDecorator(
                96, 4, 2, new WeightedBlockStateProvider(VegetationConfiguredFeatures.leafLitter(1, 3))
        );
        PlaceOnGroundTreeDecorator placeOnGroundTreeDecorator2 = new PlaceOnGroundTreeDecorator(
                150, 2, 2, new WeightedBlockStateProvider(VegetationConfiguredFeatures.leafLitter(1, 4))
        );

        register(context, SAPLING_BIRCH_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 6),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new HangingMushroomTreeDecorator(0.1F))).build());

        register(context, SAPLING_BIRCH_BEES_005_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 6),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new NotchedTrunkBeehiveTreeDecorator(0.05f), new HangingMushroomTreeDecorator(0.1F))).build());

        register(context, FOREST_BIRCH_LEAF_LITTER_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 0),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new HangingMushroomTreeDecorator(0.05F), placeOnGroundTreeDecorator, placeOnGroundTreeDecorator2)).build());

        register(context, FOREST_BIRCH_BEES_0002_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 0),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new NotchedTrunkBeehiveTreeDecorator(0.002f), new HangingMushroomTreeDecorator(0.05F))).build());

        register(context, FOREST_BIRCH_BEES_0002_LEAF_LITTER_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 0),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new NotchedTrunkBeehiveTreeDecorator(0.002f), new HangingMushroomTreeDecorator(0.05F), placeOnGroundTreeDecorator, placeOnGroundTreeDecorator2)).build());

        register(context, FOREST_SUPER_BIRCH_BEES_0002_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 6),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new NotchedTrunkBeehiveTreeDecorator(0.002f), new HangingMushroomTreeDecorator(0.05F))).build());

        register(context, FOREST_BIRCH_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(5, 2, 0),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new HangingMushroomTreeDecorator(0.05F))).build());

        register(context, OLD_GROWTH_SUPER_BIRCH_BEES_0002_KEY, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(Blocks.BIRCH_LOG),
                        new NotchedTrunkPlacer(9, 2, 2),
                        BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(ImmutableList.of(new NotchedTrunkBeehiveTreeDecorator(0.002f), new HangingMushroomTreeDecorator(0.2F))).build());

        register(context, MOSSY_FALLEN_SUPER_BIRCH_TREE_KEY, Feature.FALLEN_TREE,
                new FallenTreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.BIRCH_LOG), UniformIntProvider.create(5, 11))
                        .logDecorators(
                                ImmutableList.of(
                                        new AttachedToLogsTreeDecorator(
                                                0.6F,
                                                new WeightedBlockStateProvider(Pool.<BlockState>builder()
                                                        .add(Blocks.MOSS_CARPET.getDefaultState(), 5)
                                                        .add(Blocks.RED_MUSHROOM.getDefaultState(), 1)
                                                        .add(Blocks.BROWN_MUSHROOM.getDefaultState(), 1)
                                                ),
                                                List.of(Direction.UP)
                                        )
                                )
                        )
                .build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CombinedWorldgen.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
