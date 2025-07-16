package bluesteel42.combinedworldgen.item;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItemKeys {
    public static final String WHITE_PUMPKIN_SEEDS_PATH = "white_pumpkin_seeds";
    public static final String GREEN_PUMPKIN_SEEDS_PATH = "green_pumpkin_seeds";
    public static final RegistryKey<Item> WHITE_PUMPKIN_SEEDS_KEY = of(WHITE_PUMPKIN_SEEDS_PATH);
    public static final RegistryKey<Item> GREEN_PUMPKIN_SEEDS_KEY = of(GREEN_PUMPKIN_SEEDS_PATH);

    private static RegistryKey<Item> of(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CombinedWorldgen.MOD_ID, id));
    }
}
