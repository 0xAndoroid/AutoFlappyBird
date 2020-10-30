package ua.andoroidflappybird.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ua.andoroidflappybird.Main;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(Toolkit.getDefaultToolkit().getScreenSize().width *0.85f);
		config.height = (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.85f);
		config.x = 0;
		config.y = 0;
		config.resizable = false;
		new LwjglApplication(new Main(), config);
	}
}
