package Main;

import GameState.ExampleState;
import GameState.GameState;

public class Test {

	public static void main(String[] args) {
		Game game = new Game("Test");

		game.setStates(new GameState[] {
			new ExampleState()
		});
		game.start(0);
	}

}
