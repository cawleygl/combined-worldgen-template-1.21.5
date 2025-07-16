package bluesteel42.combinedworldgen.entity;

import bluesteel42.combinedworldgen.CombinedWorldgen;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<WhitePumpkinSnowGolemEntity> WHITE_PUMPKIN_SNOW_GOLEM = registerWhitePumpkinSnowGolem();
    public static final EntityType<GreenPumpkinSnowGolemEntity> GREEN_PUMPKIN_SNOW_GOLEM = registerGreenPumpkinSnowGolem();

    public static EntityType<WhitePumpkinSnowGolemEntity> registerWhitePumpkinSnowGolem() {
        final Identifier identifier = Identifier.of(CombinedWorldgen.MOD_ID, "white_pumpkin_snow_golem");
        final RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier);

        return Registry.register(
                Registries.ENTITY_TYPE,
                identifier,
                EntityType.Builder.create(WhitePumpkinSnowGolemEntity::new, SpawnGroup.MISC)
                        .allowSpawningInside(Blocks.POWDER_SNOW)
                        .dimensions(0.7F, 1.9F)
                        .eyeHeight(1.7F)
                        .maxTrackingRange(8)
                        .build(registryKey)
        );
    }
    public static EntityType<GreenPumpkinSnowGolemEntity> registerGreenPumpkinSnowGolem() {
        final Identifier identifier = Identifier.of(CombinedWorldgen.MOD_ID, "green_pumpkin_snow_golem");
        final RegistryKey<EntityType<?>> registryKey = RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier);

        return Registry.register(
                Registries.ENTITY_TYPE,
                identifier,
                EntityType.Builder.create(GreenPumpkinSnowGolemEntity::new, SpawnGroup.MISC)
                        .allowSpawningInside(Blocks.POWDER_SNOW)
                        .dimensions(0.7F, 1.9F)
                        .eyeHeight(1.7F)
                        .maxTrackingRange(8)
                        .build(registryKey)
        );
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(WHITE_PUMPKIN_SNOW_GOLEM, WhitePumpkinSnowGolemEntity.createSnowGolemAttributes());
        FabricDefaultAttributeRegistry.register(GREEN_PUMPKIN_SNOW_GOLEM, GreenPumpkinSnowGolemEntity.createSnowGolemAttributes());
    }
}
