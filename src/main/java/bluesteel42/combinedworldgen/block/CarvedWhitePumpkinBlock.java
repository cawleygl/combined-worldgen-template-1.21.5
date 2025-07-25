package bluesteel42.combinedworldgen.block;

import bluesteel42.combinedworldgen.entity.ModEntities;
import bluesteel42.combinedworldgen.entity.WhitePumpkinSnowGolemEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class CarvedWhitePumpkinBlock extends HorizontalFacingBlock {
    public static final MapCodec<CarvedWhitePumpkinBlock> CODEC = createCodec(CarvedWhitePumpkinBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    @Nullable
    private BlockPattern snowGolemDispenserPattern;
    @Nullable
    private BlockPattern snowGolemPattern;
    @Nullable
    private BlockPattern ironGolemDispenserPattern;
    @Nullable
    private BlockPattern ironGolemPattern;
    private static final Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE = state -> state != null
            && (state.isOf(ModBlocks.CARVED_WHITE_PUMPKIN) || state.isOf(ModBlocks.WHITE_JACK_O_LANTERN));

    @Override
    public MapCodec<? extends CarvedWhitePumpkinBlock> getCodec() {
        return CODEC;
    }

    public CarvedWhitePumpkinBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.trySpawnEntity(world, pos);
        }
    }

    public boolean canDispense(WorldView world, BlockPos pos) {
        return this.getSnowGolemDispenserPattern().searchAround(world, pos) != null || this.getIronGolemDispenserPattern().searchAround(world, pos) != null;
    }

    private void trySpawnEntity(World world, BlockPos pos) {
        BlockPattern.Result result = this.getSnowGolemPattern().searchAround(world, pos);
        if (result != null) {
            WhitePumpkinSnowGolemEntity snowGolemEntity = ModEntities.WHITE_PUMPKIN_SNOW_GOLEM.create(world, SpawnReason.TRIGGERED);
            if (snowGolemEntity != null) {
                spawnEntity(world, result, snowGolemEntity, result.translate(0, 2, 0).getBlockPos());
            }
        } else {
            BlockPattern.Result result2 = this.getIronGolemPattern().searchAround(world, pos);
            if (result2 != null) {
                IronGolemEntity ironGolemEntity = EntityType.IRON_GOLEM.create(world, SpawnReason.TRIGGERED);
                if (ironGolemEntity != null) {
                    ironGolemEntity.setPlayerCreated(true);
                    spawnEntity(world, result2, ironGolemEntity, result2.translate(1, 2, 0).getBlockPos());
                }
            }
        }
    }

    private static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        breakPatternBlocks(world, patternResult);
        entity.refreshPositionAndAngles(pos.getX() + 0.5, pos.getY() + 0.05, pos.getZ() + 0.5, 0.0F, 0.0F);
        world.spawnEntity(entity);

        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }

        updatePatternBlocks(world, patternResult);
    }

    public static void breakPatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); i++) {
            for (int j = 0; j < patternResult.getHeight(); j++) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }
        }
    }

    public static void updatePatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); i++) {
            for (int j = 0; j < patternResult.getHeight(); j++) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.updateNeighbors(cachedBlockPosition.getBlockPos(), Blocks.AIR);
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    private BlockPattern getSnowGolemDispenserPattern() {
        if (this.snowGolemDispenserPattern == null) {
            this.snowGolemDispenserPattern = BlockPatternBuilder.start()
                    .aisle(" ", "#", "#")
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build();
        }

        return this.snowGolemDispenserPattern;
    }

    private BlockPattern getSnowGolemPattern() {
        if (this.snowGolemPattern == null) {
            this.snowGolemPattern = BlockPatternBuilder.start()
                    .aisle("^", "#", "#")
                    .where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.SNOW_BLOCK)))
                    .build();
        }

        return this.snowGolemPattern;
    }

    private BlockPattern getIronGolemDispenserPattern() {
        if (this.ironGolemDispenserPattern == null) {
            this.ironGolemDispenserPattern = BlockPatternBuilder.start()
                    .aisle("~ ~", "###", "~#~")
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~', pos -> pos.getBlockState().isAir())
                    .build();
        }

        return this.ironGolemDispenserPattern;
    }

    private BlockPattern getIronGolemPattern() {
        if (this.ironGolemPattern == null) {
            this.ironGolemPattern = BlockPatternBuilder.start()
                    .aisle("~^~", "###", "~#~")
                    .where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.IRON_BLOCK)))
                    .where('~', pos -> pos.getBlockState().isAir())
                    .build();
        }

        return this.ironGolemPattern;
    }
}
