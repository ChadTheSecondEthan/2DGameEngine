package com.chad.engine.gfx;

import com.chad.engine.utils.Vector2;

import java.awt.Point;

public class Camera {

    public float x, y;

    public float screenToWorldX(float screenX) { return screenX + x; }
    public float screenToWorldY(float screenY) { return screenY + y; }
    public int worldToScreenX(float worldX) { return (int) (worldX - x); }
    public int worldToScreenY(float worldY) { return (int) (worldY - y); }

    public Vector2 screenToWorld(float screenX, float screenY) {
        return new Vector2(screenX + x, screenY + y);
    }

    public Point worldToScreen(float worldX, float worldY) {
        return new Point((int) (worldX - x), (int) (worldY - y));
    }

}
