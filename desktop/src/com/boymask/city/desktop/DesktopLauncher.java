package com.boymask.city.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boymask.city.City;
import com.boymask.city.core.StarfishCollector3DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
/*		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.height = 1000;
		cfg.width = 1200;
		new LwjglApplication(new City(), cfg);*/

		Game myGame = new StarfishCollector3DGame();
		LwjglApplication launcher = new LwjglApplication(
				myGame, "Starfish Collector 3D", 800, 600 );
	}
}
