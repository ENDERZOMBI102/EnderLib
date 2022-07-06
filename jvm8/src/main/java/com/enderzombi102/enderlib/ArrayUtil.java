package com.enderzombi102.enderlib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

	public static Class<?>[] classes( Object... values ) {
		return convertBoxes(
			Arrays.stream( values )
				.map( Object::getClass )
				.toArray( Class[]::new )
		);
	}

	public static Class<?>[] convertBoxes( Class<?>[] classes ) {
		for ( int i = 0; i < classes.length; i++ )
			classes[i] = classes[i] == Boolean.class ? boolean.class :
				classes[i] == Character.class ? char.class :
					classes[i] == Short.class ? short.class :
						classes[i] == Integer.class ? int.class :
							classes[i] == Float.class ? float.class :
								classes[i] == Double.class ? double.class :
									classes[i];
		return classes;
	}
}
