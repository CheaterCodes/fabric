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

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;

@Mixin(BiomeEffects.class)
public interface BiomeEffectsAccessor {
	@Accessor
	int getFogColor();

	@Accessor
	int getWaterColor();

	@Accessor
	int getWaterFogColor();

	@Accessor
	int getSkyColor();

	@Accessor
	Optional<Integer> getFoliageColor();

	@Accessor
	Optional<Integer> getGrassColor();

	@Accessor
	BiomeEffects.GrassColorModifier getGrassColorModifier();

	@Accessor
	Optional<BiomeParticleConfig> getParticleConfig();

	@Accessor
	Optional<SoundEvent> getLoopSound();

	@Accessor
	Optional<BiomeMoodSound> getMoodSound();

	@Accessor
	Optional<BiomeAdditionsSound> getAdditionsSound();

	@Accessor
	Optional<MusicSound> getMusic();
}

