package com.chad.engine;

import com.chad.engine.gameState.GameState;
import com.chad.engine.gfx.Renderer;
import com.chad.engine.gfx.Spritesheet;
import com.chad.engine.tile.TileMap;
import com.chad.engine.utils.Rectf;

import java.awt.*;

public class Test {

	public static void main(String[] args) {
		Game.init("Test");

		Game.setStates(new ExampleState());
		Game.start(0);
	}

	static class ExampleState extends GameState {

		private TileMap tileMap;

		public ExampleState() {
			super();

			readEntitiesFromFile("exampleState");
		}

		@Override
		public void init() {
			// add spritesheet to tile map
			tileMap = findEntityById(1);
			tileMap.setSpritesheet(new Spritesheet("exampleTilemap.png", 512 / 8));
			tileMap.setCollisionTypes(new short[] { 10 });

			for (int i = 0; i < 100; i++)
				tileMap.setTile(i, 10);
			tileMap.setTile(0, 1, 11);
		}

		@Override
		public void update(float dt) {
			super.update(dt);

			Renderer.camera.x++;
		}

		@Override
		public void draw() {
			super.draw();

			Renderer.setColor(Color.red);

			for (Rectf bounds : tileMap.getCollidableTilesAround(new Rectf(10, 10, 10, 10)))
				Renderer.fill(bounds.x + bounds.w * 0.5f - 10, bounds.y + bounds.h * 0.5f - 10, 20, 20);
		}
	}

}
