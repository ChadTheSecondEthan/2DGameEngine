package com.chad.engine.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation implements Drawable{

    private BufferedImage[] sprites;
    private short delay;
    private short timePassed;
    private byte currentFrame;
    private boolean playedOnce;
    private boolean loop;

    public Animation(BufferedImage[] sprites) {
        this.sprites = sprites;

        timePassed = 0;
        currentFrame = 0;
        playedOnce = false;

        // default delay is 12 fps
        delay = 83;

        // looping is set to true by default
        loop = true;
    }

    /** update the current frame of the animation */
    public void update(float dt) {
        timePassed += (int) (dt * 1000);
        if (timePassed > delay) {
            timePassed -= delay;
            currentFrame++;
            if (currentFrame == sprites.length) {
                if (loop) currentFrame = 0;
                playedOnce = true;
            }
        }
    }

    /** draws the current image onto the graphics */
    public void draw(Entity e, Graphics g) {
        g.drawImage(sprites[currentFrame], (int) e.getX(), (int) e.getY(), (int) e.width, (int) e.height, null);
    }

    /** set the current sprites and resets the current frame */
    public void setSprites(BufferedImage[] sprites) { this.sprites = sprites; currentFrame = 0; }

    /** sets the current frame */
    public void setCurrentFrame(byte currentFrame) { this.currentFrame = currentFrame; }

    /** set whether or not the animation will loop */
    public void setLooping(boolean loop) { this.loop = loop; }

    /** set the frame delay of the animation */
    public void setDelay(short millis) { delay = millis; }

    /** set the frame rate of the animation */
    public void setFPS(short fps) { delay = (short) (1000 / fps); }

    /** returns the current image of the animation */
    public BufferedImage getImage() { return sprites[currentFrame]; }

    /** returns whether or not this animation has been played once */
    public boolean playedOnce() { return playedOnce; }

}
