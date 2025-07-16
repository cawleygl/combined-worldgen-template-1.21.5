package bluesteel42.combinedworldgen.world.tree;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModTreeBuilders {
    public static final TrunkPlacerType<NotchedTrunkPlacer> NOTCHED_TRUNK_PLACER =
            Registry.register(
                    Registries.TRUNK_PLACER_TYPE,
                    Identifier.of(CombinedWorldgen.MOD_ID, "notched_trunk_placer"),
                    new TrunkPlacerType<>(NotchedTrunkPlacer.CODEC)
            );

    public static final TreeDecoratorType<NotchedTrunkBeehiveTreeDecorator> NOTCHED_TRUNK_BEEHIVE_TREE_DECORATOR =
            Registry.register(
                    Registries.TREE_DECORATOR_TYPE,
                    Identifier.of(CombinedWorldgen.MOD_ID, "notched_trunk_beehive_tree_decorator"),
                    new TreeDecoratorType<>(NotchedTrunkBeehiveTreeDecorator.CODEC)
            );

    public static final TreeDecoratorType<HangingMushroomTreeDecorator> HANGING_MUSHROOM_TREE_DECORATOR =
            Registry.register(
                    Registries.TREE_DECORATOR_TYPE,
                    Identifier.of(CombinedWorldgen.MOD_ID, "hanging_mushroom_tree_decorator"),
                    new TreeDecoratorType<>(HangingMushroomTreeDecorator.CODEC)
            );

    public static void initialize() {

    }
}
