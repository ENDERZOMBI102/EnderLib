package com.enderzombi102.enderlib.fabric;

import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;

public class Const {
	public static final String ID = "enderlib-quilt";
	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static final ModContainer CONTAINER = QuiltLoader.getModContainer(ID).get();
	public static final String VERSION = CONTAINER.metadata().version().raw();

	public static Identifier getId( String key ) {
		return new Identifier( ID, key );
	}
}
