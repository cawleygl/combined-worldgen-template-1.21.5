package bluesteel42.combinedworldgen.mixin;

import bluesteel42.combinedworldgen.block.ModBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ShovelItem.class)
public class LooseDirtToPathMixin {
	@Unique
	private static final Map<Block, BlockState> PATH_STATES = Maps.<Block, BlockState>newHashMap(
			new ImmutableMap.Builder<Block, BlockState>()
					.put(ModBlocks.LOOSE_DIRT, Blocks.DIRT_PATH.getDefaultState())
					.build()
	);

	@Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
	private void init(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.getBlock().getDefaultState().isOf(ModBlocks.LOOSE_DIRT)) {
			if (context.getSide() == Direction.DOWN) {
				cir.setReturnValue(ActionResult.PASS);
				cir.cancel();
			} else {
				PlayerEntity playerEntity = context.getPlayer();
				BlockState blockState2 = (BlockState) PATH_STATES.get(blockState.getBlock());
				BlockState blockState3 = null;
				if (blockState2 != null && world.getBlockState(blockPos.up()).isAir()) {
					world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
					blockState3 = blockState2;
				}
				if (blockState3 != null) {
					if (!world.isClient) {
						world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL_AND_REDRAW);
						world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, blockState3));
						if (playerEntity != null) {
							context.getStack().damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
						}
					}
					cir.setReturnValue(ActionResult.SUCCESS);
					cir.cancel();
				} else {
					cir.setReturnValue(ActionResult.PASS);
					cir.cancel();
				}
			}
		}
	}
}