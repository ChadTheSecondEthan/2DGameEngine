package com.chad.engine;

import com.chad.engine.gameState.GameState;

public class Test {

	public static void main(String[] args) {
		Game.init("Test");

		Game.setStates(new ExampleState());
		Game.start(0);
	}

	static class ExampleState extends GameState {

		public ExampleState() {
			super();

			readEntitiesFromFile("exampleState");
		}

		@Override
		public void init() {}
	}

}
