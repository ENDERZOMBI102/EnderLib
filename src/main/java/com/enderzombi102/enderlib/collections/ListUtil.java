package com.enderzombi102.enderlib.collections;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static java.util.List.copyOf;

/**
 * Utilities to work with lists.
 */
public class ListUtil {
	public static <T> @NotNull List<T> append( @NotNull List<T> dest, @NotNull List<T> src ) {
		dest.addAll( src );
		return dest;
	}

	@SafeVarargs
	public static <T> @NotNull List<T> append( @NotNull List<T> dest, T... values ) {
		return append( dest, listOf( values ) );
	}

	public static <T> @NotNull List<T> prepend( @NotNull List<T> dest, @NotNull List<T> src ) {
		dest.addAll( 0, src );
		return dest;
	}

	@SafeVarargs
	public static <T> @NotNull List<T> prepend( @NotNull List<T> dest, T... src ) {
		return prepend( dest, ListUtil.listOf( src ) );
	}

	public static <T> @NotNull T pick( @NotNull Random rnd, @NotNull List<T> elements ) {
		return elements.get( rnd.nextInt( elements.size() ) );
	}

	public static <T> @NotNull T pick( @NotNull Random rnd, @NotNull List<T> elements, @NotNull List<Integer> excludes ) {
		int index;

		do
			index = rnd.nextInt( elements.size() );
		while ( excludes.contains( index ) );

		return elements.get( index );
	}

	@SafeVarargs
	public static <T> List<T> mutableListOf( T... values ) {
		return append( new ArrayList<>(), values );
	}

	@SafeVarargs
	public static <T> List<T> listOf( T... values ) {
		return List.of( values );
	}

	public static <T> @NotNull List<T> listOf( @NotNull Collection<T> collection ) {
		return copyOf( collection );
	}

	public static <T> @NotNull List<T> mutableListOf( @NotNull Collection<T> collection ) {
		return new ArrayList<>( collection );
	}
}
