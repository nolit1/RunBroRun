package com.runbrorun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.runbrorun.Bro;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Run Bro";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new Bro(), config);
	}
}
