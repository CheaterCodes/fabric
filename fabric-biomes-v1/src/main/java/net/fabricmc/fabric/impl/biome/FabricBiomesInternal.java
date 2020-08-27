/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.impl.biome;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.mixin.biome.DynamicRegistryManagerAccessor;

public class FabricBiomesInternal {
	private static final Set<RegistryKey<Biome>> OVERWORLD_BIOMES = Sets.newHashSet();
	private static final Map<RegistryKey<Biome>, Biome.MixedNoisePoint> NETHER_BIOMES = Maps.newHashMap();

	/**
	 * Intended only for use within {@link net.minecraft.world.biome.layer.BiomeLayers}.
	 */
	public static Registry<Biome> lastBiomeRegistry;

	public static RegistryKey<Biome> register(RegistryKey<Biome> key, Biome biome) {
		Registry<Biome> registry = DynamicRegistryManagerAccessor.getBuiltin().get(Registry.BIOME_KEY);
		Registry.register(registry, key.getValue(), biome);
		return key;
	}

	public static RegistryKey<Biome> addToOverworld(RegistryKey<Biome> key) {
		OVERWORLD_BIOMES.add(key);
		return key;
	}

	public static RegistryKey<Biome> addToNether(RegistryKey<Biome> key, Biome.MixedNoisePoint mixedNoisePoint) {
		NETHER_BIOMES.put(key, mixedNoisePoint);
		return key;
	}

	public static Set<RegistryKey<Biome>> getOverworldBiomes() {
		return OVERWORLD_BIOMES;
	}

	public static Map<RegistryKey<Biome>, Biome.MixedNoisePoint> getNetherBiomes() {
		return NETHER_BIOMES;
	}
}
