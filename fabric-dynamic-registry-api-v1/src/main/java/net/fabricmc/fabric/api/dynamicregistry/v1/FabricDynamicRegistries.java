package net.fabricmc.fabric.api.dynamicregistry.v1;

import com.mojang.serialization.Codec;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import net.fabricmc.fabric.impl.dynamicregistry.FabricDynamicRegistriesInternal;

public final class FabricDynamicRegistries {
	private FabricDynamicRegistries() { }

	public static <T> Registry<T> get(RegistryKey<? extends Registry<T>> registryKey) {
		return FabricDynamicRegistriesInternal.getRegistry(registryKey);
	}

	public static <T> void register(RegistryKey<? extends Registry<T>> registryKey, Codec<T> codec, @Nullable Codec<T> syncCodec) {
		FabricDynamicRegistriesInternal.registerRegistry(registryKey, codec, syncCodec);
	}
}
