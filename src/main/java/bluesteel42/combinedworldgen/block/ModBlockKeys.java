package bluesteel42.combinedworldgen.block;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlockKeys {
    public static final String WHITE_PUMPKIN_PATH = "white_pumpkin";
    public static final String WHITE_PUMPKIN_STEM_PATH = "white_pumpkin_stem";
    public static final String ATTACHED_WHITE_PUMPKIN_STEM_PATH = "attached_white_pumpkin_stem";
    public static final String GREEN_PUMPKIN_PATH = "green_pumpkin";
    public static final String GREEN_PUMPKIN_STEM_PATH = "green_pumpkin_stem";
    public static final String ATTACHED_GREEN_PUMPKIN_STEM_PATH = "attached_green_pumpkin_stem";

    public static final RegistryKey<Block> WHITE_PUMPKIN_KEY = of(WHITE_PUMPKIN_PATH);
    public static final RegistryKey<Block> WHITE_PUMPKIN_STEM_KEY = of(WHITE_PUMPKIN_STEM_PATH);
    public static final RegistryKey<Block> ATTACHED_WHITE_PUMPKIN_STEM_KEY = of(ATTACHED_WHITE_PUMPKIN_STEM_PATH);
    public static final RegistryKey<Block> GREEN_PUMPKIN_KEY = of(GREEN_PUMPKIN_PATH);
    public static final RegistryKey<Block> GREEN_PUMPKIN_STEM_KEY = of(GREEN_PUMPKIN_STEM_PATH);
    public static final RegistryKey<Block> ATTACHED_GREEN_PUMPKIN_STEM_KEY = of(ATTACHED_GREEN_PUMPKIN_STEM_PATH);

    private static RegistryKey<Block> of(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CombinedWorldgen.MOD_ID, id));
    }
}
