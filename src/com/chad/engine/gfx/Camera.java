package com.chad.engine.gfx;

import com.chad.engine.utils.Mathf;

public class Camera {
    // translation
    public int tx = 0, ty = 0;

    public void centerOn(int x, int y, int minX, int minY, int maxX, int maxY) {
        this.tx = Mathf.clamp(x - (Renderer.WIDTH / 2), minX, maxX - Renderer.WIDTH);
        this.ty = Mathf.clamp(y - (Renderer.HEIGHT / 2), minY, maxY - Renderer.HEIGHT);
    }
}
