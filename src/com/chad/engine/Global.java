package com.chad.engine;

import com.chad.engine.utils.Time;

import java.util.Random;

public class Global {

    public static Random random = new Random(Time.now());
    public static long frames = 0;
    public static int fps = 60;
    public static float dt = 0;

    public static Time totalTime = new Time();
    public static Time stateTime = new Time();

    public static float totalTime() { return totalTime.getElapsed(); }
    public static float stateTime() { return stateTime.getElapsed(); }
}
