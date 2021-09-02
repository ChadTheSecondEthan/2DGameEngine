package com.chad.engine;

import com.chad.engine.utils.Time;

import java.util.Random;

public class Global {

    public static Random random = new Random(Time.now());
    public static long frames = 0;

}
