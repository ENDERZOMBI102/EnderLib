package com.enderzombi102.enderlib.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static com.enderzombi102.enderlib.collections.ArrayUtil.classes;
import static com.enderzombi102.enderlib.reflection.Reflection.IMPL_LOOKUP;

@SuppressWarnings("unchecked")
public final class Invokers {
	private Invokers() { }

	public static <T> T invoke( Object obj, String name, Class<T> retType, Object... args ) {
		try {
			return (T) IMPL_LOOKUP.bind( obj, name, MethodType.methodType( retType, classes( args ) ) ).invoke( args );
		} catch ( Throwable e ) {
			throw new RuntimeException( e );
		}
	}

	public static void invoke( Object obj, String name, Object... args ) {
		try {
			IMPL_LOOKUP.bind( obj, name, MethodType.methodType( void.class, classes( args ) ) ).invoke( args );
		} catch ( Throwable e ) {
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
		var handle = handleToStatic(
			clazz,
			name,
			retType,
			classes( params )
		);
		if ( params.length == 0 )
			return (T) handle.invoke();

		if ( params.length == 1 )
			return (T) handle.invoke( params[0] );

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

	public static <T> T contruct( Class<T> clazz, Object[] params, Class<?>[] types ) throws Throwable {
		var x = IMPL_LOOKUP.findConstructor(
			clazz,
			MethodType.methodType( void.class, types )
		);
		var y = x.invoke( params );
		return (T) y;
	}
}
