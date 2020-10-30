package ua.andoroidflappybird;

import com.badlogic.gdx.Game;
import ua.andoroidflappybird.screens.FlappyBird;

public class Main extends Game {
	@Override
	public void create() {
		Statements st = new Statements(this);
		setScreen(new FlappyBird(st));
	}
}
