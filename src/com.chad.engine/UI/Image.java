package com.chad.engine.UI;

import com.chad.engine.Entity.SpriteRenderer;
import com.chad.engine.Utils.Resources;

import java.awt.image.BufferedImage;

public class Image extends UIElement {

    public Image(BufferedImage image) {
        super();

        drawable = new SpriteRenderer(image);
    }

    public void setImage(BufferedImage image) { ((SpriteRenderer) drawable).setImage(image); }
    public void setImage(String imgPath) { setImage(Resources.loadImage(imgPath)); }

    public BufferedImage getImage() { return ((SpriteRenderer) drawable).getImage(); }

}
