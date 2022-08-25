package com.enderzombi102.enderlib;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class ArrayUtil {
	/**
	 * Concatenates two arrays and returns the result
	 * @param src0 array 0
	 * @param src1 array 1
	 * @param <T> type of the final array
	 * @return a new array with the contents of the two given arrays
	 */
	public static <T> T[] plus( T[] src0, T... src1 ) {
		return (T[]) new ArrayList<>() {{
			addAll( List.of(src0) );
			addAll( List.of(src1) );
		}}.toArray( Object[]::new );
	}

	/**
	 * Creates an array of the given class's objects and returns it
	 * @param clazz type's class to create the array of
	 * @return the created array
	 * @param <T> type to create the array of
	 */
	public static <T> T[] arrayOf( Class<T> clazz ) {
		return arrayOf( clazz, 0 );
	}

	/**
	 * Creates an array of the given class's objects and returns its class object
	 * @param clazz type's class to create the array of
	 * @return the created array's class object
	 * @param <T> type to create the array of
	 */
	public static <T> Class<T[]> arrayType( Class<T> clazz ) {
		return (Class<T[]>) arrayOf( clazz, 0 ).getClass();
	}

	/**
	 * Creates an array of the given class's objects and returns it
	 * @param clazz type's class to create the array of
	 * @param size the size of the to-be-created array
	 * @return the created array
	 * @param <T> type to create the array of
	 */
	public static <T> T[] arrayOf( Class<T> clazz, int size ) {
		return (T[]) Array.newInstance( clazz , size );
	}

	/**
	 * Returns an array with the classes of all the given objects
	 * @param values objects to get the classes from
	 * @return the {@link Class} array
	 */
	public static Class<?>[] classes( Object... values ) {
		return convertBoxes(
			Arrays.stream( values )
				.map( Object::getClass )
				.toArray( Class[]::new )
		);
	}

	/**
	 * Replaces boxed classes with their unboxed variants, useful for reflection
	 * @param classes classes to convert
	 * @return an array with the classes replaced
	 */
	public static Class<?>[] convertBoxes( Class<?>[] classes ) {
		for ( var i = 0; i < classes.length; i++ )
			classes[i] = classes[i] == Boolean.class ? boolean.class :
				classes[i] == Character.class ? char.class :
					classes[i] == Short.class ? short.class :
						classes[i] == Integer.class ? int.class :
							classes[i] == Float.class ? float.class :
								classes[i] == Double.class ? double.class :
									classes[i];
		return classes;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Float} wrappers into their primitives
	 * @param floats the array of {@link Float}s
	 * @return an array of float
	 */
	public static float[] primitive( Float @NotNull [] floats ) {
		var arr = new float[ floats.length ];

		for ( var index = 0; index < floats.length; index++ )
			arr[index] = floats[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Integer} wrappers into their primitives
	 * @param integers the array of {@link Integer}s
	 * @return an array of int
	 */
	public static int[] primitive( Integer @NotNull [] integers ) {
		var arr = new int[ integers.length ];

		for ( var index = 0; index < integers.length; index++ )
			arr[index] = integers[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Double} wrappers into their primitives
	 * @param doubles the array of {@link Double}s
	 * @return an array of double
	 */
	public static double[] primitive( Double @NotNull [] doubles ) {
		var arr = new double[ doubles.length ];

		for ( var index = 0; index < doubles.length; index++ )
			arr[index] = doubles[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Boolean} wrappers into their primitives
	 * @param booleans the array of {@link Boolean}s
	 * @return an array of boolean
	 */
	public static boolean[] primitive( Boolean @NotNull [] booleans ) {
		var arr = new boolean[ booleans.length ];

		for ( var index = 0; index < booleans.length; index++ )
			arr[index] = booleans[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Short} wrappers into their primitives
	 * @param shorts the array of {@link Short}s
	 * @return an array of short
	 */
	public static short[] primitive( Short @NotNull [] shorts ) {
		var arr = new short[ shorts.length ];

		for ( var index = 0; index < shorts.length; index++ )
			arr[index] = shorts[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Byte} wrappers into their primitives
	 * @param bytes the array of {@link Byte}s
	 * @return an array of byte
	 */
	public static byte[] primitive( Byte @NotNull [] bytes ) {
		var arr = new byte[ bytes.length ];

		for ( var index = 0; index < bytes.length; index++ )
			arr[index] = bytes[index];

		return arr;
	}
}
