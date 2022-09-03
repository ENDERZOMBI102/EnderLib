package com.enderzombi102.enderlib.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.enderzombi102.enderlib.collections.ArrayUtil.arrayOf;

public class MapUtil {

	public static <K, V> Map<K, V> append( Map<K, V> map, List<Map.Entry<K, V>> entries ) {
		map.putAll( mapOf( entries ) );
		return map;
	}

	@SafeVarargs
	public static <K, V> Map<K, V> append( Map<K, V> map, Map.Entry<K, V>... entries ) {
		map.putAll( mapOf( entries ) );
		return map;
	}

	public static <K, V> Map<K, V> mutableMapOf( List<Map.Entry<K, V>> entries ) {
		return new HashMap<>( mapOf( entries ) );
	}

	@SafeVarargs
	public static <K, V> Map<K, V> mutableMapOf( Map.Entry<K, V>... entries ) {
		return new HashMap<>( mapOf( entries ) );
	}

	public static <K, V> Map<K, V> mapOf( List<Map.Entry<K, V>> entries ) {
		return Map.ofEntries( arrayOf( entries ) );
	}

	@SafeVarargs
	public static <K, V> Map<K, V> mapOf( Map.Entry<K, V>... entries ) {
		return Map.ofEntries( entries );
	}
}
