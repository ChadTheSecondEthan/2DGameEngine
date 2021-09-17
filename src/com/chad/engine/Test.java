package com.chad.engine;

import com.chad.engine.entity.tween.Tween;
import com.chad.engine.gameState.GameState;
import com.chad.engine.gfx.Spritesheet;
import com.chad.engine.tile.TileMap;
import com.chad.engine.entity.ColorRenderer;
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

		public ExampleState() {
			super();

			readEntitiesFromFile("exampleState");
		}

		@Override
		public void init() {

			// add spritesheet to tile map
			TileMap tm = findEntityById(1);
			tm.setSpritesheet(new Spritesheet("exampleTilemap.png", 512 / 8));

			for (int i = 0; i < 10; i++)
				tm.setTile(i * 4, 10);
		}
	}

}
