package com.enderzombi102.enderlib.stream;

import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Some additional collectors.
 */
public class Collectors {

	/**
	 * Creates a {@link Collector} that joins the result of {@link Object#toString()}.
	 */
	public static Collector<@NotNull Object, ?, @NotNull String> joining() {
		return Collector.of(
			StringBuilder::new,
			StringBuilder::append,
			StringBuilder::append,
			StringBuilder::toString
		);
	}

	/**
	 * Creates a {@link Collector} that joins the result of {@link Object#toString()}.
	 * @param delimiter the string that will be put between every entry.
	 */
	public static Collector<@NotNull Object, ?, @NotNull String> joining( @NotNull String delimiter ) {
		return joining( delimiter, "", "" );
	}


	/**
	 * Creates a {@link Collector} that joins the result of {@link Object#toString()}.
	 * @param delimiter the string that will be put between every entry.
	 * @param prefix the string that will be put at the start.
	 * @param suffix the string that will be put at the end.
	 */
	public static Collector<@NotNull Object, ?, @NotNull String> joining( @NotNull String delimiter, @NotNull String prefix, @NotNull String suffix ) {
		return Collector.of(
			() ->  new StringJoiner( delimiter, prefix, suffix ),
			( joiner, obj ) -> joiner.add( obj.toString() ),
			StringJoiner::merge,
			StringJoiner::toString
		);
	}
}
