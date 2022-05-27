package com.enderzombi102.enderlib.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public final class Invokers {
	private Invokers() { }

	public static <T> T invoke( Object obj, String name, Class<T> retType, Object... args ) {
		try {
			return (T) obj.getClass()
				.getDeclaredMethod(
					name,
					Arrays.stream( args )
						.map( Object::getClass )
						.collect( Collectors.toList() )
						.toArray( new Class[] {} )
				).invoke( obj, args );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException( e );
		}
	}

	public static void invoke( Object obj, String name, Object... args ) {
		try {
			obj.getClass()
				.getDeclaredMethod(
					name,
					Arrays.stream( args )
						.map( Object::getClass )
						.collect( Collectors.toList() )
						.toArray( new Class[] {} )
				).invoke( obj, args );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException( e );
		}
	}
}
