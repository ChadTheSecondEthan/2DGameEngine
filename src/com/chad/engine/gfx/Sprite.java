package com.chad.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {
    // sprite size in pixels
    public int width, height;

    // image
    public BufferedImage image;

    public Sprite(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream(path)));
        } catch (IOException e) {
            throw new Error(e);
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
    }
}
