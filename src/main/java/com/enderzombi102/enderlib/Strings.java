package com.enderzombi102.enderlib;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * A class with many String-related utilities
 */
public class Strings {
	private static final Pattern UPPERCASE = Pattern.compile("[A-Z]");

	/**
	 * Used to format a string with {} syntax
	 * @param fmt string to format
	 * @param objs objects to format the string with
	 * @return the formatted string
	 */
	public static @NotNull String format( @NotNull String fmt, Object... objs ) {
		for ( Object obj : objs )
			fmt = fmt.replaceFirst("\\{}", obj.toString() );

		return fmt;
	}

	/**
	 * Function to convert a PascalCase string to a snake_case one
	 * @param string string to convert
	 */
	public static String pascalToSnake( String string ) {
		// Use regex to match all UPPERCASE letters, and replace them with their lowercase counterpart prefixed by _
		string = UPPERCASE.matcher(string).replaceAll( result -> "_" + result.group().toLowerCase() );
		// remove the starting _ as it's an artifact of this method
		return string.startsWith("_") ? string.replaceFirst( "_", "" ) : string;
	}

	/**
	 * Function to convert a snake_case string to a PascalCase one
	 * @param string string to convert
	 */
	public static String snakeToPascal( String string ) {
		String[] parts = string.split("_");
		for ( int i = 0; i < parts.length; i++ )
			parts[i] = Character.toUpperCase( parts[i].charAt(0) ) + parts[i].substring(1);

		return String.join( "", parts );
	}
}
