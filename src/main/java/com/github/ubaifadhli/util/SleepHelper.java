package com.github.ubaifadhli.util;

public class SleepHelper {
    public static void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}