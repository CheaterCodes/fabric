package net.fabricmc.fabric.mixin.dynamicregistry;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import net.fabricmc.fabric.impl.dynamicregistry.FabricDynamicRegistriesInternal;

@Mixin(DynamicRegistryManager.class)
public class DynamicRegistryManagerMixin {
	@Inject(method = "create", at = @At("RETURN"))
	private static void loadBuiltin(CallbackInfoReturnable<DynamicRegistryManager.Impl> cir) {
		DynamicRegistryManager.Impl registryManager = cir.getReturnValue();
		FabricDynamicRegistriesInternal.loadBuiltin(registryManager);
	}

	@Redirect(method = "load(Lnet/minecraft/util/registry/DynamicRegistryManager$Impl;Lnet/minecraft/util/dynamic/RegistryOps;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;INFOS:Ljava/util/Map;"))
	private static Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> appendInfosLoad(Map<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builtinInfos) {
		FabricDynamicRegistriesInternal.appendInfos(builtinInfos);
		return builtinInfos;
	}
}
