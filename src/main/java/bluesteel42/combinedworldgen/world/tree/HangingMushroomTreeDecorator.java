package bluesteel42.combinedworldgen.world.tree;

import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.block.TrunkAttachedMushroomBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;

public class HangingMushroomTreeDecorator extends TreeDecorator {
    public static final MapCodec<HangingMushroomTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(HangingMushroomTreeDecorator::new, decorator -> decorator.probability);
    private final float probability;

    public HangingMushroomTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeBuilders.HANGING_MUSHROOM_TREE_DECORATOR;
    }

    @Override
    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            List<BlockPos> list = generator.getLogPositions();
            if (!list.isEmpty()) {
                int i = ((BlockPos)list.getFirst()).getY();
                list.stream().filter(pos -> pos.getY() - i > 1).forEach(pos -> {
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        if (random.nextFloat() <= 0.1F) {
                            Direction direction2 = direction.getOpposite();
                            BlockPos blockPos = pos.add(direction2.getOffsetX(), 0, direction2.getOffsetZ());
                            if (generator.isAir(blockPos)) {
                                generator.replace(blockPos, ModBlocks.TRUNK_ATTACHED_BROWN_MUSHROOM.getDefaultState().with(TrunkAttachedMushroomBlock.MUSHROOMS, random.nextInt(3) + 1).with(TrunkAttachedMushroomBlock.FACING, direction));
                            }
                        }
                    }
                });
            }
        }
    }
}
