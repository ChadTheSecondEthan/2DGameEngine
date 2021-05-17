package Main;

import GameState.ExampleState;

public class Test {

	public static void main(String[] args) {
		Game game = new Game("Test");
		game.start(new ExampleState(game.getGameLoop()));
	}

}
