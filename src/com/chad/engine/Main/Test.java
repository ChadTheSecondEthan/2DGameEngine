package com.chad.engine.Main;

import com.chad.engine.GameState.ExampleState;
import com.chad.engine.GameState.GameState;

public class Test {

	public static void main(String[] args) {
		Game game = new Game("Test");

		game.setStates(new GameState[] {
			new ExampleState()
		});
		game.start(0);
	}

}
