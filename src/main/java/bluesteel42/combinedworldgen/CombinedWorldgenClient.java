package bluesteel42.combinedworldgen;

import bluesteel42.combinedworldgen.entity.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.StemBlock;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import bluesteel42.combinedworldgen.block.ModBlocks;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.GrassColors;

public class CombinedWorldgenClient implements ClientModInitializer {
    public static final EntityModelLayer WHITE_MAIN_MODEL_LAYER = new EntityModelLayer(Identifier.of(CombinedWorldgen.MOD_ID, "white_pumpkin_snow_golem"), "main");
    public static final EntityModelLayer GREEN_MAIN_MODEL_LAYER = new EntityModelLayer(Identifier.of(CombinedWorldgen.MOD_ID, "green_pumpkin_snow_golem"), "main");
    @Override
    public void onInitializeClient() {
        // Color Violet Stems
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (view == null || pos == null) {
                return GrassColors.getDefaultColor();
            }
            return BiomeColors.getGrassColor(view, pos);
        }, ModBlocks.VIOLET);

        // Color Water Lilies
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BlockColors.PLACED_LILY_PAD, ModBlocks.WHITE_WATER_LILY);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BlockColors.PLACED_LILY_PAD, ModBlocks.BLUE_WATER_LILY);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BlockColors.PLACED_LILY_PAD, ModBlocks.PINK_WATER_LILY);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BlockColors.PLACED_LILY_PAD, ModBlocks.PURPLE_WATER_LILY);

        // Color Pumpkin Stems
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int i = (Integer)state.get(StemBlock.AGE);
            return ColorHelper.getArgb(i * 32, 255 - i * 8, i * 4);
        }, ModBlocks.GREEN_PUMPKIN_STEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> -2046180, ModBlocks.ATTACHED_GREEN_PUMPKIN_STEM);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int i = (Integer)state.get(StemBlock.AGE);
            return ColorHelper.getArgb(120 + (i * 65) / 7, 141 + (i * 44) / 7,  59  + (i * 126) / 7);
        }, ModBlocks.WHITE_PUMPKIN_STEM);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> ColorHelper.getArgb(185, 185, 185), ModBlocks.ATTACHED_WHITE_PUMPKIN_STEM);

        // Register Snow Golems
        EntityRendererRegistry.register(ModEntities.WHITE_PUMPKIN_SNOW_GOLEM, WhitePumpkinSnowGolemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.GREEN_PUMPKIN_SNOW_GOLEM, GreenPumpkinSnowGolemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(WHITE_MAIN_MODEL_LAYER, WhitePumpkinSnowGolemEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GREEN_MAIN_MODEL_LAYER, GreenPumpkinSnowGolemEntityModel::getTexturedModelData);
    }
}
