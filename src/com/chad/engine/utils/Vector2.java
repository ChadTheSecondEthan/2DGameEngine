package com.chad.engine.utils;

public class Vector2 {

    public float x, y;

    public Vector2() {
        x = y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 one() {
        return new Vector2(1f, 1f);
    }

}
