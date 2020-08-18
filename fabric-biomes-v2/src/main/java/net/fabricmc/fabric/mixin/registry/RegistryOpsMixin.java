package net.fabricmc.fabric.mixin.registry;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.impl.biome.FabricBiomeUtil;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.OptionalInt;

@Mixin(RegistryOps.class)
public class RegistryOpsMixin<E> {
	@Redirect(method = "readSupplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/MutableRegistry;replace(Ljava/util/OptionalInt;Lnet/minecraft/util/registry/RegistryKey;Ljava/lang/Object;Lcom/mojang/serialization/Lifecycle;)Ljava/lang/Object;"))
	private E onLoadRegistryEntry(MutableRegistry<E> mutableRegistry, OptionalInt rawId, RegistryKey<E> key, E newEntry, Lifecycle lifecycle) {
		return mutableRegistry.replace(rawId, key, FabricBiomeUtil.transformRegistryEntry(key, newEntry), lifecycle);
	}
}
