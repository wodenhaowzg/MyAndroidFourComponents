package com.example.myandroidfourcomponents.utils;

import android.util.Log;

import java.text.SimpleDateFormat;

public class MyLog {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void error(String tag, String msg) {
        try {
            throw new Exception(tag + " -> " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
