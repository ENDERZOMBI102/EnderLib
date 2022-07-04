package com.enderzombi102.enderlib;

import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.util.Map;

import static com.enderzombi102.enderlib.reflection.Getters.getStatic;
import static com.enderzombi102.enderlib.reflection.Invokers.invokeStatic;

@SuppressWarnings("unchecked")
public class Runtime {
	/**
	 * Loads a module and make it usable by the unnamed module
	 * @param module module's name
	 * @throws Throwable if something went wrong
	 */
	public static void openModule( String module ) throws Throwable {
		invokeStatic(
			"jdk.internal.module.Modules",
			"loadModule",
			Module.class,
			module
		);
	}

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
	public static void attachAgent( String library ) {
		try {
			// force the jvm to accept self-attachment
			getStatic(
				Class.forName("jdk.internal.misc.VM"),
				"savedProps",
				Map.class
			).put( "jdk.attach.allowAttachSelf", "true" );
			// attach & load agent
			VirtualMachine machine = VirtualMachine.attach( String.valueOf( ManagementFactory.getRuntimeMXBean().getPid() ) );
			if ( library.endsWith( ".jar" ) )
				machine.loadAgent( library );
			else
				machine.loadAgentLibrary( library );
			machine.detach();
		} catch ( Throwable e ) { throw new RuntimeException( e ); }
	}
}
