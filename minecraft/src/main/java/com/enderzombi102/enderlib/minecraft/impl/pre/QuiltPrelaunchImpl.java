package com.enderzombi102.enderlib.minecraft.impl.pre;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class QuiltPrelaunchImpl implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch( ModContainer mod ) {
		MixinExtrasBootstrap.init();
	}
}
