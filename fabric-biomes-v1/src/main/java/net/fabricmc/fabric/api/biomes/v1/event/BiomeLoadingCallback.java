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

package net.fabricmc.fabric.api.biomes.v1.event;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.api.biomes.v1.FabricBiomeBuilder;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

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
