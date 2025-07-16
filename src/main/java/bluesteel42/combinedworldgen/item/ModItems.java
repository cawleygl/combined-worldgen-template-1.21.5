package bluesteel42.combinedworldgen.item;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import bluesteel42.combinedworldgen.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ModItems {
    public static final Item WHITE_WATER_LILY = Items.register(ModBlocks.WHITE_WATER_LILY, PlaceableOnWaterItem::new);
    public static final Item PINK_WATER_LILY = Items.register(ModBlocks.PINK_WATER_LILY, PlaceableOnWaterItem::new);
    public static final Item BLUE_WATER_LILY = Items.register(ModBlocks.BLUE_WATER_LILY, PlaceableOnWaterItem::new);
    public static final Item PURPLE_WATER_LILY = Items.register(ModBlocks.PURPLE_WATER_LILY, PlaceableOnWaterItem::new);
    public static final Item WHITE_PUMPKIN_SEEDS = Items.register(ModItemKeys.WHITE_PUMPKIN_SEEDS_KEY, createBlockItemWithUniqueName(ModBlocks.WHITE_PUMPKIN_STEM));
    public static final Item GREEN_PUMPKIN_SEEDS = Items.register(ModItemKeys.GREEN_PUMPKIN_SEEDS_KEY, createBlockItemWithUniqueName(ModBlocks.GREEN_PUMPKIN_STEM));

    public static final Item CARVED_WHITE_PUMPKIN = Items.register(
            Blocks.CARVED_PUMPKIN,
            (UnaryOperator<Item.Settings>)(settings -> settings.component(
                    DataComponentTypes.EQUIPPABLE,
                    EquippableComponent.builder(EquipmentSlot.HEAD).swappable(false).cameraOverlay(Identifier.of(CombinedWorldgen.MOD_ID, "misc/white_pumpkinblur")).build()
            ))
    );

    public static final Item CARVED_GREEN_PUMPKIN = Items.register(
            Blocks.CARVED_PUMPKIN,
            (UnaryOperator<Item.Settings>)(settings -> settings.component(
                    DataComponentTypes.EQUIPPABLE,
                    EquippableComponent.builder(EquipmentSlot.HEAD).swappable(false).cameraOverlay(Identifier.of(CombinedWorldgen.MOD_ID,"misc/green_pumpkinblur")).build()
            ))
    );

    private static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    public static void initialize() {}
}
