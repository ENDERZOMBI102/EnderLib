package com.enderzombi102.enderlib.reflection;

import com.enderzombi102.enderlib.SafeUtils;
import org.jetbrains.annotations.Range;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Map;

import static com.enderzombi102.enderlib.ArrayUtil.*;
import static com.enderzombi102.enderlib.ListUtil.*;
import static com.enderzombi102.enderlib.reflection.Getters.getStatic;
import static com.enderzombi102.enderlib.reflection.Invokers.invoke;
import static com.enderzombi102.enderlib.reflection.Setters.setStatic;


@SuppressWarnings("unchecked")
public final class Reflection {
	static final MethodHandles.Lookup IMPL_LOOKUP;
	static final Unsafe UNSAFE;

	/**
	 * Add a value to an enum
	 * @param clazz the enum's class
	 * @param name name of the value to add
	 */
	public static < T extends Enum<T> > void add( Class<T> clazz, String name ) {
		try {
			// get old array
			var arr = getStatic( clazz, "$VALUES", arrayType( clazz ) );
			// create new enum instance
			var value = (T) IMPL_LOOKUP.findConstructor(
				clazz,
				MethodType.methodType( void.class, String.class, int.class )
			).invoke( name, arr.length );
			// set $VALUES to new array
			setStatic(
				clazz,
				"$VALUES",
				append( mutableListOf( arr ), value ).toArray( arrayOf( clazz ) )
			);
			// add to const dir
			invoke(
				clazz,
				"enumConstantDirectory",
				Map.class
			).put( name, value );
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}

	/**
	 * Getter for the godly IMPL_LOOKUP
	 */
	public static MethodHandles.Lookup getImplLookup() {
		return IMPL_LOOKUP;
	}

	/**
	 * Getter for the sun's Unsafe object
	 */
	public static Unsafe getUnsafe() {
		return UNSAFE;
	}

	/**
	 * Returns the class and method that called the method that called getCallerInfo()
	 */
	public static CallerInfo getCallerInfo() throws ClassNotFoundException {
		return getCallerInfo( 1 );
	}

	/**
	 * Returns the class and method at frame point -frameOffset from the getCallerInfo() call
	 */
	public static CallerInfo getCallerInfo( @Range( from=0, to=100 ) int frameOffset ) throws ClassNotFoundException {
		var stack = Thread.currentThread().getStackTrace();
		var frame = stack[ 2 + frameOffset ];

		return new CallerInfo(
			Class.forName( frame.getClassName() ),
			frame.getMethodName(),
			frame.getLineNumber()
		);
	}

	/**
	 * Returns the class and method that called the method that called getCallerInfoSafe()
	 */
	public static CallerInfo getCallerInfoSafe() {
		return getCallerInfoSafe( 1 );
	}

	/**
	 * Returns the class and method at frame point -frameOffset from the getCallerInfo() call
	 */
	public static CallerInfo getCallerInfoSafe( @Range( from=0, to=100 ) int frameOffset ) {
		var stack = Thread.currentThread().getStackTrace();
		var frame = stack[ 2 + frameOffset ];

		return new CallerInfo(
			SafeUtils.doSafely( () -> Class.forName( frame.getClassName() ) ),
			frame.getMethodName(),
			frame.getLineNumber()
		);
	}

	static {
		try {
			final var unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeField.setAccessible(true);
			UNSAFE = (Unsafe) unsafeField.get(null);
			final var implLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
			IMPL_LOOKUP = (MethodHandles.Lookup) UNSAFE.getObject(
				UNSAFE.staticFieldBase(implLookupField),
				UNSAFE.staticFieldOffset(implLookupField)
			);
		} catch (Throwable e) { throw new RuntimeException(e); }
	}

	public record CallerInfo( Class<?> clazz, String method, int line ) { }
}
