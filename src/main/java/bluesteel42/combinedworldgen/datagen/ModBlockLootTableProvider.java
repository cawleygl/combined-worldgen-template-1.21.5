package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.item.ModItems;
import bluesteel42.combinedworldgen.property.ModProperties;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.StringIdentifiable;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public <T extends Comparable<T> & StringIdentifiable> LootTable.Builder trunkAttachedMushroomBlockDrops(Block attachedMushroom, Block mushroomItem) {
        return LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(mushroomItem)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3))
                                    .conditionally(BlockStatePropertyLootCondition.builder(attachedMushroom).properties(StatePredicate.Builder.create().exactMatch(ModProperties.MUSHROOMS, 3))))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2))
                                    .conditionally(BlockStatePropertyLootCondition.builder(attachedMushroom).properties(StatePredicate.Builder.create().exactMatch(ModProperties.MUSHROOMS, 2))))
                                .apply(ExplosionDecayLootFunction.builder())
                        )
        );
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TRUNK_ATTACHED_BROWN_MUSHROOM, trunkAttachedMushroomBlockDrops(ModBlocks.TRUNK_ATTACHED_BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM));
        addDrop(ModBlocks.LOOSE_DIRT);
        addDrop(ModBlocks.WHITE_WATER_LILY);
        addDrop(ModBlocks.BLUE_WATER_LILY);
        addDrop(ModBlocks.PINK_WATER_LILY);
        addDrop(ModBlocks.PURPLE_WATER_LILY);
        addDrop(ModBlocks.WHITE_PUMPKIN);
        addDrop(ModBlocks.CARVED_WHITE_PUMPKIN);
        addDrop(ModBlocks.WHITE_JACK_O_LANTERN);
        addDrop(ModBlocks.WHITE_PUMPKIN_STEM, cropStemDrops(ModBlocks.WHITE_PUMPKIN_STEM, ModItems.WHITE_PUMPKIN_SEEDS));
        addDrop(ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM, attachedCropStemDrops(ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM, ModItems.WHITE_PUMPKIN_SEEDS));
        addDrop(ModBlocks.GREEN_PUMPKIN);
        addDrop(ModBlocks.CARVED_GREEN_PUMPKIN);
        addDrop(ModBlocks.GREEN_JACK_O_LANTERN);
        addDrop(ModBlocks.GREEN_PUMPKIN_STEM, cropStemDrops(ModBlocks.GREEN_PUMPKIN_STEM, ModItems.GREEN_PUMPKIN_SEEDS));
        addDrop(ModBlocks.ATTACHED_GREEN_PUMPKIN_STEM, attachedCropStemDrops(ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM, ModItems.WHITE_PUMPKIN_SEEDS));
    }
}
