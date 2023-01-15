package com.enderzombi102.enderlib.stream;

import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * A few methods to add additional stream creators.
 */
public class Streams {
	/**
	 * Splits a string into a stream of tokens.
	 * @param string string to split.
	 * @param regex regex that will be used to split the string.
	 * @return a {@link Stream<String>} with the tokens.
	 * @see String#split(String)
	 */
	public static Stream<String> splitToStream( @NotNull String string, @RegExp @NotNull String regex ) {
		return stream( string.split( regex ) );
	}
}
