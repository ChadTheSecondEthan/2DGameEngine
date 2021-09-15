package com.chad.engine;

import com.chad.engine.entity.tween.Tween;
import com.chad.engine.gameState.GameState;
import com.chad.engine.gfx.Spritesheet;
import com.chad.engine.tile.TileMap;
import com.chad.engine.ui.ColorRenderer;
import com.chad.engine.utils.Functions;
import com.chad.engine.utils.Stats;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Game.init("Test");

		Game.setStates(new ExampleState());
		Game.start(0);
	}

	static class ExampleState extends GameState {

		private ArrayList<ColorRenderer> squares;
		private ArrayList<Tween> tweens;

		public ExampleState() {
			super();

			readEntitiesFromFile("exampleState");
		}

		@Override
		public void init() {
			int size = 100;

			// initialize tweens
			tweens = new ArrayList<>();

			// add spritesheet to tile map
			TileMap tm = findEntityById(1);
			tm.setSpritesheet(new Spritesheet("exampleTilemap.png", 512 / 8));

			// create stats
			Stats s = Stats.find("testStats");

			// get color renderers from the game state
			squares = findEntitiesById(0);
			float[] points = Functions.getSublinePoints(Window.getWidth(), squares.size(), size, false, true);

			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).setBounds(points[i], 0, size, size);
				squares.get(i).centerY();

				tweens.add(Tween.oscillateY(squares.get(i), 100, 1, (float) Math.PI * 2 * ((float) i / squares.size())));
			}
		}

		@Override
		public void update(float dt) {
			super.update(dt);

			for (Tween t : tweens)
				t.update(dt);
		}
	}

}
