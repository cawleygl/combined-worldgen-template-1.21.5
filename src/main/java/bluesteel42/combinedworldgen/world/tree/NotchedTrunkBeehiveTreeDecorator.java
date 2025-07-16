package bluesteel42.combinedworldgen.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NotchedTrunkBeehiveTreeDecorator extends TreeDecorator {
    public static final MapCodec<NotchedTrunkBeehiveTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
            .fieldOf("probability")
            .xmap(NotchedTrunkBeehiveTreeDecorator::new, decorator -> decorator.probability);
    private static final Direction BEE_NEST_FACE = Direction.SOUTH;
    private static final Direction[] GENERATE_DIRECTIONS = (Direction[])Direction.Type.HORIZONTAL
            .stream()
            .filter(direction -> direction != BEE_NEST_FACE.getOpposite())
            .toArray(Direction[]::new);
    private final float probability;

    public NotchedTrunkBeehiveTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeBuilders.NOTCHED_TRUNK_BEEHIVE_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        List<BlockPos> list = generator.getLeavesPositions();
        List<BlockPos> list2 = generator.getLogPositions();

        if (!list2.isEmpty()) {
            // Count how many times each x and z appear
            Map<Integer, Long> xCounts = list2.stream().collect(Collectors.groupingBy(BlockPos::getX, Collectors.counting()));
            Map<Integer, Long> zCounts = list2.stream().collect(Collectors.groupingBy(BlockPos::getZ, Collectors.counting()));

            // Find the outlier (only one of its x or z value appears once)
            Optional<BlockPos> outlierOpt = list2.stream().filter(pos ->
                    xCounts.get(pos.getX()) == 1L || zCounts.get(pos.getZ()) == 1L
            ).findFirst();

            Random random = generator.getRandom();
            if (!(random.nextFloat() >= this.probability)) {
                int i = !list.isEmpty()
                        ? Math.max(((BlockPos)list.getFirst()).getY() - 1, ((BlockPos)list2.getFirst()).getY() + 1)
                        : Math.min(((BlockPos)list2.getFirst()).getY() + 1 + random.nextInt(3), ((BlockPos)list2.getLast()).getY());
                List<BlockPos> list3 = (List<BlockPos>)list2.stream()
                        .filter(pos -> pos.getY() == i)
                        .flatMap(pos -> Stream.of(GENERATE_DIRECTIONS).map(pos::offset))
                        .collect(Collectors.toList());

                if (!list3.isEmpty()) {
                    Util.shuffle(list3, random);
                    if (outlierOpt.isPresent()) {
                        int lowestLogY = list2.stream().mapToInt(BlockPos::getY).min().getAsInt();
                        BlockPos outlier = outlierOpt.get();
                        BlockPos belowOutlier = outlier.down();
                        if (belowOutlier.getY() - lowestLogY > 4 && generator.isAir(belowOutlier)) {
                            if (outlier.getY() < i - 1) {
                                list3.add(belowOutlier);
                                Util.shuffle(list3, random);
                            } else {
                                // Prioritize outlier if it is in the highest position
                                list3.addFirst(belowOutlier);
                            }
                        }
                    }

                    Optional<BlockPos> optional = list3.stream().filter(pos -> generator.isAir(pos) && generator.isAir(pos.offset(BEE_NEST_FACE))).findFirst();
                    if (!optional.isEmpty()) {
                        generator.replace((BlockPos)optional.get(), Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, BEE_NEST_FACE));
                        generator.getWorld().getBlockEntity((BlockPos)optional.get(), BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
                            int ix = 2 + random.nextInt(2);

                            for (int j = 0; j < ix; j++) {
                                blockEntity.addBee(BeehiveBlockEntity.BeeData.create(random.nextInt(599)));
                            }
                        });
                    }
                }
            }
        }
    }
}
