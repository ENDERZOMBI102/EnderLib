package com.enderzombi102.enderlib.collections;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class ListUtil {
	public static <T> List<T> append( List<T> dest, List<T> src ) {
		dest.addAll( src );
		return dest;
	}

	@SafeVarargs
	public static <T> List<T> append( List<T> dest, T... values ) {
		return append( dest, asList( values ) );
	}

	@SafeVarargs
	public static <T> List<T> mutableListOf( T... values ) {
		return append( new ArrayList<>(), values );
	}

	@SafeVarargs
	public static <T> List<T> listOf( T... values ) {
		return unmodifiableList( asList( values ) );
	}
}
