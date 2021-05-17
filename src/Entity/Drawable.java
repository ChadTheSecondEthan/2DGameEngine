package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Drawable {
    void draw(Entity e, Graphics g);
    BufferedImage getImage();
}
