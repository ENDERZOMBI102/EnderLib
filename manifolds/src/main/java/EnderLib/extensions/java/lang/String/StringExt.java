package EnderLib.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import com.enderzombi102.enderlib.Strings;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

@Extension
public class StringExt {
	/**
	 * Format this string with the following arguments
	 * @param objs objects to format the string with
	 * @return the formatted string
	 */
	public static @NotNull String with( @This String thiz, Object... objs ) {
		return Strings.format( thiz, objs );
	}

	/**
	 * Method to convert a PascalCase string to a snake_case one
	 */
	public static @NotNull String pascalToSnake( @This String thiz ) {
		return Strings.pascalToSnake( thiz );
	}

	/**
	 * Method to convert a snake_case string to a PascalCase one
	 */
	public static @NotNull String snakeToPascal( @This String thiz ) {
		return Strings.snakeToPascal( thiz );
	}

	/**
	 * Print this {@link String} to {@link System#out}
	 */
	public static void print( @This String thiz ) {
		thiz.print( System.out );
	}

	/**
	 * Print this {@link String} to the given stream
	 */
	public static void print( @This String thiz, PrintStream stream ) {
		stream.print( thiz );
	}

	/**
	 * Print this {@link String} + \n to {@link System#out}
	 */
	public static void println( @This String thiz ) {
		thiz.println( System.out );
	}

	/**
	 * Print this {@link String} + \n to the given stream
	 */
	public static void println( @This String thiz, PrintStream stream ) {
		stream.println( thiz );
	}
}