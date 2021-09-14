package com.chad.engine.tile;

import com.chad.engine.entity.Entity;
import com.chad.engine.utils.GameFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TileMap extends Entity {

    private short[][] tiles;
    private int tileSize;

    private void getTiles(String filePath) throws Exception {
        // first 3 lines are width, height, and tile size
        // after that, each tile is separated by a space
        ArrayList<String> lines = new GameFile(filePath).readLines();
        width = Integer.parseInt(lines.get(0));
        height = Integer.parseInt(lines.get(1));
        tileSize = Integer.parseInt(lines.get(2));

        // there should be 3 lines plus the height
        assert lines.size() == height + 3;

        // read tiles from each line
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {

    }

    @Override
    public boolean setAttribute(String attr, String value) throws Exception {
        if (attr.equals("src")) {
            getTiles(value);
            return true;
        }
        return super.setAttribute(attr, value);
    }

}
