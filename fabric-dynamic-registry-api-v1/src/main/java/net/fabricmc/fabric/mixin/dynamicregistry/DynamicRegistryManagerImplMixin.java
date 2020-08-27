package net.fabricmc.fabric.mixin.dynamicregistry;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import net.fabricmc.fabric.impl.dynamicregistry.FabricDynamicRegistriesInternal;

@Mixin(DynamicRegistryManager.Impl.class)
public class DynamicRegistryManagerImplMixin {
	@Redirect(method = "<init>()V", at = @At(value = "FIELD", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;INFOS:Ljava/util/Map;"))
	private static Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> appendInfosInit(Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builtinInfos) {
		FabricDynamicRegistriesInternal.appendInfos(builtinInfos);
		return builtinInfos;
	}

	@Redirect(method = "getDataResultForCodec", at = @At(value = "FIELD", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;INFOS:Ljava/util/Map;"))
	private static Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> appendInfosGetDataResultForCodec(Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builtinInfos) {
		FabricDynamicRegistriesInternal.appendInfos(builtinInfos);
		return builtinInfos;
	}

	@Redirect(method = "fromRegistryCodecs", at = @At(value = "FIELD", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;INFOS:Ljava/util/Map;"))
	private static Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> appendInfosFromRegistryCodecs(Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builtinInfos) {
		FabricDynamicRegistriesInternal.appendInfos(builtinInfos);
		return builtinInfos;
	}
}
