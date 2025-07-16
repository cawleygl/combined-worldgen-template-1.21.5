package bluesteel42.combinedworldgen.entity;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class WhitePumpkinSnowGolemEntityRenderer extends MobEntityRenderer<WhitePumpkinSnowGolemEntity, WhitePumpkinSnowGolemEntityRenderState, WhitePumpkinSnowGolemEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(CombinedWorldgen.MOD_ID, "textures/entity/white_pumpkin_snow_golem.png");

    public WhitePumpkinSnowGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WhitePumpkinSnowGolemEntityModel(context.getPart(EntityModelLayers.SNOW_GOLEM)), 0.5F);
        this.addFeature(new WhitePumpkinSnowGolemPumpkinFeatureRenderer(this, context.getBlockRenderManager()));
    }

    public Identifier getTexture(WhitePumpkinSnowGolemEntityRenderState snowGolemEntityRenderState) {
        return TEXTURE;
    }

    public WhitePumpkinSnowGolemEntityRenderState createRenderState() {
        return new WhitePumpkinSnowGolemEntityRenderState();
    }

    public void updateRenderState(WhitePumpkinSnowGolemEntity snowGolemEntity, WhitePumpkinSnowGolemEntityRenderState snowGolemEntityRenderState, float f) {
        super.updateRenderState(snowGolemEntity, snowGolemEntityRenderState, f);
        snowGolemEntityRenderState.hasPumpkin = snowGolemEntity.hasPumpkin();
    }
}
