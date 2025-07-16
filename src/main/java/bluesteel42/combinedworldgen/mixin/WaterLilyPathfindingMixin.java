package bluesteel42.combinedworldgen.mixin;

import bluesteel42.combinedworldgen.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LandPathNodeMaker.class)
public class WaterLilyPathfindingMixin {
    @Inject(at = @At("HEAD"), method = "getCommonNodeType", cancellable = true)
    private static void init(BlockView world, BlockPos pos, CallbackInfoReturnable<PathNodeType> cir) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.isIn(ModTags.Blocks.WATER_LILIES) ) {
            cir.setReturnValue(PathNodeType.TRAPDOOR);
            cir.cancel();
        }
    }
}
