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
		return (T[]) new ArrayList<>() {{
			addAll( List.of(src0) );
			addAll( List.of(src1) );
		}}.toArray( Object[]::new );
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

	/**
	 * Constructs an array with the provided values.<br>
	 * Similar to {@link ListUtil#listOf(Object[])}, but with arrays.
	 * @param values array's items.
	 * @param <T> type of array.
	 * @return array with provided items.
	 */
	@SafeVarargs
	public static <T> T[] arrayOf( T... values ) {
		return values;
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

	/**
	 * Uses the power of iterators to de-wrap all {@link Character} wrappers into their primitives
	 * @param bytes the array of {@link Character}s
	 * @return an array of char
	 */
	public static char[] primitive( Character @NotNull [] bytes ) {
		char[] arr = new char[ bytes.length ];

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
		var arr = new Float[ floats.length ];

		for ( var index = 0; index < floats.length; index++ )
			arr[index] = floats[index];

		return arr;
	}

	/**
	 * Converts an array of integers to an array of {@link Integer}.
	 * @param integers array to convert.
	 * @return The same array but with the boxed version of int.
	 */
	public static Integer[] box( int @NotNull [] integers ) {
		var arr = new Integer[ integers.length ];

		for ( var index = 0; index < integers.length; index++ )
			arr[index] = integers[index];

		return arr;
	}

	/**
	 * Converts an array of doubles to an array of {@link Double}.
	 * @param doubles array to convert.
	 * @return The same array but with the boxed version of double.
	 */
	public static Double[] box( double @NotNull [] doubles ) {
		var arr = new Double[ doubles.length ];

		for ( var index = 0; index < doubles.length; index++ )
			arr[index] = doubles[index];

		return arr;
	}

	/**
	 * Converts an array of booleans to an array of {@link Boolean}.
	 * @param booleans array to convert.
	 * @return The same array but with the boxed version of boolean.
	 */
	public static Boolean[] box( boolean @NotNull [] booleans ) {
		var arr = new Boolean[ booleans.length ];

		for ( var index = 0; index < booleans.length; index++ )
			arr[index] = booleans[index];

		return arr;
	}

	/**
	 * Converts an array of shorts to an array of {@link Short}.
	 * @param shorts array to convert.
	 * @return The same array but with the boxed version of short.
	 */
	public static Short[] box( short @NotNull [] shorts ) {
		var arr = new Short[ shorts.length ];

		for ( var index = 0; index < shorts.length; index++ )
			arr[index] = shorts[index];

		return arr;
	}

	/**
	 * Converts an array of bytes to an array of {@link Byte}.
	 * @param bytes array to convert.
	 * @return The same array but with the boxed version of byte.
	 */
	public static Byte[] box( byte @NotNull [] bytes ) {
		var arr = new Byte[ bytes.length ];

		for ( var index = 0; index < bytes.length; index++ )
			arr[index] = bytes[index];

		return arr;
	}

	/**
	 * Converts an array of chars to an array of {@link Character}.
	 * @param bytes array to convert.
	 * @return The same array but with the boxed version of char.
	 */
	public static Character[] box( char @NotNull [] bytes ) {
		Character[] arr = new Character[ bytes.length ];

		for ( int index = 0; index < bytes.length; index++ )
			arr[index] = bytes[index];

		return arr;
	}
	// endregion to box
}
