package RUSD.desktop;



import rusd.myGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	
	
	public static void main (String[] arg) {
	
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Planet Defender";
		
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		config.fullscreen = false;
		config.x = 1917;
		config.y = 180;
		
		new LwjglApplication(new myGame(), config);
		
	}
}
