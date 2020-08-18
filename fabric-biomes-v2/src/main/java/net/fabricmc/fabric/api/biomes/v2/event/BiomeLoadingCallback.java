package net.fabricmc.fabric.api.biomes.v2.event;

import net.fabricmc.fabric.api.biomes.v2.FabricBiomeBuilder;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public interface BiomeLoadingCallback {
	Event<BiomeLoadingCallback> EVENT = EventFactory.createArrayBacked(
			BiomeLoadingCallback.class,
			(listeners) -> (biomeRegistryKey, biomeBuilder) -> {
				for (BiomeLoadingCallback callback : listeners) {
					callback.onBiomeLoading(biomeRegistryKey, biomeBuilder);
				}
			}
	);

	void onBiomeLoading(RegistryKey<Biome> biomeRegistryKey, FabricBiomeBuilder biomeBuilder);
}
