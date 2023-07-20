package com.enderzombi102.enderlib;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Utilities to work in a more functional style.
 */
public class Functional {
	/**
	 * Applies a function to a value if it is not {@code null} and returns its result, else returns {@code null}.
	 * @param object object to test.
	 * @param func function to apply to the object.
	 * @param <T> the object's type.
	 * @param <R> the return type of the function.
	 * @return the result of applying the function or {@code null}.
	 */
	@Contract("null, _ -> null")
	public static <T, R> @Nullable R ifNonNull( @Nullable T object, @NotNull Function<T, R> func ) {
		if ( object != null )
			return func.apply( object );
		return null;
	}

	/**
	 * Configure an object created by the given constructor with the given function, then return the configured object.<br>
	 * This might be used to configure objects in a functional-like approach.
	 * @param constructor the constructor for the object to configure.
	 * @param config configuring function.
	 * @param <T> type of the object.
	 * @return the configured object.
	 */
	@Contract("_, _ -> !null")
	public static <T> @NotNull T configure( @NotNull Supplier<T> constructor, @NotNull Consumer<T> config ) {
		var value = constructor.get();
		config.accept( value );
		return value;
	}

	/**
	 * Configure an object constructed by calling a given constructor with a parameter with the given function, then return the configured object.<br>
	 * This might be used to configure objects in a functional-like approach.
	 * @param constructor the constructor for the object to configure.
	 * @param param the parameter to give to the constructor.
	 * @param config configuring function.
	 * @param <P> the type of the parameter.
	 * @param <T> type of the object.
	 * @return the configured object.
	 */
	@Contract("_, _, _ -> !null")
	public static <P, T> @NotNull T configure( @NotNull Function<P, T> constructor, @UnknownNullability P param, @NotNull Consumer<T> config ) {
		var value = constructor.apply( param );
		config.accept( value );
		return value;
	}

	/**
	 * Executes a function if the given value is {@code null} and returns its result, else returns the value.
	 * @param object object to test.
	 * @param func function to call if the object is {@code null}.
	 * @param <T> the object's type.
	 * @return the result of applying the function or {@code null}.
	 */
	@Contract("null, _ -> _; !null, _ -> !null")
	public static <T> @Nullable T ifNull( @Nullable T object, @NotNull Supplier<T> func ) {
		if ( object == null )
			return func.get();
		return object;
	}

	/**
	 * Applies a function to a {@link AutoCloseable} resource.<br>
	 * may be used, for example, to read a file.
	 * @param supplier a getter for the closeable object to apply the function to.
	 * @param func function to apply to the object.
	 * @param <T> input type.
	 * @param <R> return type.
	 * @return whatever the applying function returns.
	 * @throws RuntimeException if there was an uncaught {@link Throwable}.
	 */
	public static <T extends AutoCloseable, R> @UnknownNullability R with( @NotNull SafeUtils.ThrowingSupplier<T, ?> supplier, @NotNull ThrowingFunction<T, R, ?> func ) {
		try ( var e = supplier.get() ) {
			return func.apply( e );
		} catch ( Throwable e ) {
			throw new RuntimeException( e );
		}
	}

	/**
	 * Applies a function to a {@link AutoCloseable} resource.<br>
	 * may be used, for example, to read a file.
	 * @param closeable a closeable object to apply the function to.
	 * @param func function to apply to the object.
	 * @param <T> input type.
	 * @param <R> return type.
	 * @return whatever the applying function returns.
	 * @throws RuntimeException if there was an uncaught {@link Throwable}.
	 */
	public static <T extends AutoCloseable, R> @UnknownNullability R with( @NotNull T closeable, @NotNull ThrowingFunction<T, R, ?> func ) {
		try ( var e = closeable ) {
			return func.apply( e );
		} catch ( Throwable e ) {
			throw new RuntimeException( e );
		}
	}

	/**
	 * Creates a function that applies the given function to a value and returns it.
	 * @param modifier function that will be applied.
	 * @param <T> type of the modifying object.
	 * @return a function that applies the given function.
	 */
	public static <T> @NotNull UnaryOperator<T> modify( @NotNull Consumer<T> modifier ) {
		return obj -> { modifier.accept( obj ); return obj; };
	}

	/**
	 * Throws a {@link RuntimeException}.
	 * @param exc exception to be thrown.
	 */
	public static void raise( @NotNull RuntimeException exc ) {
		throw exc;
	}

	/**
	 * A function that processes a value and might throw an exception.
	 * @param <T> function's parameter type.
	 * @param <R> function's return type.
	 * @param <Ex> function's thrown exception type.
	 */
	public interface ThrowingFunction<T, R, Ex extends Throwable> {
		R apply( T t ) throws Ex;
	}
}
