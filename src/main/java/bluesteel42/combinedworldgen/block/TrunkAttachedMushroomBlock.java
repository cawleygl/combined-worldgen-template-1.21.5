package bluesteel42.combinedworldgen.block;

import bluesteel42.combinedworldgen.property.ModProperties;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class TrunkAttachedMushroomBlock extends HorizontalFacingBlock implements Fertilizable, Segmented {
    public static final MapCodec<TrunkAttachedMushroomBlock> CODEC = createCodec(TrunkAttachedMushroomBlock::new);
    public static final IntProperty MUSHROOMS = ModProperties.MUSHROOMS;
    private final Function<BlockState, VoxelShape> shapeFunction;

    @Override
    public MapCodec<TrunkAttachedMushroomBlock> getCodec() {
        return CODEC;
    }

    protected TrunkAttachedMushroomBlock(Settings settings) {
        super(settings);
        this.shapeFunction = this.createShapeFunction();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(state.get(FACING)));
        return blockState.isIn(BlockTags.BIRCH_LOGS);
    }

    private Function<BlockState, VoxelShape> createShapeFunction() {
        Map<Direction, VoxelShape> firstMushroomMap = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(1.0, 4.0, 0.0, 7.0, 7.0, 3.0));
        Map<Direction, VoxelShape> secondMushroomMap = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(8.0, 0.0, 0.0, 14.0, 3.0, 3.0));
        Map<Direction, VoxelShape> thirdMushroomMap = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidShape(10.0, 10.0, 0.0, 16.0, 13.0, 3.0));
        return this.createShapeFunction(state -> {
        VoxelShape finalShape = VoxelShapes.empty();
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (state.get(FACING).equals(direction)) {
                finalShape = firstMushroomMap.get(direction);
                if (state.get(MUSHROOMS) > 1) {
                    finalShape = VoxelShapes.union(finalShape, secondMushroomMap.get(direction));
                }
                if (state.get(MUSHROOMS) > 2) {
                    finalShape = VoxelShapes.union(finalShape, thirdMushroomMap.get(direction));
                }
            }
        }
        return finalShape;
        });
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape)this.shapeFunction.apply(state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState targetBlockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        BlockState thisBlockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                thisBlockState = thisBlockState.with(FACING, direction);
                if (thisBlockState.canPlaceAt(worldView, blockPos)) {
                    return targetBlockState.isOf(this)
                            ? targetBlockState.with(MUSHROOMS, Math.min(3, (Integer)targetBlockState.get(MUSHROOMS) + 1))
                            : thisBlockState;
                }
            }
        }

        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        return direction == state.get(FACING) && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return (Integer)state.get(MUSHROOMS) < 3;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.4;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(MUSHROOMS, (Integer)state.get(MUSHROOMS) + 1), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, MUSHROOMS);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public IntProperty getAmountProperty() {
        return MUSHROOMS;
    }

    @Override
    public boolean shouldAddSegment(BlockState state, ItemPlacementContext context, IntProperty property) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(state.getBlock().asItem()) && (Integer)state.get(property) < 3;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return this.shouldAddSegment(state, context, MUSHROOMS) ? true : super.canReplace(state, context);
    }

    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(Items.BROWN_MUSHROOM);
    }

}
