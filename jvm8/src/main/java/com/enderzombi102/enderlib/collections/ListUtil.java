package com.enderzombi102.enderlib.collections;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class ListUtil {
	public static <T> @NotNull List<T> append( @NotNull List<T> dest, @NotNull List<T> src ) {
		dest.addAll( src );
		return dest;
	}

	@SafeVarargs
	public static <T> @NotNull List<T> append( @NotNull List<T> dest, T... values ) {
		return append( dest, asList( values ) );
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
	public static <T> @NotNull List<T> mutableListOf( T... values ) {
		return append( new ArrayList<>(), values );
	}

	@SafeVarargs
	public static <T> @NotNull List<T> listOf( T... values ) {
		return unmodifiableList( asList( values ) );
	}

	@SuppressWarnings( "unchecked" )
	public static <T> @NotNull List<T> listOf( @NotNull Collection<T> collection ) {
		return listOf( (T[]) collection.toArray() );
	}

	public static <T> @NotNull List<T> mutableListOf( @NotNull Collection<T> collection ) {
		return new ArrayList<>( collection );
	}
}
