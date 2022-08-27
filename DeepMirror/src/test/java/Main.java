import sun.jvm.hotspot.HotSpotAgent;

public class Main {
	public static void main( String[] argv ) {
		System.out.println("Hello world!");
		var x = new HotSpotAgent();
		x.attach( 37000 );
		var debugger= x.getDebugger();
		var vm = x.getDebugger();
		x.detach();
	}
}
