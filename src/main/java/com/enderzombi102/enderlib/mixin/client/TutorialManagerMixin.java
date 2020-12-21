package com.enderzombi102.enderlib.mixin.client;

import com.enderzombi102.enderlib.Config;
import net.minecraft.client.tutorial.TutorialManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TutorialManager.class)
public class TutorialManagerMixin {

	@Inject(at = @At("HEAD"), method = "tick()V", cancellable = true)
	private void init(CallbackInfo info) {
		if ( Config.isTutorialDisabled() ) {
			info.cancel();
		}
	}

}
