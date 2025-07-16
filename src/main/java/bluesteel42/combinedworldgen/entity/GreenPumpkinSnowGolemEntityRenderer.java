package bluesteel42.combinedworldgen.entity;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class GreenPumpkinSnowGolemEntityRenderer extends MobEntityRenderer<GreenPumpkinSnowGolemEntity, GreenPumpkinSnowGolemEntityRenderState, GreenPumpkinSnowGolemEntityModel> {
    private static final Identifier TEXTURE = Identifier.of(CombinedWorldgen.MOD_ID, "textures/entity/green_pumpkin_snow_golem.png");

    public GreenPumpkinSnowGolemEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GreenPumpkinSnowGolemEntityModel(context.getPart(EntityModelLayers.SNOW_GOLEM)), 0.5F);
        this.addFeature(new GreenPumpkinSnowGolemPumpkinFeatureRenderer(this, context.getBlockRenderManager()));
    }

    public Identifier getTexture(GreenPumpkinSnowGolemEntityRenderState snowGolemEntityRenderState) {
        return TEXTURE;
    }

    public GreenPumpkinSnowGolemEntityRenderState createRenderState() {
        return new GreenPumpkinSnowGolemEntityRenderState();
    }

    public void updateRenderState(GreenPumpkinSnowGolemEntity snowGolemEntity, GreenPumpkinSnowGolemEntityRenderState snowGolemEntityRenderState, float f) {
        super.updateRenderState(snowGolemEntity, snowGolemEntityRenderState, f);
        snowGolemEntityRenderState.hasPumpkin = snowGolemEntity.hasPumpkin();
    }
}
