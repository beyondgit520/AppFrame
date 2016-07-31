package com.app.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import com.app.utils.MUtils;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/6.
 */
public class APP extends Application {
    private static Context context;
    private List<Activity> activities = new ArrayList<>();

    public static Context getContext() {
        return context;
    }

    // 得到设置的语言信息
    public static Locale getSetLocale() {
        // 读取储存的语言设置信息
        SharedPreferences preferences = context.getSharedPreferences("language", MODE_PRIVATE);
        switch (preferences.getString("language", "default")) {
            case "china":
                return Locale.CHINA;
            default:
                return Locale.ENGLISH;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        Fresco.initialize(this);
        LeakCanary.install(this);
        MUtils.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Configuration config = getApplicationContext().getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(getSetLocale());
        } else {
            config.locale = getSetLocale();
        }
        getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileCount(50)
                .memoryCacheSize(5 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) activities.remove(activity);
    }
}
