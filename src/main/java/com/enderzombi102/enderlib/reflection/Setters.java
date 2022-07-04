package com.enderzombi102.enderlib.reflection;

import static com.enderzombi102.enderlib.reflection.Reflection.IMPL_LOOKUP;

@SuppressWarnings({ "unchecked", "unused" })
public final class Setters {
	private Setters() { }

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new short value for the field
	 */
	public static void set( Object obj, String name, short value ) {
		set( obj, name, value, short.class );
	}

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new char value for the field
	 */
	public static void set( Object obj, String name, char value ) {
		set( obj, name, value, char.class );
	}

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new int value for the field
	 */
	public static void set( Object obj, String name, int value ) {
		set( obj, name, value, int.class );
	}

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new float value for the field
	 */
	public static void set( Object obj, String name, float value ) {
		set( obj, name, value, float.class );
	}

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new double value for the field
	 */
	public static void set( Object obj, String name, double value ) {
		set( obj, name, value, double.class );
	}

	/**
	 * Set a field on an object
	 * @param obj class to set the field on
	 * @param name name of the field to set
	 * @param value new boolean value for the field
	 */
	public static void set( Object obj, String name, boolean value ) {
		set( obj, name, value, boolean.class );
	}

	/**
	 * Set a field on an object
	 * @param obj object to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 */
	public static <T> void set( Object obj, String name, T value ) {
		set( obj, name, value, (Class<T>) value.getClass() );
	}

	/**
	 * Set a field on an object
	 * @param obj object to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 * @param valueType Class of the value
	 */
	public static <T> void set( Object obj, String name, T value, Class<T> valueType ) {
		try {
			IMPL_LOOKUP.findSetter( obj.getClass(), name, valueType ).invoke(obj, value);
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new short value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, short value ) {
		setStatic( clazz, name, value, short.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new char value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, char value ) {
		setStatic( clazz, name, value, char.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new int value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, int value ) {
		setStatic( clazz, name, value, int.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new float value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, float value ) {
		setStatic( clazz, name, value, float.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new double value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, double value ) {
		setStatic( clazz, name, value, double.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new boolean value for the field
	 */
	public static void setStatic( Class<?> clazz, String name, boolean value ) {
		setStatic( clazz, name, value, boolean.class );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 */
	public static <T> void setStatic( Class<?> clazz, String name, T value ) {
		setStatic( clazz, name, value, (Class<T>) value.getClass() );
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 * @param type type of the value
	 */
	public static <T> void setStatic( Class<?> clazz, String name, T value, Class<T> type ) {
		try {
			IMPL_LOOKUP.findStaticSetter( clazz, name, type ).invoke(value);
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}
}
