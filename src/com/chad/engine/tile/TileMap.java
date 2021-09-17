package com.chad.engine.tile;

import com.chad.engine.entity.Entity;
import com.chad.engine.gfx.Renderer;
import com.chad.engine.gfx.Spritesheet;
import com.chad.engine.utils.GameFile;
import com.chad.engine.utils.Rectf;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class TileMap extends Entity {

    // sprite sheet and tile types
    private Spritesheet spritesheet;
    private short[][] tiles;

    // the types of tiles with which the player will collide
    private short[] collisionTypes;

    // width and height in numbers of tiles
    private int tx;
    private int ty;

    // tile size
    private int tileSize;

    public TileMap() {
        tiles = new short[0][0];
        collisionTypes = new short[0];
    }

    private void setTiles(String filePath) {
        // get lines
        ArrayList<String> lines = new GameFile(filePath).readLines();

        // first 3 lines are x tiles, y tiles, and tile size
        tx = Integer.parseInt(lines.get(0));
        ty = Integer.parseInt(lines.get(1));
        tileSize = Integer.parseInt(lines.get(2));

        // create tiles array
        tiles = new short[tx][ty];

        // read tiles from each line where numbers are separated by spaces
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

            for (; index < tx; index++)
                tileLine[index] = (short) value;

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
                Renderer.draw(spritesheet.getSprite(tiles[y][x]), x * tileSize + ox,
                        y * tileSize + oy, tileSize, tileSize);
    }

    @Override
    public boolean setAttribute(String attr, String value) throws Exception {
        if ("src".equals(attr)) {
            setTiles(value);
            return true;
        }
        return super.setAttribute(attr, value);
    }

    public boolean tileCanCollide(int x, int y) {
        short tile = tiles[y][x];
        for (short type : collisionTypes)
            if (tile == type)
                return true;
        return false;
    }
    public boolean tileCanCollide(int index) {
        return tileCanCollide(index / tx, index % tx);
    }

    public void setCollisionTypes(short[] types) { collisionTypes = types; }
    public void setSpritesheet(Spritesheet s) { spritesheet = s; }
    public void setTile(int x, int y, int tile) { tiles[y][x] = (short) tile; }
    public void setTile(int index, int tile) {
        tiles[index / tx][index % tx] = (short) tile;
    }

    public void setTiles(short[][] tiles) { this.tiles = tiles; }
    public void setTileSize(int tileSize) { this.tileSize = tileSize; }

    public Rectf[] getTileBoundsAround(Rectf other) {
        // get the top left tile index
        int ix = (int) other.x / tileSize;
        int iy = (int) other.y / tileSize;

        return new Rectf[] {
                getTileBounds(ix, iy), // top left
                getTileBounds(ix + 1, iy), // top right
                getTileBounds(ix + 1, iy + 1), // bottom right
                getTileBounds(ix, iy) // bottom left
        };
    }

    public Rectf[] getCollidableTilesAround(Rectf other) {
        // get the top left tile index
        int ix = (int) other.x / tileSize;
        int iy = (int) other.y / tileSize;

        // make a list for the bounds
        ArrayList<Rectf> collidableBounds = new ArrayList<>();
        if (tileCanCollide(ix, iy))
            collidableBounds.add(getTileBounds(ix, iy));
        if (tileCanCollide(ix + 1, iy))
            collidableBounds.add(getTileBounds(ix + 1, iy));
        if (tileCanCollide(ix + 1, iy + 1))
            collidableBounds.add(getTileBounds(ix + 1, iy + 1));
        if (tileCanCollide(ix, iy + 1))
            collidableBounds.add(getTileBounds(ix, iy + 1));

        // return as an array
        return collidableBounds.toArray(new Rectf[0]);
    }

    public Rectf getTileBounds(int index) {
        return new Rectf(getX() + (index / tx) * tileSize, getY() + (index % tx) * tileSize, tileSize, tileSize);
    }

    public Rectf getTileBounds(int x, int y) {
        return new Rectf(getX() + x * tileSize, getY() + y * tileSize, tileSize, tileSize);
    }
}
