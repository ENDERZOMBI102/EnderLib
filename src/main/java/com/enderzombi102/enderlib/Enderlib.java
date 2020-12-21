package com.enderzombi102.enderlib;

import lombok.Getter;
import net.fabricmc.api.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnvironmentInterface(value = EnvType.CLIENT, itf = ClientModInitializer.class)
public class Enderlib implements ClientModInitializer, ModInitializer {

	public static final Logger logger = LogManager.getLogger("enderlib");
	@Getter
	private static Enderlib instance;

	public Enderlib() {
		instance = this;
	}

	@Override
	public void onInitialize() {
		logger.info("Welcome to the future of everything!");
	}


	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {

	}
}
