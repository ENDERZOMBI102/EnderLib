package com.enderzombi102.enderlib.reflection;

import java.util.List;

import static com.enderzombi102.enderlib.collections.ListUtil.listOf;

/**
 * Class containing stuf useful to work with Types
 */
public class Types {
	/** immutable list containing all boolean types */
	public static final List<Class<?>> BOOLEAN = listOf( Boolean.class, boolean.class );
	/** immutable list containing all char types */
	public static final List<Class<?>> CHAR = listOf( Character.class, char.class );
	/** immutable list containing all byte types */
	public static final List<Class<?>> BYTE = listOf( Byte.class, byte.class );
	/** immutable list containing all short types */
	public static final List<Class<?>> SHORT = listOf( Short.class, short.class );
	/** immutable list containing all integer types */
	public static final List<Class<?>> INTEGER = listOf( Integer.class, int.class );
	/** immutable list containing all long types */
	public static final List<Class<?>> LONG = listOf( Long.class, long.class );
	/** immutable list containing all float types */
	public static final List<Class<?>> FLOAT = listOf( Float.class, float.class );
	/** immutable list containing all double types */
	public static final List<Class<?>> DOUBLE = listOf( Double.class, double.class );

	// region unbox
	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static boolean unbox( Boolean box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static char unbox( Character box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static byte unbox( Byte box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static short unbox( Short box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static int unbox( Integer box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static long unbox( Long box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static float unbox( Float box ) {
		return box;
	}

	/**
	 * Unboxes a value
	 * @param box boxed value
	 * @return the unboxed value
	 */
	public static double unbox( Double box ) {
		return box;
	}

	/**
	 * Converts a boxed class to an unboxed one.
	 * @param clazz the boxed class
	 * @return the unboxed version, or clazz if there's not one
	 */
	public static Class<?> unbox( Class<?> clazz ) {
		switch ( clazz.getName() ) {
			case "java.lang.Boolean":
				return boolean.class;
			case "java.lang.Character":
				return char.class;
			case "java.lang.Short":
				return short.class;
			case "java.lang.Integer":
				return int.class;
			case "java.lang.Long":
				return long.class;
			case "java.lang.Float":
				return float.class;
			case "java.lang.Double":
				return double.class;
			default:
				return clazz;
		}
	}
	// endregion unbox

	// region box
	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Boolean box( boolean unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Character box( char unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Byte box( byte unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Short box( short unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Integer box( int unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Long box( long unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Float box( float unbox ) {
		return unbox;
	}

	/**
	 * Boxes a value
	 * @param unbox unboxed value
	 * @return the boxed value
	 */
	public static Double box( double unbox ) {
		return unbox;
	}

	/**
	 * Converts a boxed class to an unboxed one.
	 * @param clazz the boxed class
	 * @return the unboxed version, or clazz if there's not one
	 */
	public static Class<?> box( Class<?> clazz ) {
		switch ( clazz.getName() ) {
			case "boolean":
				return Boolean.class;
			case "char":
				return Character.class;
			case "short":
				return Short.class;
			case "int":
				return Integer.class;
			case "long":
				return Long.class;
			case "float":
				return Float.class;
			case "double":
				return Double.class;
			default:
				return clazz;
		}
	}
	// endregion box
}
