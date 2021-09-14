package com.chad.engine.entity;

import com.chad.engine.Game;
import com.chad.engine.Window;

import java.awt.image.BufferedImage;

public class Mask extends Entity {

    public Mask() {
        super();

        drawable = new SpriteRenderer();
    }

    @Override
    public void update(float dt) {
        ((SpriteRenderer) drawable).setImage(Window.getFrameImage().getSubimage((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight()));
    }
}
