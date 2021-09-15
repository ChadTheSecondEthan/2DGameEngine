package com.chad.engine.gfx;

import com.chad.engine.utils.Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Spritesheet {
    // sprite sheet size in pixels
    public final int width, height;

    // sprite sheet size in tiles
    public final int tx, ty;

    // sprite sprite size
    public final int size;

    // subimages
    private final BufferedImage[][] sprites;

    public Spritesheet(String path, int size) {
        this.size = size;

        BufferedImage image = Resources.loadImage(path);
        width = image.getWidth();
        height = image.getHeight();

        assert width % size == 0 && height % size == 0;

        tx = width / size;
        ty = height / size;

        sprites = new BufferedImage[tx][ty];

        for (int w = 0; w < width / size; w++)
            for (int h = 0; h < height / size; h++)
                sprites[w][h] = image.getSubimage(w * size, h * size, size, size);
    }

    public BufferedImage getSprite(int index) { return sprites[index / ty][index % tx]; }
}
