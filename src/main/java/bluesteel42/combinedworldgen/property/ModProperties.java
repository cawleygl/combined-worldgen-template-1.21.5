package bluesteel42.combinedworldgen.property;

import net.minecraft.state.property.IntProperty;

public class ModProperties {
    /**
     * A property that specifies the amount of mushrooms attached to a birch trunk/log.
     */
    public static final IntProperty MUSHROOMS = IntProperty.of("mushrooms", 1, 3);
}
