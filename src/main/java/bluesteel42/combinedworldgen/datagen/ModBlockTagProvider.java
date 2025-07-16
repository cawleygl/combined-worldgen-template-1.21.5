package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(ModBlocks.TRUNK_ATTACHED_BROWN_MUSHROOM);

        getOrCreateTagBuilder(BlockTags.BEE_ATTRACTIVE).add(ModBlocks.VIOLET);
        getOrCreateTagBuilder(BlockTags.FLOWERS).add(ModBlocks.VIOLET);
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(ModBlocks.VIOLET);

        getOrCreateTagBuilder(BlockTags.DIRT).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.CONVERTABLE_TO_MUD).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(ModBlocks.LOOSE_DIRT);
        getOrCreateTagBuilder(BlockTags.WOLVES_SPAWNABLE_ON).add(ModBlocks.LOOSE_DIRT);

        getOrCreateTagBuilder(ModTags.Blocks.WATER_LILIES).add(ModBlocks.WHITE_WATER_LILY);
        getOrCreateTagBuilder(ModTags.Blocks.WATER_LILIES).add(ModBlocks.BLUE_WATER_LILY);
        getOrCreateTagBuilder(ModTags.Blocks.WATER_LILIES).add(ModBlocks.PINK_WATER_LILY);
        getOrCreateTagBuilder(ModTags.Blocks.WATER_LILIES).add(ModBlocks.PURPLE_WATER_LILY);

        getOrCreateTagBuilder(BlockTags.FROG_PREFER_JUMP_TO).addTag(ModTags.Blocks.WATER_LILIES);
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).addTag(ModTags.Blocks.WATER_LILIES);
        getOrCreateTagBuilder(BlockTags.BEE_ATTRACTIVE).addTag(ModTags.Blocks.WATER_LILIES);
        getOrCreateTagBuilder(BlockTags.FLOWERS).addTag(ModTags.Blocks.WATER_LILIES);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.CARVED_WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.WHITE_JACK_O_LANTERN);
        getOrCreateTagBuilder(BlockTags.CROPS).add(ModBlocks.WHITE_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.CARVED_WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(ModBlocks.WHITE_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.CARVED_WHITE_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.WHITE_JACK_O_LANTERN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.CARVED_GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(ModBlocks.GREEN_JACK_O_LANTERN);
        getOrCreateTagBuilder(BlockTags.CROPS).add(ModBlocks.GREEN_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE).add(ModBlocks.CARVED_GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(ModBlocks.GREEN_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(ModBlocks.ATTACHED_GREEN_PUMPKIN_STEM);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.CARVED_GREEN_PUMPKIN);
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(ModBlocks.GREEN_JACK_O_LANTERN);

    }
}
