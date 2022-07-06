package com.enderzombi102.enderlib.minecraft;

import com.enderzombi102.enderlib.minecraft.api.entrypoint.EarlyLoad;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.ModContainer;

public class EnderlibLanguageAdapter implements LanguageAdapter {
	@Override
	public native <T> T create( ModContainer mod, String value, Class<T> type );

	static {
		FabricLoader.getInstance()
			.getEntrypoints( "enderlib:earlyload", EarlyLoad.class )
			.forEach( EarlyLoad::onLoad );
	}
}
