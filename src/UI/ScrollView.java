package UI;

import Entity.*;
import Main.Game;
import Utils.Input;
import Utils.Mathf;

import java.awt.*;

public class ScrollView extends UIElement {

    // TODO get masking to work

    private float progress;
    private float maxProgress;
    private float minProgress;
    private float scrollSensitivity;

    private Color maskColor;
    private Mask topMask, bottomMask;

    public ScrollView() {
        super();

        progress = 0;
        minProgress = 0;
        maxProgress = height;
        scrollSensitivity = 10;

        maskColor = Color.white;

        topMask = new Mask();
        bottomMask = new Mask();

        addSpawnListener(topMask::spawn);
        addSpawnListener(bottomMask::spawn);

        addDespawnListener(topMask::despawn);
        addDespawnListener(bottomMask::despawn);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);

        topMask.setBounds(getAbsoluteX(), 0, width, getAbsoluteY());

        int bottom = (int) (getAbsoluteY() + height);
        bottomMask.setBounds(getAbsoluteX(), bottom, width, Game.instance().getWindowSize().height - bottom);
    }

    @Override
    public void addChild(Entity child) {
        super.addChild(child);
        child.setzIndex(getzIndex() + 1);
    }

    @Override
    public void setzIndex(int zIndex) {
        super.setzIndex(zIndex);
        topMask.setzIndex(zIndex + 2);
        bottomMask.setzIndex(zIndex + 2);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        float oldProgress = progress;
        progress = Mathf.clamp(minProgress, maxProgress, progress + Input.scrollAmount() * scrollSensitivity);

        float dProgress = progress - oldProgress;
        for (Entity child : getChildrenList())
            child.setRelativeY(child.getRelativeY() - dProgress);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    public void setScrollSensitivity(float s) { scrollSensitivity = s; }
    public float getScrollSensitivity() { return scrollSensitivity; }

    public void setMaskColor(Color color) { this.maskColor = color; }
    public Color getMaskColor() { return maskColor; }

    public float getProgress() { return progress; }
    public void setProgress(float progress) { this.progress = progress; }

    public float getMaxProgress() { return maxProgress; }
    public void setMaxProgress(float maxProgress) {
        this.maxProgress = Math.max(maxProgress, 0);
    }

    public float getMinProgress() { return minProgress; }
    public void setMinProgress(float minProgress) {
        this.minProgress = Math.min(minProgress, 0);
    }

}
