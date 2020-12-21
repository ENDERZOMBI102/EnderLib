package com.enderzombi102.enderlib;

import lombok.Getter;
import lombok.Setter;

public class Config {

	public static void loadConfigFromFile() {

	}

	@Getter
	private static boolean tutorialDisabled = true;

	@Getter
	private static short worldPortalRecursionLevel = 2;

	@Getter
	private static boolean worldPortalsEnabled = true;

	@Getter
	private static short maxRendersPerTick = 16;

	@Getter
	@Setter
	private static boolean usingRomanNumbers = false;

	@Getter
	private static short radix = 16;







}
