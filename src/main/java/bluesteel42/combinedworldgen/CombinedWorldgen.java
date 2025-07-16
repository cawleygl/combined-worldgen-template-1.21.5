package bluesteel42.combinedworldgen;

import bluesteel42.combinedworldgen.block.ModBlocks;
import bluesteel42.combinedworldgen.entity.ModEntities;
import bluesteel42.combinedworldgen.item.ModItems;
import bluesteel42.combinedworldgen.world.gen.ModWorldGeneration;
import bluesteel42.combinedworldgen.world.tree.ModTreeBuilders;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CombinedWorldgen implements ModInitializer {
	public static final String MOD_ID = "combined-worldgen";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlocks.initialize();
		ModItems.initialize();
		ModEntities.initialize();
		ModWorldGeneration.generateModWorldGen();
		ModTreeBuilders.initialize();
		LOGGER.info("Hello Fabric world!");
	}
}