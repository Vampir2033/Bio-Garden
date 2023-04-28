package ru.dorogin.biogarden;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.dorogin.biogarden.BioGardenGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	private final static int WIDTH = 1366;
	private final static int HEIGHT = 768;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(WIDTH, HEIGHT);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("Bio Garden");
		new Lwjgl3Application(new BioGardenGame(), config);
	}
}
