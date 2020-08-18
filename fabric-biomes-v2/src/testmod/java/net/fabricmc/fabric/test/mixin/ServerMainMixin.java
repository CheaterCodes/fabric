package net.fabricmc.fabric.test.mixin;

import net.fabricmc.fabric.api.biomes.v2.event.BiomeLoadingCallback;
import net.minecraft.server.Main;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Main.class)
public class ServerMainMixin {
	@Inject(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;create()Lnet/minecraft/util/registry/DynamicRegistryManager$Impl;"))
	private static void beforeDynamicRegistryManagerCreate(String[] args, CallbackInfo ci) {
		BiomeLoadingCallback.EVENT.register((biomeRegistryKey, biomeBuilder) -> {
			if(BiomeKeys.PLAINS.equals(biomeRegistryKey)) {
				biomeBuilder.structureFeature(() -> ConfiguredStructureFeatures.DESERT_PYRAMID);
			}
		});
	}
}
