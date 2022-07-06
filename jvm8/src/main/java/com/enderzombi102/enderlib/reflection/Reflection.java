package com.enderzombi102.enderlib.reflection;

import com.enderzombi102.enderlib.SafeUtils;
import com.sun.tools.attach.VirtualMachine;
import org.jetbrains.annotations.Range;
import sun.management.VMManagement;
import sun.misc.Unsafe;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static com.enderzombi102.enderlib.reflection.Getters.get;
import static com.enderzombi102.enderlib.reflection.Getters.getStatic;
import static com.enderzombi102.enderlib.reflection.Invokers.invoke;
import static com.enderzombi102.enderlib.reflection.Setters.setStatic;


@SuppressWarnings("unchecked")
public final class Reflection {
	static final MethodHandles.Lookup IMPL_LOOKUP;
	static final Unsafe UNSAFE;

	/**
	 * Add a value to an enum
	 * @param clazz the enum's class
	 * @param name name of the value to add
	 */
	@SuppressWarnings("unchecked")
	public static < T extends Enum<T> > void add( Class<T> clazz, String name ) {
		try {
			// get old array
			T[] arr = getStatic( clazz, "$VALUES", arrayType( clazz ) );
			// create new instance
			T value = (T) IMPL_LOOKUP.findConstructor(
				clazz,
				MethodType.methodType( void.class, String.class, int.class )
			).invoke( name, arr.length );
			// set $VALUES to new array
			setStatic(
				clazz,
				"$VALUES",
				new ArrayList<T>() {{
					Collections.addAll( this, arr );
					add( value );
				}}.toArray( arrayOf( clazz ) )
			);
			// add to const dir
			invoke(
				clazz,
				"enumConstantDirectory",
				Map.class
			).put( name, value );
		} catch ( Throwable e ) { throw new RuntimeException(e); }

	}

	/**
	 * Creates an array of the given class's objects and returns it
	 * @param clazz type's class to create the array of
	 * @return the created array
	 * @param <T> type to create the array of
	 */
	public static <T> T[] arrayOf( Class<T> clazz ) {
		return arrayOf( clazz, 0 );
	}

	/**
	 * Creates an array of the given class's objects and returns it
	 * @param clazz type's class to create the array of
	 * @param size the size of the to-be-created array
	 * @return the created array
	 * @param <T> type to create the array of
	 */
	public static <T> T[] arrayOf( Class<T> clazz, int size ) {
		return (T[]) Array.newInstance( clazz , size );
	}
	/**
	 * Creates an array of the given class's objects and returns its class object
	 * @param clazz type's class to create the array of
	 * @return the created array's class object
	 * @param <T> type to create the array of
	 */
	public static <T> Class<T[]> arrayType( Class<T> clazz ) {
		return (Class<T[]>) arrayOf( clazz, 0 ).getClass();
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
			VirtualMachine machine = VirtualMachine.attach(
				String.valueOf(
					invoke(
						get( ManagementFactory.getRuntimeMXBean(), "jvm", VMManagement.class ),
						"getProcessId",
						Integer.class
					)
				)
			);
			if ( library.endsWith( ".jar" ) )
				machine.loadAgent( library );
			else
				machine.loadAgentLibrary( library );
			machine.detach();
		} catch ( Throwable e ) { throw new RuntimeException( e ); }
	}

	/**
	 * Getter for the godly IMPL_LOOKUP
	 */
	public static MethodHandles.Lookup getImplLookup() {
		return IMPL_LOOKUP;
	}

	/**
	 * Getter for the sun's Unsafe object
	 */
	public static Unsafe getUnsafe() {
		return UNSAFE;
	}

	/**
	 * Returns the class and method that called the method that called getCallerInfo()
	 */
	public static CallerInfo getCallerInfo() throws ClassNotFoundException {
		return getCallerInfo( 1 );
	}

	/**
	 * Returns the class and method at frame point -frameOffset from the getCallerInfo() call
	 */
	public static CallerInfo getCallerInfo( @Range( from=0, to=100 ) int frameOffset ) throws ClassNotFoundException {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement frame = stack[ 2 + frameOffset ];

		return new CallerInfo(
			Class.forName( frame.getClassName() ),
			frame.getMethodName(),
			frame.getLineNumber()
		);
	}

	/**
	 * Returns the class and method that called the method that called getCallerInfoSafe()
	 */
	public static CallerInfo getCallerInfoSafe() {
		return getCallerInfoSafe( 1 );
	}

	/**
	 * Returns the class and method at frame point -frameOffset from the getCallerInfo() call
	 */
	public static CallerInfo getCallerInfoSafe( @Range( from=0, to=100 ) int frameOffset ) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement frame = stack[ 2 + frameOffset ];

		return new CallerInfo(
			SafeUtils.doSafely( () -> Class.forName( frame.getClassName() ) ),
			frame.getMethodName(),
			frame.getLineNumber()
		);
	}

	static {
		try {
			final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeField.setAccessible(true);
			UNSAFE = (Unsafe) unsafeField.get(null);
			final Field implLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
			IMPL_LOOKUP = (MethodHandles.Lookup) UNSAFE.getObject(
				UNSAFE.staticFieldBase(implLookupField),
				UNSAFE.staticFieldOffset(implLookupField)
			);
		} catch (Throwable e) { throw new RuntimeException(e); }
	}

	public static class CallerInfo {
		private final Class<?> clazz;
		private final String method;
		private final int line;

		public CallerInfo( Class<?> clazz, String method, int line ) {
			this.clazz = clazz;
			this.method = method;
			this.line = line;
		}

		public int line() {
			return line;
		}

		public String method() {
			return method;
		}

		public Class<?> clazz() {
			return clazz;
		}
	}

}

