package com.app.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;

import com.app.R;
import com.app.module.login.entity.LoginInfo;
import com.app.module.login.entity.UserInfo;
import com.app.utils.Logger;
import com.app.utils.MUtils;
import com.app.utils.SPUtils;
import com.app.utils.UILImageLoader;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Administrator on 2016/7/6.
 */
public class APP extends Application {
    private static Context context;
    private static LoginInfo mloginInfo;
    private List<Activity> activities = new ArrayList<>();

    public static LoginInfo getLoginInfo() {
        return mloginInfo;
    }

    public static void setLoginInfo(LoginInfo loginInfo) {
        mloginInfo = loginInfo;
    }

    public static UserInfo getUserInfo() {
        return mloginInfo.extension.memberInfo;
    }

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
        Logger.d("Application", "onCreate:" + (mloginInfo == null ? "null" : mloginInfo.memberId));
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
        initGalleryFinal();
        String loginInfo = (String) SPUtils.get(context, "loginInfo", "");
        if (!TextUtils.isEmpty(loginInfo)) {
            setLoginInfo(new Gson().fromJson(loginInfo, LoginInfo.class));
        }
    }

    private void initGalleryFinal() {//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.black))
                .setIconBack(R.drawable.ic_navigation)
                .setFabNornalColor(getResources().getColor(R.color.black))
                .setFabPressedColor(getResources().getColor(R.color.black_p))
                .build();

        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableCrop(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();
        //配置imageloader
        cn.finalteam.galleryfinal.ImageLoader imageloader = new UILImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(context, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
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

    @Override public void onTerminate() {
        super.onTerminate();
        Logger.d("Application", "onTerminate");
    }
}
