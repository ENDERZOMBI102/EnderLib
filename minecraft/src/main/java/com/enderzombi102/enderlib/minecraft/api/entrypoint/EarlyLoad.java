package com.enderzombi102.enderlib.minecraft.api.entrypoint;

/**
 * Called by a language adapter impl, it is run _before_ anything BUT after {@link VeryEarlyLoad}
 */
public interface EarlyLoad {
	void onLoad();
}
