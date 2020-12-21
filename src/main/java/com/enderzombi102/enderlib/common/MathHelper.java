package com.enderzombi102.enderlib.common;

public class MathHelper {

	public static float interpolateValues(float prevVal, float nextVal, float partialTick) {
		return prevVal + partialTick * (nextVal - prevVal);
	}

}
