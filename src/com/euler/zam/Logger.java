package com.euler.zam;

public class Logger {
    public static void log(String message) {
        System.out.println(message);
    }

    public static void log(int value) {
        Logger.log((value + ""));
    }
}
