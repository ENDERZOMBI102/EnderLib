package com.enderzombi102.enderlib.minecraft.impl.mixin;

import com.enderzombi102.enderlib.minecraft.impl.integr.modmenu.EnderlibModuleMod;
import com.terraformersmc.modmenu.ModMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
	value = ModMenu.class,
	targets = "com.terraformersmc.modmenu.ModMenu",
	remap = false
)
@Pseudo
public class ModMenuMixin {
	@Inject(
		method = "onInitializeClient",
		at = @At(
			value = "INVOKE",
			target = "Lcom/terraformersmc/modmenu/event/ModMenuEventHandler;register()V"
		)
	)
	public void onOnInitializeClient( CallbackInfo ci ) {
		var loader = this.getClass().getClassLoader();
		if ( loader.getResource( "EnderLib/extensions/java/lang/String/StringExt.class" ) != null )
			// add manifold lib
			ModMenu.MODS.put(
				"enderlib-manifolds",
				new EnderlibModuleMod(
					"manifolds",
					"Manifolds",
					"EnderLib module containing various extension methods and stuff"
				)
			);
		// add main lib
		ModMenu.MODS.put(
			"enderlib-core",
			new EnderlibModuleMod(
				"core",
				"Core",
				"EnderLib main module, contains various utilities ENDER commonly use"
			)
		);
	}
}
