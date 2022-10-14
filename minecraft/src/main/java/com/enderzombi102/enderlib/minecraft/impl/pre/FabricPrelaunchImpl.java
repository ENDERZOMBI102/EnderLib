package com.enderzombi102.enderlib.minecraft.impl.pre;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.quiltmc.loader.api.ModContainer;

public class FabricPrelaunchImpl implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		MixinExtrasBootstrap.init();
	}
}
