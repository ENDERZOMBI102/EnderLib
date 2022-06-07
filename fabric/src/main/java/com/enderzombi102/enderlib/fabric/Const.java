package com.enderzombi102.enderlib.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;

public class Const {
	public static final String ID = "enderlib-quilt";
	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static final ModContainer CONTAINER = FabricLoader.getInstance().getModContainer(ID).get();
	public static final String VERSION = CONTAINER.getMetadata().getVersion().getFriendlyString();

	public static Identifier getId( String key ) {
		return new Identifier( ID, key );
	}
}
