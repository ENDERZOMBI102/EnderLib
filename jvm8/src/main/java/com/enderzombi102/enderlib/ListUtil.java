package com.enderzombi102.enderlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
	@SafeVarargs
	public static <T> List<T> append( List<T> list, T... values ) {
		list.addAll( Arrays.asList( values ) );
		return list;
	}

	@SafeVarargs
	public static <T> List<T> mutableListOf( T... values ) {
		return append( new ArrayList<>(), values );
	}
}
