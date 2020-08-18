package net.fabricmc.fabric.impl.biome;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.biomes.v2.FabricBiomeBuilder;
import net.fabricmc.fabric.api.biomes.v2.event.BiomeLoadingCallback;
import net.minecraft.util.registry.*;
import net.minecraft.world.biome.Biome;

import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public class FabricBiomeUtil {
	public static void transformBiomes(DynamicRegistryManager.Impl manager) {
		MutableRegistry<Biome> biomes = manager.get(Registry.BIOME_KEY);
		Set<Map.Entry<RegistryKey<Biome>, Biome>> entries = ImmutableSet.copyOf(biomes.getEntries());
		for (Map.Entry<RegistryKey<Biome>, Biome> entry : entries) {
			int rawId = biomes.getRawId(entry.getValue());
			Biome newBiome = transformBiome(entry.getKey(), entry.getValue());
			biomes.replace(OptionalInt.of(rawId), entry.getKey(), newBiome, Lifecycle.stable());
		}
	}

	private static Biome transformBiome(RegistryKey<Biome> biomeRegistryKey, Biome biome) {
		FabricBiomeBuilder builder = FabricBiomeBuilder.of(biome);

		BiomeLoadingCallback.EVENT.invoker().onBiomeLoading(biomeRegistryKey, builder);

		return builder.build();
	}

	public static void transformBiomes(SimpleRegistry<Biome> biomes) {
		Set<Map.Entry<RegistryKey<Biome>, Biome>> entries = ImmutableSet.copyOf(biomes.getEntries());
		for (Map.Entry<RegistryKey<Biome>, Biome> entry : entries) {
			int rawId = biomes.getRawId(entry.getValue());
			Biome newBiome = transformBiome(entry.getKey(), entry.getValue());
			biomes.replace(OptionalInt.of(rawId), entry.getKey(), newBiome, Lifecycle.stable());
		}
	}

	public static <E> E transformRegistryEntry(RegistryKey<E> registryKey, E entry) {
		if(registryKey.isOf(Registry.BIOME_KEY)) {
			return (E)transformBiome((RegistryKey<Biome>)registryKey, (Biome)entry);
		}
		return entry;
	}

	public static <E> DataResult<SimpleRegistry<E>> transformRegistry(RegistryKey<? extends Registry<E>> registryKey, SimpleRegistry<E> registry) {
		if(Registry.BIOME_KEY.equals(registryKey)) {
			transformBiomes((SimpleRegistry<Biome>) registry);
		}
		return DataResult.success(registry);
	}
}
