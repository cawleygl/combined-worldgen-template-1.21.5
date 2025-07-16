package bluesteel42.combinedworldgen.datagen;

import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.item.ModItems;
import bluesteel42.combinedworldgen.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.FLOWERS).add(ModBlocks.VIOLET.asItem());
        getOrCreateTagBuilder(ItemTags.BEE_FOOD).add(ModBlocks.VIOLET.asItem());
        getOrCreateTagBuilder(ItemTags.DIRT).add(ModBlocks.LOOSE_DIRT.asItem());

        getOrCreateTagBuilder(ModTags.Items.WATER_LILIES).add(ModBlocks.WHITE_WATER_LILY.asItem());
        getOrCreateTagBuilder(ModTags.Items.WATER_LILIES).add(ModBlocks.BLUE_WATER_LILY.asItem());
        getOrCreateTagBuilder(ModTags.Items.WATER_LILIES).add(ModBlocks.PINK_WATER_LILY.asItem());
        getOrCreateTagBuilder(ModTags.Items.WATER_LILIES).add(ModBlocks.PURPLE_WATER_LILY.asItem());

        getOrCreateTagBuilder(ItemTags.FLOWERS).addTag(ModTags.Items.WATER_LILIES);
        getOrCreateTagBuilder(ItemTags.BEE_FOOD).addTag(ModTags.Items.WATER_LILIES);

        getOrCreateTagBuilder(ModTags.Items.PUMPKINS).add(Blocks.PUMPKIN.asItem());
        getOrCreateTagBuilder(ModTags.Items.PUMPKINS).add(ModBlocks.WHITE_PUMPKIN.asItem());
        getOrCreateTagBuilder(ModTags.Items.PUMPKINS).add(ModBlocks.GREEN_PUMPKIN.asItem());

        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(ModItems.WHITE_PUMPKIN_SEEDS);
        getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(ModItems.WHITE_PUMPKIN_SEEDS);
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE).add(ModBlocks.CARVED_WHITE_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).add(ModBlocks.CARVED_WHITE_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.GAZE_DISGUISE_EQUIPMENT).add(ModBlocks.CARVED_WHITE_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.MAP_INVISIBILITY_EQUIPMENT).add(ModBlocks.CARVED_WHITE_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(ModItems.GREEN_PUMPKIN_SEEDS);
        getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(ModItems.GREEN_PUMPKIN_SEEDS);
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE).add(ModBlocks.CARVED_GREEN_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).add(ModBlocks.CARVED_GREEN_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.GAZE_DISGUISE_EQUIPMENT).add(ModBlocks.CARVED_GREEN_PUMPKIN.asItem());
        getOrCreateTagBuilder(ItemTags.MAP_INVISIBILITY_EQUIPMENT).add(ModBlocks.CARVED_GREEN_PUMPKIN.asItem());

    }
}
