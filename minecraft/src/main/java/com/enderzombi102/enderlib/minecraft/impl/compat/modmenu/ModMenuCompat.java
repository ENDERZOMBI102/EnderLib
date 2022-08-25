package com.enderzombi102.enderlib.minecraft.impl.compat.modmenu;

import com.terraformersmc.modmenu.ModMenu;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuCompat implements ModMenuApi {
	static {
		var loader = ModMenuCompat.class.getClassLoader();
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
		if ( loader.getResource( "com/enderzombi102/enderlib/Strings.class" ) != null )
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

