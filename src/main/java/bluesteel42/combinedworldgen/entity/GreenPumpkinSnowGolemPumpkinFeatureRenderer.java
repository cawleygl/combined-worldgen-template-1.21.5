package bluesteel42.combinedworldgen.entity;

import bluesteel42.combinedworldgen.block.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class GreenPumpkinSnowGolemPumpkinFeatureRenderer extends FeatureRenderer<GreenPumpkinSnowGolemEntityRenderState, GreenPumpkinSnowGolemEntityModel> {
    private final BlockRenderManager blockRenderManager;

    public GreenPumpkinSnowGolemPumpkinFeatureRenderer(FeatureRendererContext<GreenPumpkinSnowGolemEntityRenderState, GreenPumpkinSnowGolemEntityModel> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, GreenPumpkinSnowGolemEntityRenderState snowGolemEntityRenderState, float f, float g
    ) {
        if (snowGolemEntityRenderState.hasPumpkin) {
            if (!snowGolemEntityRenderState.invisible || snowGolemEntityRenderState.hasOutline) {
                matrixStack.push();
                this.getContextModel().getHead().applyTransform(matrixStack);
                float h = 0.625F;
                matrixStack.translate(0.0F, -0.34375F, 0.0F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                matrixStack.scale(0.625F, -0.625F, -0.625F);
                BlockState blockState = ModBlocks.CARVED_GREEN_PUMPKIN.getDefaultState();
                BlockStateModel blockStateModel = this.blockRenderManager.getModel(blockState);
                int j = LivingEntityRenderer.getOverlay(snowGolemEntityRenderState, 0.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                VertexConsumer vertexConsumer = snowGolemEntityRenderState.hasOutline && snowGolemEntityRenderState.invisible
                        ? vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE))
                        : vertexConsumerProvider.getBuffer(RenderLayers.getEntityBlockLayer(blockState));
                BlockModelRenderer.render(matrixStack.peek(), vertexConsumer, blockStateModel, 0.0F, 0.0F, 0.0F, i, j);
                matrixStack.pop();
            }
        }
    }
}
