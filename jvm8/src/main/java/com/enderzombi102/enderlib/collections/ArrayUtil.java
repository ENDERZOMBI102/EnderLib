package com.enderzombi102.enderlib.collections;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.enderzombi102.enderlib.reflection.Types.unbox;

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
		return (T[]) new ArrayList<T>() {{
			addAll( Arrays.asList( src0 ) );
			addAll( Arrays.asList( src1 ) );
		}}.toArray( arrayOf( Object.class, src0.length + src1.length ) );
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
	 * @return the created array
	 * @param <T> type to create the array of
	 */
	public static <T> T[] arrayOf( Class<T> clazz ) {
		return arrayOf( clazz, 0 );
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
	 * Converts a list of type {@link T} to an array
	 * @param list list to convert
	 * @return a new array with the elements of the list
	 */
	public static <T> T[] arrayOf( List<T> list ) {
		return list.toArray( arrayOf( (Class<T>) list.get( 0 ).getClass() ) );
	}

	public static Class<?>[] classes( Object... values ) {
		return convertBoxes(
			Arrays.stream( values )
				.map( Object::getClass )
				.toArray( Class[]::new )
		);
	}

	public static Class<?>[] convertBoxes( Class<?>[] classes ) {
		for ( int i = 0; i < classes.length; i++ )
			classes[i] = unbox( classes[i] );
		return classes;
	}

	// region to primitives
	/**
	 * Uses the power of iterators to de-wrap all {@link Float} wrappers into their primitives
	 * @param floats the array of {@link Float}s
	 * @return an array of float
	 */
	public static float[] primitive( Float @NotNull [] floats ) {
		float[] arr = new float[ floats.length ];

		for ( int index = 0; index < floats.length; index++ )
			arr[index] = floats[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Integer} wrappers into their primitives
	 * @param integers the array of {@link Integer}s
	 * @return an array of int
	 */
	public static int[] primitive( Integer @NotNull [] integers ) {
		int[] arr = new int[ integers.length ];

		for ( int index = 0; index < integers.length; index++ )
			arr[index] = integers[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Double} wrappers into their primitives
	 * @param doubles the array of {@link Double}s
	 * @return an array of double
	 */
	public static double[] primitive( Double @NotNull [] doubles ) {
		double[] arr = new double[ doubles.length ];

		for ( int index = 0; index < doubles.length; index++ )
			arr[index] = doubles[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Boolean} wrappers into their primitives
	 * @param booleans the array of {@link Boolean}s
	 * @return an array of boolean
	 */
	public static boolean[] primitive( Boolean @NotNull [] booleans ) {
		boolean[] arr = new boolean[ booleans.length ];

		for ( int index = 0; index < booleans.length; index++ )
			arr[index] = booleans[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Short} wrappers into their primitives
	 * @param shorts the array of {@link Short}s
	 * @return an array of short
	 */
	public static short[] primitive( Short @NotNull [] shorts ) {
		short[] arr = new short[ shorts.length ];

		for ( int index = 0; index < shorts.length; index++ )
			arr[index] = shorts[index];

		return arr;
	}

	/**
	 * Uses the power of iterators to de-wrap all {@link Byte} wrappers into their primitives
	 * @param bytes the array of {@link Byte}s
	 * @return an array of byte
	 */
	public static byte[] primitive( Byte @NotNull [] bytes ) {
		byte[] arr = new byte[ bytes.length ];

		for ( int index = 0; index < bytes.length; index++ )
			arr[index] = bytes[index];

		return arr;
	}
	// endregion to primitives

	// region to box

	/**
	 * Converts an array of floats to an array of {@link Float}.
	 * @param floats array to convert.
	 * @return The same array but with the boxed version of float.
	 */
	public static Float[] box( float @NotNull [] floats ) {
		Float[] arr = new Float[ floats.length ];

		for ( int index = 0; index < floats.length; index++ )
			arr[index] = floats[index];

		return arr;
	}

	/**
	 * Converts an array of integers to an array of {@link Integer}.
	 * @param integers array to convert.
	 * @return The same array but with the boxed version of int.
	 */
	public static Integer[] box( int @NotNull [] integers ) {
		Integer[] arr = new Integer[ integers.length ];

		for ( int index = 0; index < integers.length; index++ )
			arr[index] = integers[index];

		return arr;
	}

	/**
	 * Converts an array of doubles to an array of {@link Double}.
	 * @param doubles array to convert.
	 * @return The same array but with the boxed version of double.
	 */
	public static Double[] box( double @NotNull [] doubles ) {
		Double[] arr = new Double[ doubles.length ];

		for ( int index = 0; index < doubles.length; index++ )
			arr[index] = doubles[index];

		return arr;
	}

	/**
	 * Converts an array of booleans to an array of {@link Boolean}.
	 * @param booleans array to convert.
	 * @return The same array but with the boxed version of boolean.
	 */
	public static Boolean[] box( boolean @NotNull [] booleans ) {
		Boolean[] arr = new Boolean[ booleans.length ];

		for ( int index = 0; index < booleans.length; index++ )
			arr[index] = booleans[index];

		return arr;
	}

	/**
	 * Converts an array of shorts to an array of {@link Short}.
	 * @param shorts array to convert.
	 * @return The same array but with the boxed version of short.
	 */
	public static Short[] box( short @NotNull [] shorts ) {
		Short[] arr = new Short[ shorts.length ];

		for ( int index = 0; index < shorts.length; index++ )
			arr[index] = shorts[index];

		return arr;
	}

	/**
	 * Converts an array of bytes to an array of {@link Byte}.
	 * @param bytes array to convert.
	 * @return The same array but with the boxed version of byte.
	 */
	public static Byte[] box( byte @NotNull [] bytes ) {
		Byte[] arr = new Byte[ bytes.length ];

		for ( int index = 0; index < bytes.length; index++ )
			arr[index] = bytes[index];

		return arr;
	}
	// endregion to box

}
