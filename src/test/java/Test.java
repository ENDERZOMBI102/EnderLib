import com.enderzombi102.enderlib.RuntimeUtil;
import com.enderzombi102.enderlib.reflection.Getters;
import com.enderzombi102.enderlib.reflection.Invokers;
import com.enderzombi102.enderlib.reflection.Reflection;
import com.enderzombi102.enderlib.reflection.Setters;

public class Test {
	public static void main( String[] argv ) throws Throwable {
		// test static getter/setter/invokers
		assert Private.getZero() == 0;
		Invokers.invokeStatic( Private.class, "increment" );
		assert Getters.getStatic( Private.class, "zero", int.class ) == 1;
		Setters.setStatic( Private.class, "zero", 23 );
		assert Private.getZero() == 23;

		// test getter/setter/invokers
		var privat = new Private1(12);
		assert Invokers.invoke( privat, "num", int.class ) == 12;
		Setters.set( privat, "num", 23 );
		assert privat.num() == 23;

		// lets fix valve
		assert Valve.values().length == 5;
		Reflection.add( Valve.class, "Three" );
		assert Valve.values().length == 6;

		RuntimeUtil.openModule( "jdk.hotspot" );
	}
}
