package bluesteel42.combinedworldgen.world.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraft.state.property.Properties;
import java.util.List;
import java.util.function.BiConsumer;

public class NotchedTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<NotchedTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> fillTrunkPlacerFields(instance).apply(instance, NotchedTrunkPlacer::new)
    );

    public NotchedTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModTreeBuilders.NOTCHED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config
    ) {
        setToDirt(world, replacer, random, startPos.down(), config);

        int branch = random.nextInt(height - 3);
        int branchChance = random.nextInt(4);

        for (int i = 0; i < height; i++) {
            BlockPos pos = startPos.up(i);
            if (height > 7 && branchChance == 0 && i == branch) {
                List<Direction> xDirections = Direction.Type.HORIZONTAL.getShuffled(random);
                if (this.getAndSetState(world, replacer, random, pos, config)) {
                    Direction branchDirection = xDirections.getFirst();
                    BlockPos posOffset = pos.offset(branchDirection);
                    replacer.accept(posOffset, config.trunkProvider.get(random, posOffset).with(Properties.AXIS, branchDirection.getAxis()));
                    this.getAndSetState(world, replacer, random, posOffset, config);
                }
            } else {
                this.getAndSetState(world, replacer, random, pos, config);
            }

        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false));
    }
}
