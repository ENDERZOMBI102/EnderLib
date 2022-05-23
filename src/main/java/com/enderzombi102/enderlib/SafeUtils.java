package com.enderzombi102.enderlib;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods for calling methods without having to deal with exceptions.
 * Intended use case is calling methods with checked exceptions when it's known that for some reason it won't throw.
 * @author MattiDragon
 */
public final class SafeUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger( "EnderLib | SafeUtils" );
	private SafeUtils() { }

	/**
	 * Call the runnable and ignores any exceptions it throws. Exceptions will be printed.
	 * @param runnable The runnable to run.
	 */
	public static void doSafely( @NotNull ThrowingRunnable<?> runnable ) {
		try {
			runnable.run();
		} catch ( Throwable e ) {
			LOGGER.error( "Could not execute safely", e );
		}
	}

	/**
	 * Uses the supplier to get a value and returns it. If it throws an exception {@code null} will be returned. Exceptions will be printed.
	 * @param supplier The supplier to use.
	 */
	public static <T> T doSafely( @NotNull ThrowingSupplier<T, ?> supplier ) {
		try {
			return supplier.get();
		} catch ( Throwable e ) {
			LOGGER.error( "Could not execute safely", e );
			return null;
		}
	}

	public interface ThrowingRunnable<TEx extends Throwable> {
		void run() throws TEx;
	}

	public interface ThrowingSupplier<T, TEx extends Throwable> {
		T get() throws TEx;
	}
}
