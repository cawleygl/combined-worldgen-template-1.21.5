package bluesteel42.combinedworldgen.entity;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables {
    public static RegistryKey<LootTable> WHITE_PUMPKIN_SNOW_GOLEM_SHEARING = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(CombinedWorldgen.MOD_ID, "shearing/white_pumpkin_snow_golem"));
    public static RegistryKey<LootTable> GREEN_PUMPKIN_SNOW_GOLEM_SHEARING = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(CombinedWorldgen.MOD_ID, "shearing/green_pumpkin_snow_golem"));
}