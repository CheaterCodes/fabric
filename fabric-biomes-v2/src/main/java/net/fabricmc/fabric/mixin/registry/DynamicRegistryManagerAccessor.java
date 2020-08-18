package net.fabricmc.fabric.mixin.registry;

import net.minecraft.util.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DynamicRegistryManager.class)
public interface DynamicRegistryManagerAccessor {
	@Accessor("BUILTIN")
	static DynamicRegistryManager.Impl getBuiltin() {
		throw new AbstractMethodError();
	}
}
