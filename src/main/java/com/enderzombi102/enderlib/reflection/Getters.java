package com.enderzombi102.enderlib.reflection;

import static com.enderzombi102.enderlib.reflection.ReflectionUtil.IMPL_LOOKUP;

@SuppressWarnings("unchecked")
public final class Getters {
	private Getters() { }

	/**
	 * Get the value of a field on an object
	 * @param obj object to set the field on
	 * @param name name of the field to set
	 * @param valueType Class of the value
	 */
	public static <T> T get( Object obj, String name, Class<T> valueType ) {
		try {
			return (T) IMPL_LOOKUP.findGetter( obj.getClass(), name, valueType ).invoke(obj);
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}

	/**
	 * Get the value of a static field on a class
	 * @param clazz class to set the field on
	 * @param name name of the field to set
	 */
	public static <T> T getStatic( Class<?> clazz, String name, Class<T> valueType ) {
		try {
			return (T) IMPL_LOOKUP.findStaticGetter( clazz, name, valueType ).invoke();
		} catch ( Throwable e ) { throw new RuntimeException(e); }
	}
}
