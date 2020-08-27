package net.fabricmc.fabric.impl.dynamicregistry;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public final class FabricDynamicRegistriesInternal {
	// Builtin registries
	private static Map<RegistryKey<? extends Registry<?>>, Registry<?>> BUILTIN = Maps.newHashMap();
	private static List<DynamicRegistryManager.Info<?>> INFOS = Lists.newLinkedList();

	private FabricDynamicRegistriesInternal() { }

	@SuppressWarnings("unchecked")
	public static <T> Registry<T> getRegistry(RegistryKey<? extends Registry<T>> registryKey) {
		return (Registry<T>) BUILTIN.computeIfAbsent(registryKey,
				key -> new SimpleRegistry<T>((RegistryKey<? extends Registry<T>>) key, Lifecycle.stable()));
	}

	public static <T> void registerRegistry(RegistryKey<? extends Registry<T>> registryKey, Codec<T> codec, @Nullable Codec<T> syncCodec) {
		INFOS.add(new DynamicRegistryManager.Info<>(registryKey, codec, syncCodec));
	}

	public static void appendInfos(Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builtinInfos) {
		for (DynamicRegistryManager.Info<?> info : INFOS) {
			builtinInfos.put(info.getRegistry(), info);
		}
	}

	@SuppressWarnings({"unchecked", "OptionalGetWithoutIsPresent"})
	public static <T> void loadBuiltin(DynamicRegistryManager.Impl registryManager) {
		for (Map.Entry<RegistryKey<? extends Registry<?>>, Registry<?>> builtin : BUILTIN.entrySet()) {
			MutableRegistry<T> registry = registryManager.get((RegistryKey<? extends Registry<T>>) builtin.getKey());

			if (registry != null) {
				Registry<T> original = (Registry<T>) builtin.getValue();

				for (T entry : original) {
					registry.add(original.getKey(entry).get(), entry, Lifecycle.stable());
				}
			}
		}
	}
}
