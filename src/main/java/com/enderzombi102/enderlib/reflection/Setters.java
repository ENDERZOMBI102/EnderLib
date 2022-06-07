package com.enderzombi102.enderlib.reflection;

import static com.enderzombi102.enderlib.reflection.Reflection.IMPL_LOOKUP;

public final class Setters {
	private Setters() { }

	/**
	 * Set a field on an object
	 * @param obj object to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 */
	public static void set( Object obj, String name, Object value ) {
		set( obj, name, value, value.getClass() );
	}

	/**
	 * Set a field on an object
	 * @param obj object to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 * @param valueType Class of the value
	 */
	public static void set( Object obj, String name, Object value, Class<?> valueType ) {
		try {
			IMPL_LOOKUP.findSetter( obj.getClass(), name, valueType ).invoke(obj, value);
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}

	/**
	 * Set a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 * @param value new value for the field
	 */
	public static <T> void setStatic( Class<?> clazz, String name, T value ) {
		try {
			IMPL_LOOKUP.findStaticSetter( clazz, name, value.getClass() ).invoke(value);
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}
}
