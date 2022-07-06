package com.enderzombi102.enderlib.minecraft.api.entrypoint;

/**
 * Called on fabric platforms by a mixin config, which is loaded before _anything_
 */
public interface VeryEarlyLoad {
	void onLoad();
}
