package bluesteel42.combinedworldgen.block;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import bluesteel42.combinedworldgen.item.ModItemKeys;
import bluesteel42.combinedworldgen.item.ModItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    /* ORE DIRT */
    public static final Block LOOSE_DIRT = register("loose_dirt", settings -> new LooseDirtBlock(new ColorCode(9858122), Blocks.MUD, settings), AbstractBlock.Settings.copy(Blocks.DIRT), true, false);

    /* BIRCH BIOMES */
    public static final Block TRUNK_ATTACHED_BROWN_MUSHROOM = register("trunk_attached_brown_mushroom", TrunkAttachedMushroomBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BROWN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .luminance(state -> 1)
                    .postProcess(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );
    public static final Block VIOLET = register("violet", FlowerbedBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().sounds(BlockSoundGroup.FLOWERBED).pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );

    /* SWAMP BIOME */
    public static final Block WHITE_WATER_LILY = register("white_water_lily", WaterLilyBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE)
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            true
    );
    public static final Block PINK_WATER_LILY = register("pink_water_lily", WaterLilyBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK)
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            true
    );    public static final Block BLUE_WATER_LILY = register("blue_water_lily", WaterLilyBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLUE)
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            true
    );    public static final Block PURPLE_WATER_LILY = register("purple_water_lily", WaterLilyBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.PURPLE)
                    .breakInstantly()
                    .sounds(BlockSoundGroup.LILY_PAD)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            true
    );
    public static final Block WHITE_PUMPKIN_STEM = register(
            ModBlockKeys.WHITE_PUMPKIN_STEM_PATH,
            settings -> new StemBlock(ModBlockKeys.WHITE_PUMPKIN_KEY, ModBlockKeys.ATTACHED_WHITE_PUMPKIN_STEM_KEY, ModItemKeys.WHITE_PUMPKIN_SEEDS_KEY, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STEM)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );
    public static final Block ATTACHED_WHITE_PUMPKIN_STEM = register(
            ModBlockKeys.ATTACHED_WHITE_PUMPKIN_STEM_PATH,
            settings -> new AttachedStemBlock(ModBlockKeys.WHITE_PUMPKIN_STEM_KEY, ModBlockKeys.WHITE_PUMPKIN_KEY, ModItemKeys.WHITE_PUMPKIN_SEEDS_KEY, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );
    public static final Block WHITE_PUMPKIN = register(
            ModBlockKeys.WHITE_PUMPKIN_PATH,
            WhitePumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );
    public static final Block CARVED_WHITE_PUMPKIN = register(
            "carved_white_pumpkin",
            CarvedWhitePumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            false
    );
    public static final Block WHITE_JACK_O_LANTERN = register(
            "white_jack_o_lantern",
            CarvedWhitePumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .luminance(state -> 15)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );
    public static final Block GREEN_PUMPKIN_STEM = register(
            ModBlockKeys.GREEN_PUMPKIN_STEM_PATH,
            settings -> new StemBlock(ModBlockKeys.GREEN_PUMPKIN_KEY, ModBlockKeys.ATTACHED_GREEN_PUMPKIN_STEM_KEY, ModItemKeys.GREEN_PUMPKIN_SEEDS_KEY, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.STEM)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );
    public static final Block ATTACHED_GREEN_PUMPKIN_STEM = register(
            ModBlockKeys.ATTACHED_GREEN_PUMPKIN_STEM_PATH,
            settings -> new AttachedStemBlock(ModBlockKeys.GREEN_PUMPKIN_STEM_KEY, ModBlockKeys.GREEN_PUMPKIN_KEY, ModItemKeys.GREEN_PUMPKIN_SEEDS_KEY, settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            true
    );
    public static final Block GREEN_PUMPKIN = register(
            ModBlockKeys.GREEN_PUMPKIN_PATH,
            GreenPumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );
    public static final Block CARVED_GREEN_PUMPKIN = register(
            "carved_green_pumpkin",
            CarvedGreenPumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY),
            false,
            false
    );
    public static final Block GREEN_JACK_O_LANTERN = register(
            "green_jack_o_lantern",
            CarvedGreenPumpkinBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .luminance(state -> 15)
                    .allowsSpawning(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY),
            true,
            false
    );

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, boolean registerItem, boolean nonOpaqueBlock) {
        final Identifier identifier = Identifier.of(CombinedWorldgen.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);

        if (registerItem) {
            Items.register(block);
        }

        if (nonOpaqueBlock) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }

        return block;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register((itemGroup) -> {
                    itemGroup.addAfter(Items.BROWN_MUSHROOM, ModBlocks.TRUNK_ATTACHED_BROWN_MUSHROOM);
                    itemGroup.addAfter(Items.WILDFLOWERS, ModBlocks.VIOLET);
                    itemGroup.addAfter(Items.LILY_PAD, ModBlocks.BLUE_WATER_LILY);
                    itemGroup.addAfter(Items.LILY_PAD, ModBlocks.PURPLE_WATER_LILY);
                    itemGroup.addAfter(Items.LILY_PAD, ModBlocks.PINK_WATER_LILY);
                    itemGroup.addAfter(Items.LILY_PAD, ModBlocks.WHITE_WATER_LILY);
                    itemGroup.addAfter(Items.COARSE_DIRT, ModBlocks.LOOSE_DIRT);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.WHITE_JACK_O_LANTERN);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.CARVED_WHITE_PUMPKIN);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.WHITE_PUMPKIN);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.GREEN_JACK_O_LANTERN);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.CARVED_GREEN_PUMPKIN);
                    itemGroup.addAfter(Items.JACK_O_LANTERN, ModBlocks.GREEN_PUMPKIN);
                    itemGroup.addAfter(Items.PUMPKIN_SEEDS, ModItems.WHITE_PUMPKIN_SEEDS);
                    itemGroup.addAfter(Items.PUMPKIN_SEEDS, ModItems.GREEN_PUMPKIN_SEEDS);
                });
    }
}
