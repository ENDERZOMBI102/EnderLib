package com.enderzombi102.enderlib.io;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.enderzombi102.enderlib.SafeUtils.doSafely;

public abstract class FileUtils {
	private FileUtils() { }

	/**
	 * Converts an {@link URL} to a {@link Path} object.
	 * @param url url to convert.
	 * @return {@link Path} equivalent.
	 */
	@SuppressWarnings("ConstantConditions")
	public static @NotNull Path toPath( @NotNull URL url ) {
		return Paths.get( doSafely( url::toURI ) );
	}
}
