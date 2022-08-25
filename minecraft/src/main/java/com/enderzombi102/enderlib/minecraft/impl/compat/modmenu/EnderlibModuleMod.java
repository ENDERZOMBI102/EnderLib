package com.enderzombi102.enderlib.minecraft.impl.compat.modmenu;

import com.enderzombi102.enderlib.minecraft.impl.Const;
import com.terraformersmc.modmenu.ModMenu;
import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricIconHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("ClassCanBeRecord")
class EnderlibModuleMod implements Mod {
	private final @NotNull String id;
	private final @NotNull String name;
	private final @NotNull String description;

	EnderlibModuleMod( @NotNull String id, @NotNull String name, @NotNull String description ) {
		this.id = "enderlib-" + id;
		this.name = "EnderLib - " + name;
		this.description = description;
		ModMenu.PARENT_MAP.put( this, ModMenu.MODS.get( "enderlib" ) );
	}

	@Override
	public @NotNull String getId() {
		return this.id;
	}

	@Override
	public @NotNull String getName() {
		return this.name;
	}

	@Override
	public @NotNull NativeImageBackedTexture getIcon( FabricIconHandler handler, int i ) {
		return handler.createIcon(
			FabricLoader.getInstance().getModContainer( "enderlib-fabric" ).orElseThrow(),
			"asstes/enderlib/" + this.id + ".png"
		);
	}

	@Override
	public @NotNull String getSummary() {
		return "";
	}

	@Override
	public @NotNull String getDescription() {
		return this.description;
	}

	@Override
	public @NotNull String getVersion() {
		return Const.VERSION;
	}

	@Override
	public @NotNull String getPrefixedVersion() {
		return Const.VERSION;
	}

	@Override
	public @NotNull List<String> getAuthors() {
		return List.of( "ENDERZOMBI102" );
	}

	@Override
	public @NotNull List<String> getContributors() {
		return List.of();
	}

	@Override
	public @NotNull List<String> getCredits() {
		return List.of();
	}

	@Override
	public @NotNull Set<Badge> getBadges() {
		return Set.of( Badge.LIBRARY );
	}

	@Override
	public @Nullable String getWebsite() {
		return null;
	}

	@Override
	public @Nullable String getIssueTracker() {
		return Const.CONTAINER.getMetadata().getContact().get( "issues" ).orElseThrow();
	}

	@Override
	public @Nullable String getSource() {
		return Const.CONTAINER.getMetadata().getContact().get( "sources" ).orElseThrow();
	}

	@Override
	public @Nullable String getParent() {
		return "enderlib";
	}

	@Override
	public @NotNull Set<String> getLicense() {
		return Set.of( "MIT" );
	}

	@Override
	public @NotNull Map<String, String> getLinks() {
		return Map.of();
	}

	@Override
	public boolean isReal() {
		return false;
	}
}
