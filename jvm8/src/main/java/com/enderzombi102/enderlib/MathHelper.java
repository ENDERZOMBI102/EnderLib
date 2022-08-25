package com.enderzombi102.enderlib;

public final class MathHelper {
	private MathHelper() { }

	public static float clamp( float min, float max, float value ) {
		return Math.max( min, Math.min( max, value ) );
	}

	public static double clamp( double min, double max, double value ) {
		return Math.max( min, Math.min( max, value ) );
	}

	public static int clamp( int min, int max, int value ) {
		return Math.max( min, Math.min( max, value ) );
	}

	public static long clamp( long min, long max, long value ) {
		return Math.max( min, Math.min( max, value ) );
	}

	public static float lerp( float delta, float start, float end ) {
		return start + delta * ( end - start );
	}

	public static double lerp( double delta, double start, double end ) {
		return start + delta * ( end - start );
	}

	public static double clampedLerp( double start, double end, double delta ) {
		return clamp( start, end, lerp( delta, start, end ) );
	}

	public static float clampedLerp( float start, float end, float delta ) {
		return clamp( start, end, lerp( delta, start, end ) );
	}
}
