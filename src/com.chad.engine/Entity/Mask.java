package com.chad.engine.Entity;

import com.chad.engine.Main.Game;

import java.awt.image.BufferedImage;

public class Mask extends Entity {

    private BufferedImage frameImage;

    public Mask() {
        super();

        drawable = new SpriteRenderer();

        frameImage = Game.instance().getGameLoop().getFrameImage();
    }

    @Override
    public void update(float dt) {
        ((SpriteRenderer) drawable).setImage(frameImage.getSubimage((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight()));
    }
}
