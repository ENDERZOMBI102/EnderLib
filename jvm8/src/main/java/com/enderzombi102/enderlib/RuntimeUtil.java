package com.enderzombi102.enderlib;

import com.sun.tools.attach.VirtualMachine;
import org.jetbrains.annotations.NotNull;
import sun.management.VMManagement;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.enderzombi102.enderlib.reflection.Getters.get;
import static com.enderzombi102.enderlib.reflection.Getters.getStatic;
import static com.enderzombi102.enderlib.reflection.Invokers.invoke;

public class RuntimeUtil {

	/**
	 * Attach a java agent to the running jvm.<br>
	 * It may be a jar or a native executable, the extension is used to determine which one it is. ( .jar == java, .* == native )
	 * @param library the jar/dll the agent is in, may be the same jar
	 */
	public static void attachAgent( Path library ) {
		attachAgent( library.toAbsolutePath().toString() );
	}

	/**
	 * Attach a java agent to the running jvm.<br>
	 * It may be a jar or a native executable, the extension is used to determine which one it is. ( .jar == java, .* == native )
	 * @param library the jar/dll the agent is in, may be the same jar
	 */
	public static void attachAgent( File library ) {
		attachAgent( library.getAbsolutePath() );
	}

	/**
	 * Attach a java agent to the running jvm.<br>
	 * It may be a jar or a native executable, the extension is used to determine which one it is. ( .jar == java, .* == native )
	 * @param library the jar/dll the agent is in, may be the same jar
	 */
	public static void attachAgent( URL library ) {
		attachAgent( library.getPath() );
	}

	/**
	 * Attach a java agent to the running jvm.<br>
	 * It may be a jar or a native executable, the extension is used to determine which one it is. ( .jar == java, .* == native )
	 * @param library the jar/dll the agent is in, may be the same jar
	 */
	@SuppressWarnings("unchecked")
	public static void attachAgent( String library ) {
		try {
			// force the jvm to accept self-attachment
			getStatic(
				Class.forName("jdk.internal.misc.VM"),
				"savedProps",
				Map.class
			).put( "jdk.attach.allowAttachSelf", "true" );
			// attach & load agent
			VirtualMachine machine = VirtualMachine.attach( String.valueOf( invoke(
				get( ManagementFactory.getRuntimeMXBean(), "jvm", VMManagement.class ),
				"getProcessId",
				Integer.class
			) ) );
			if ( library.endsWith( ".jar" ) )
				machine.loadAgent( library );
			else
				machine.loadAgentLibrary( library );
			machine.detach();
		} catch ( Throwable e ) { throw new RuntimeException( e ); }
	}

	/**
	 * Find's a jar's position by looking at a class's location.<br/>
	 * This assumes that the directory structure looks in a specific way.
	 *
	 * @param clazz      class to look up the jar for
	 * @param name 		 name of the jar
	 * @param version	 version of the jar
	 */
	public static Path findJar( @NotNull String clazz, @NotNull String name, @NotNull String version ) throws ClassNotFoundException {
		return findJar( Class.forName( clazz, false, RuntimeUtil.class.getClassLoader() ), name, version );
	}

	/**
	 * Find's a jar's position by looking at a class's location.<br/>
	 * This assumes that the directory structure looks in a specific way.
	 *
	 * @param clazz      class to look up the jar for
	 * @param name 		 name of the jar
	 * @param version	 version of the jar
	 */
	public static Path findJar( @NotNull Class<?> clazz, @NotNull String name, @NotNull String version ) {
		return findJar( clazz, dev -> name + "-" + version + ( dev ? "-dev" : "" ) + ".jar" );
	}

	/**
	 * Find's a jar's position by looking at a class's location.<br/>
	 * This assumes that the directory structure looks in a specific way.
	 *
	 * @param clazz      class to look up the jar for
	 * @param nameGetter called with true if checking for a dev jar, used to get the name of the jarfile
	 */
	public static Path findJar( @NotNull Class<?> clazz, @NotNull Function<Boolean, String> nameGetter ) {
		URL loc = clazz.getProtectionDomain().getCodeSource().getLocation();
		if ( loc.getPath().endsWith( ".jar" ) )
			// its already the jar
			return Paths.get( loc.getPath() );
		else if ( Paths.get( "./../build/devlibs/", nameGetter.apply(true) ).toFile().exists() )
			return Paths.get( "./../build/devlibs/", nameGetter.apply(true) );
		else if ( Paths.get( "./../build/libs/", nameGetter.apply(false) ).toFile().exists() )
			return Paths.get( "./../build/libs/", nameGetter.apply(false) );
		else
			throw new IllegalStateException("Failed to find jar!");
	}

	/**
	 * Calls the provided supplier and returns its result.
	 * @param supplier supplier to call.
	 * @param <T> return type.
	 * @return supplier's result.
	 */
	public static <T> T run( Supplier<T> supplier ) {
		return supplier.get();
	}
}
