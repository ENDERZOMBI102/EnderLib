package com.enderzombi102.enderlib.fabric.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Language;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

@Mixin(Language.class)
public class LanguageMixin {
	@Shadow
	@Final
	private static Pattern TOKEN_PATTERN;

	@Shadow
	@Final
	private static Gson GSON;

	@Inject(
		method = "load",
		at = @At("HEAD"),
		cancellable = true
	)
	private static void onLoad( InputStream inputStream, BiConsumer<String, String> entryConsumer, CallbackInfo info ) {
		recursiveLoadTranslations(
			"",
			GSON.fromJson( new InputStreamReader( inputStream, StandardCharsets.UTF_8 ), JsonObject.class ),
			entryConsumer
		);
		info.cancel();
	}

	private static void recursiveLoadTranslations( @NotNull String currentKey, JsonObject obj, BiConsumer<String, String> consumer ) {
		for ( Map.Entry<String, JsonElement> entry : obj.entrySet() ) {
			if ( entry.getValue() instanceof JsonObject )
				recursiveLoadTranslations(
					( currentKey.isEmpty() ? "" : currentKey + "." ) + entry.getKey(),
					(JsonObject) entry.getValue(),
					consumer
				);
			else {
				var key = currentKey;

				if ( !key.isEmpty() ) {
					if ( !entry.getKey().equals( "value" ) )
						key = key + "." + entry.getKey();
				} else
					key = entry.getKey();

				consumer.accept(
					key,
					TOKEN_PATTERN.matcher( JsonHelper.asString( entry.getValue(), key ) ).replaceAll( "%$1s" )
				);
			}
		}
	}
}
