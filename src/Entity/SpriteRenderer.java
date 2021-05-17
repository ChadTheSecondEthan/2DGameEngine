package Entity;

import Utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer implements Drawable {

    private BufferedImage image;

    public SpriteRenderer(BufferedImage image) {
        this.image = image;
    }

    public SpriteRenderer(String imgPath) {
        image = Resources.loadImage(imgPath);
    }

    public void draw(Entity e, Graphics g) {
        g.drawImage(image, (int) e.x, (int) e.y, (int) e.width, (int) e.height, null);
    }

    public BufferedImage getImage() { return image; }
}
