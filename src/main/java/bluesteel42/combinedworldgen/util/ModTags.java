package bluesteel42.combinedworldgen.util;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> WATER_LILIES = createTag("water_lilies");

        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CombinedWorldgen.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> WATER_LILIES = createTag("water_lilies");
        public static final TagKey<Item> PUMPKINS = createTag("pumpkins");

        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CombinedWorldgen.MOD_ID, name));
        }
    }
}
