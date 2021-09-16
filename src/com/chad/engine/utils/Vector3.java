package com.chad.engine.utils;

public class Vector3 {

    public float x, y, z;

    public Vector3() {
        x = y = 0;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 one() {
        return new Vector3(1f, 1f, 1f);
    }

}
