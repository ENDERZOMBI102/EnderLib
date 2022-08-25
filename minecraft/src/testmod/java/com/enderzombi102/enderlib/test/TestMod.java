package com.enderzombi102.enderlib.test;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class TestMod implements ModInitializer {
	@Override
	public void onInitialize( ModContainer mod ) {
		Registry.register(
			Registry.ITEM,
			getId( "testblock" ),
			new BlockItem(
				Registry.register(
					Registry.BLOCK,
					getId( "testblock" ),
					new Block(
						QuiltBlockSettings.of( Material.SOIL )
							.dropsNothing()
							.luminance( 6 )
							.sounds( BlockSoundGroup.GRASS )
							.breakInstantly()
					)
				),
				new QuiltItemSettings().fireproof()
			)
		);
	}

	private static Identifier getId( String path ) {
		return new Identifier( "enderlib-testmod", path );
	}
}
