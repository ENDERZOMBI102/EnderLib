package com.enderzombi102.enderlib.reflection;

import java.util.List;

/**
 * Class containing stuuf useful to work with Types
 */
public class Types {
	/** immutable list containing all boolean types */
	public static final List<Class<?>> BOOLEAN = List.of( Boolean.class, boolean.class );
	/** immutable list containing all char types */
	public static final List<Class<?>> CHAR = List.of( Character.class, char.class );
	/** immutable list containing all byte types */
	public static final List<Class<?>> BYTE = List.of( Byte.class, byte.class );
	/** immutable list containing all short types */
	public static final List<Class<?>> SHORT = List.of( Short.class, short.class );
	/** immutable list containing all integer types */
	public static final List<Class<?>> INTEGER = List.of( Integer.class, int.class );
	/** immutable list containing all long types */
	public static final List<Class<?>> LONG = List.of( Long.class, long.class );
	/** immutable list containing all float types */
	public static final List<Class<?>> FLOAT = List.of( Float.class, float.class );
	/** immutable list containing all double types */
	public static final List<Class<?>> DOUBLE = List.of( Double.class, double.class );

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
		return switch ( clazz.getName() ) {
			case "java.lang.Boolean" -> boolean.class;
			case "java.lang.Character" -> char.class;
			case "java.lang.Short" -> short.class;
			case "java.lang.Integer" -> int.class;
			case "java.lang.Float" -> float.class;
			case "java.lang.Double" -> double.class;
			default -> clazz;
		};
	}
}
