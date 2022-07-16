public class Main {
	public static void main( String[] argv ) {
		System.out.println("Hello world!");
		sun.jvm.hotspot.runtime.VM.shutdown();
		System.out.println("Hello world 2!");
	}
}
