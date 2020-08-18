package net.fabricmc.fabric.mixin.registry;

import net.minecraft.util.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DynamicRegistryManager.class)
public class DynamicRegistryManagerMixin {
	@Inject(method = "create", at = @At("RETURN"))
	private static void onCreate(CallbackInfoReturnable<DynamicRegistryManager.Impl> cir) {
		//FabricBiomeUtil.transformBiomes(cir.getReturnValue());
	}
}
