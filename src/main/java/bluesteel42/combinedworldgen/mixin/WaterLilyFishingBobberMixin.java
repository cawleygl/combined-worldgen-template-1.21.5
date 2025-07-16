package bluesteel42.combinedworldgen.mixin;

import bluesteel42.combinedworldgen.entity.PositionType;
import bluesteel42.combinedworldgen.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public class WaterLilyFishingBobberMixin {
    @Inject(at = @At("HEAD"), method = "getPositionType*", cancellable = true)
    private void init(BlockView world, BlockPos pos, CallbackInfoReturnable<PositionType> cir) {
        FishingBobberEntity thisObject = (FishingBobberEntity) (Object) this;
        BlockState blockState = thisObject.getWorld().getBlockState(pos);
        if (blockState.isIn(ModTags.Blocks.WATER_LILIES)) {
            cir.setReturnValue(PositionType.ABOVE_WATER);
            cir.cancel();
        }
    }
}
