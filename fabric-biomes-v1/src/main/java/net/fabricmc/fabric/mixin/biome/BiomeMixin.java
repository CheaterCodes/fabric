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

package net.fabricmc.fabric.mixin.biome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.impl.biome.HasBeenProcessedProvider;

@Mixin(Biome.class)
public class BiomeMixin implements HasBeenProcessedProvider {
	@Unique
	private boolean fabric_hasBeenProcessed = false;

	@Override
	public boolean hasBeenProcessed() {
		return fabric_hasBeenProcessed;
	}

	@Override
	public void setProcessed() {
		fabric_hasBeenProcessed = true;
	}
}
