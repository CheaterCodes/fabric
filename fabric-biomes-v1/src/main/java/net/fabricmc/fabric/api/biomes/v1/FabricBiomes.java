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

package net.fabricmc.fabric.api.biomes.v1;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.impl.biome.FabricBiomesInternal;

public final class FabricBiomes {
	private FabricBiomes() { }

	public static RegistryKey<Biome> register(RegistryKey<Biome> key, Biome biome) {
		return FabricBiomesInternal.register(key, biome);
	}

	public static RegistryKey<Biome> addToOverworld(RegistryKey<Biome> key) {
		return FabricBiomesInternal.addToOverworld(key);
	}

	public static RegistryKey<Biome> addToNether(RegistryKey<Biome> key, Biome.MixedNoisePoint mixedNoisePoint) {
		return FabricBiomesInternal.addToNether(key, mixedNoisePoint);
	}
}
