package com.enderzombi102.enderlib.collections;

import java.util.*;

public class MapUtil {

	public static <K, V> Map<K, V> append( Map<K, V> map, List<Map.Entry<K, V>> entries ) {
		map.putAll( mutableMapOf( entries ) );
		return map;
	}

	@SafeVarargs
	public static <K, V> Map<K, V> append( Map<K, V> map, Map.Entry<K, V>... entries ) {
		map.putAll( mutableMapOf( entries ) );
		return map;
	}

	public static <K, V> Map<K, V> mutableMapOf( List<Map.Entry<K, V>> entries ) {
		return new HashMap<K ,V>() {{
			for ( Map.Entry<K, V> entry : entries )
				put( entry.getKey(), entry.getValue() );
		}};
	}

	@SafeVarargs
	public static <K, V> Map<K, V> mutableMapOf( Map.Entry<K, V>... entries ) {
		return new HashMap<K ,V>() {{
			for ( Map.Entry<K, V> entry : entries )
				put( entry.getKey(), entry.getValue() );
		}};
	}

	public static <K, V> Map<K, V> mapOf( List<Map.Entry<K, V>> entries ) {
		return Collections.unmodifiableMap( mutableMapOf( entries ) );
	}

	@SafeVarargs
	public static <K, V> Map<K, V> mapOf( Map.Entry<K, V>... entries ) {
		return Collections.unmodifiableMap( mutableMapOf( entries ) );
	}
}
