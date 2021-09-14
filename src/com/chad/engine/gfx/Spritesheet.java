package com.chad.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Spritesheet {
    // sprite sheet size in pixels
    public int width, height;

    // sprite sprite size
    public int size;

    // subimages
    private BufferedImage[][] sprites;

    public Spritesheet(String path, int size) {
        this.size = size;

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Spritesheet.class.getResourceAsStream(path)));
            width = image.getWidth();
            height = image.getHeight();

            sprites = new BufferedImage[width][height];

            for (int w = 0; w < width / size; w++)
                for (int h = 0; h < height / size; h++)
                    sprites[w][h] = image.getSubimage(w * size, h * size, size, size);

        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public BufferedImage getSprite(int index) { return sprites[index / size][index % size]; }
}
