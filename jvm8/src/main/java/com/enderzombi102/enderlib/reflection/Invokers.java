package com.enderzombi102.enderlib.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.enderzombi102.enderlib.reflection.Reflection.IMPL_LOOKUP;

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
						.toArray( new Class[] { } )
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

	public static <T> T invokeStatic( String clazz, String name, Class<T> retType, Object... params ) throws Throwable {
		return invokeStatic( Class.forName( clazz ), name, retType, params );
	}

	public static void invokeStatic( String clazz, String name, Object... params ) throws Throwable {
		invokeStatic( Class.forName( clazz ), name, void.class, params );
	}

	public static <T> T invokeStatic( Class<?> clazz, String name, Class<T> retType, Object... params ) throws Throwable {
		MethodHandle handle = handleToStatic(
			clazz,
			name,
			retType,
			Arrays.stream( params )
				.map( Object::getClass )
				.toArray( Class[]::new )
		);
		if ( params.length == 0 )
			return (T) handle.invoke();

		return (T) handle.invoke( params );
	}

	public static void invokeStatic( Class<?> clazz, String name, Object... params ) throws Throwable {
		invokeStatic( clazz, name, void.class, params );
	}

	public static MethodHandle handleToStatic( Class<?> clazz, String name, Class<?> retType, Class<?>... params ) throws NoSuchMethodException, IllegalAccessException {
		return IMPL_LOOKUP.findStatic(
			clazz,
			name,
			MethodType.methodType( retType, params )
		);
	}
}
