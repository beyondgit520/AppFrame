package com.app.base;

import android.app.Application;
import android.content.Context;

import com.app.utils.MUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/7/6.
 */
public class APP extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        Fresco.initialize(this);
        LeakCanary.install(this);
        MUtils.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public static Context getContext() {
        return context;
    }
}
