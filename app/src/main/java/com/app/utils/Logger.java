package com.app.utils;

import android.util.Log;

import com.app.base.Constant;


/**
 * Created by Administrator on 2016/7/4.
 */
public class Logger {
    public static void d(String tag, String msg) {
        if (Constant.debug)
            Log.d(tag, msg);
    }
}
