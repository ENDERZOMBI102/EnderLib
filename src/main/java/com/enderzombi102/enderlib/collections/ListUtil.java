package com.enderzombi102.enderlib.collections;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	public static <T> List<T> append( List<T> dest, List<T> src ) {
		dest.addAll( src );
		return dest;
	}

	@SafeVarargs
	public static <T> List<T> append( List<T> dest, T... values ) {
		return append( dest, listOf( values ) );
	}

	@SafeVarargs
	public static <T> List<T> mutableListOf( T... values ) {
		return append( new ArrayList<>(), values );
	}

	@SafeVarargs
	public static <T> List<T> listOf( T... values ) {
		return List.of( values );
	}
}
