package com.chad.engine.utils;

public class Control {
    private final int[] keys;

    public Control(int... keys) { this.keys = keys; }

    public boolean down() {
        for (int key : keys)
            if (Keyboard.keys[key].down)
                return true;
        return false;
    }

    public boolean pressed() {
        for (int key : keys)
            if (Keyboard.keys[key].pressed)
                return true;
        return false;
    }
}