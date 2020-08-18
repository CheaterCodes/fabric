package net.fabricmc.fabric.api.biomes.v2;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.mixin.registry.DynamicRegistryManagerAccessor;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class FabricBiomes {
	public void register(RegistryKey<Biome> key, Biome biome) {
		getRegistry().add(key, biome, Lifecycle.stable());
	}

	public MutableRegistry<Biome> getRegistry() {
		return DynamicRegistryManagerAccessor.getBuiltin().get(Registry.BIOME_KEY);
	}
}
