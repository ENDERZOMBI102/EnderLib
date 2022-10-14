package com.enderzombi102.enderlib.minecraft.impl.mixin;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.util.Language;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Language.class)
public class LanguageMixin {

	@ModifyReceiver(
		method = "load",
		at = @At(
			value = "INVOKE",
			target = "Lcom/google/gson/JsonObject;entrySet()Ljava/util/Set;"
		)
	)
	private static JsonObject onLoad( JsonObject value ) {
		return recursiveLoadTranslations( "", value, new JsonObject() );
	}

	private static JsonObject recursiveLoadTranslations( @NotNull String currentKey, JsonObject obj, JsonObject result ) {
		for ( var entry : obj.entrySet() ) {
			if ( entry.getValue() instanceof JsonObject newObj )
				recursiveLoadTranslations(
					( currentKey.isEmpty() ? "" : currentKey + "." ) + entry.getKey(),
					newObj,
					result
				);
			else {
				var key = currentKey;

				if (! key.isEmpty() ) {
					if ( !entry.getKey().equals( "value" ) )
						key = key + "." + entry.getKey();
				} else {
					key = entry.getKey();
				}

				result.add( key, entry.getValue() );
			}
		}
		return result;
	}
}
