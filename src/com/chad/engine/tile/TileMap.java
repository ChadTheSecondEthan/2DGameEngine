package com.chad.engine.tile;

import com.chad.engine.Global;
import com.chad.engine.Window;
import com.chad.engine.entity.Entity;
import com.chad.engine.gfx.Renderer;
import com.chad.engine.gfx.Spritesheet;
import com.chad.engine.utils.GameFile;

import java.util.ArrayList;

public class TileMap extends Entity {

    // sprite sheet and tile types
    private Spritesheet spritesheet;
    private short[][] tiles;

    // width and height in numbers of tiles
    private int tx;
    private int ty;

    // tile size
    private int tileSize;

    public TileMap() { this(0, 0, 0); }

    public TileMap(int tx, int ty, int tileSize) {
        super();

        this.tx = tx;
        this.ty = ty;
        this.tileSize = tileSize;
    }

    private void getTiles(String filePath) {
        // get lines
        ArrayList<String> lines = new GameFile(filePath).readLines();

        // there should be 3 lines plus the height
        assert lines.size() == height + 3;

        // first 3 lines are x tiles, y tiles, and tile size
        tx = Integer.parseInt(lines.get(0));
        ty = Integer.parseInt(lines.get(1));
        tileSize = Integer.parseInt(lines.get(2));

        // twidth and theight should be positive
        assert tx > 0 && ty > 0;

        // create tiles array
        tiles = new short[tx][ty];

        // read tiles from each lines where numbers are separated by spaces
        for (int i = 0; i < ty; i++) {

            // get the line, create a new array for the tiles
            String line = lines.get(i + 3);
            short[] tileLine = new short[tx];

            // index and value variables to keep track of the current tile in the string
            int index = 0;
            int value = 0;

            // loop through each character to get the current tile value
            for (int j = 0; j < line.length(); j++) {

                // move on once a space character has been reached
                if (line.charAt(j) == ' ') {
                    tileLine[index] = (short) value;
                    index++;
                    value = 0;
                    continue;
                }

                // otherwise, shift the previous value by 10 and add the new value
                value = value * 10 + line.charAt(j) - 48;
            }

            // add this to the tiles array
            tiles[i] = tileLine;
        }
    }

    @Override
    public void update(float dt) {}

    @Override
    public void draw() {
        // get offset based on the position of the tilemap
        float ox = getX();
        float oy = getY();

        // draw each tile
        for (int x = 0; x < tx; x++)
            for (int y = 0; y < ty; y++)
                Renderer.draw(spritesheet.getSprite(tiles[x][y] + 10), y * tileSize + ox,
                        x * tileSize + oy, tileSize, tileSize);
    }

    public boolean checkTileCollisions(Entity other) {
        // TODO
        return false;
    }

    @Override
    public boolean setAttribute(String attr, String value) throws Exception {
        switch (attr) {
            case "src":
                getTiles(value);
                return true;
        }
        return super.setAttribute(attr, value);
    }

    public void setSpritesheet(Spritesheet s) { spritesheet = s; }

}
