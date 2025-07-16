package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import bluesteel42.combinedworldgen.entity.ModLootTables;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModShearingLootTableProvider extends SimpleFabricLootTableProvider {
    public ModShearingLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.SHEARING);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
                lootTableBiConsumer.accept(ModLootTables.WHITE_PUMPKIN_SNOW_GOLEM_SHEARING,
                        LootTable.builder()
                                .pool(LootPool.builder()
                                        .with(ItemEntry.builder(ModBlocks.CARVED_WHITE_PUMPKIN))
                                )
                );
                lootTableBiConsumer.accept(ModLootTables.GREEN_PUMPKIN_SNOW_GOLEM_SHEARING,
                        LootTable.builder()
                                .pool(LootPool.builder()
                                        .with(ItemEntry.builder(ModBlocks.CARVED_GREEN_PUMPKIN))
                                )
                );
    }
}
