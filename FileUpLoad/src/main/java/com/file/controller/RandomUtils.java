package com.file.controller;

import java.util.Random;

public class RandomUtils {
    public static int nextInt(int num){
        Random random = new Random();
        int result = random.nextInt(num);
        return result;
    }
}


