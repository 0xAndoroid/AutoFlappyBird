package ua.andoroidflappybird.desktop;

import com.badlogic.gdx.Files;
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
		config.addIcon("icons/bird-16.png", Files.FileType.Internal);
		config.addIcon("icons/bird-32.png", Files.FileType.Internal);
		config.addIcon("icons/bird-128.png", Files.FileType.Internal);
		config.addIcon("icons/bird-512.png", Files.FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
