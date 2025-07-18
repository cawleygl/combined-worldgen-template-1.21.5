package bluesteel42.combinedworldgen.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class WaterLilyBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<WaterLilyBlock> CODEC = createCodec(WaterLilyBlock::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(14.0, 0.0, 1.5);

    @Override
    public MapCodec<WaterLilyBlock> getCodec() {
        return CODEC;
    }

    public WaterLilyBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        super.onEntityCollision(state, world, pos, entity, handler);
        if (world instanceof ServerWorld && entity instanceof AbstractBoatEntity) {
            world.breakBlock(new BlockPos(pos), true, entity);
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos);
        FluidState fluidState2 = world.getFluidState(pos.up());
        return (fluidState.getFluid() == Fluids.WATER || floor.getBlock() instanceof IceBlock) && fluidState2.getFluid() == Fluids.EMPTY;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        for (int k = 0; k < 8; k++) {
            BlockPos blockPos2 = pos.add(random.nextInt(5) - 2, 0, random.nextInt(5) - 2);
            int placeChance = random.nextInt(4);
            if (!world.isAir(blockPos2) || !state.canPlaceAt(world, blockPos2) || placeChance == 0) {
                continue;
            }

            if (placeChance == 1 || placeChance == 2) {
                world.setBlockState(blockPos2, Blocks.LILY_PAD.getDefaultState(), Block.NOTIFY_LISTENERS);
            } else {
                world.setBlockState(blockPos2, state, Block.NOTIFY_LISTENERS);
            }
        }
    }
}
