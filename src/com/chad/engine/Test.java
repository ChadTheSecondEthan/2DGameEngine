package com.chad.engine;

import com.chad.engine.entity.Entity;
import com.chad.engine.gameState.GameState;
import com.chad.engine.ui.ColorRenderer;

import java.util.ArrayList;

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
		public void init() {
			int index = 0;
			for (Entity e : getEntities())
				if (e instanceof ColorRenderer) {
					e.setBounds(index * 125, 0, 100, 100);
					((ColorRenderer) e).centerY();

					index++;
				}
		}
	}

}
