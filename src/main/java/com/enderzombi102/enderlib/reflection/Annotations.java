package com.enderzombi102.enderlib.reflection;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * Class containing functions useful to work with annotations
 */
public class Annotations {
	/**
	 * Utility function to handle annotations.
	 * @param obj object which can be annotated
	 * @param annotation annotation's class
	 * @param getter value getter
	 * @return the value of the requested annotation, or null
	 * @param <T> return type
	 * @param <A> annotation type
	 */
	public static <T, A extends Annotation> @Nullable T annotation( AnnotatedElement obj, Class<A> annotation, Function<A, T> getter ) {
		return annotation( obj, annotation, getter, null );
	}

	/**
	 * Utility function to handle annotations.
	 * @param obj object which can be annotated
	 * @param annotation annotation's class
	 * @param getter value getter
	 * @param fallback returned value if the annotation is not present
	 * @return the value of the requested annotation, or fallback
	 * @param <T> return type
	 * @param <A> annotation type
	 */
	public static <T, A extends Annotation> T annotation( AnnotatedElement obj, Class<A> annotation, Function<A, T> getter, T fallback ) {
		return Optional.ofNullable( obj.getAnnotation( annotation ) )
			.map( getter )
			.orElse( fallback );
	}
}
