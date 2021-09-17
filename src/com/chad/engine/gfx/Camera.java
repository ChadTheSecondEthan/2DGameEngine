package com.chad.engine.gfx;

import com.chad.engine.utils.Vector2;

public class Camera {

    public float x, y;

    public float screenToWorldX(float screenX) { return screenX + x; }
    public float screenToWorldY(float screenY) { return screenY + y; }
    public float worldToScreenX(float worldX) { return worldX - x; }
    public float worldToScreenY(float worldY) { return worldY - y; }

    public Vector2 screenToWorld(float screenX, float screenY) {
        return new Vector2(screenX + x, screenY + y);
    }

    public Vector2 worldToScreen(float worldX, float worldY) {
        return new Vector2(worldX - x, worldY - y);
    }

}
