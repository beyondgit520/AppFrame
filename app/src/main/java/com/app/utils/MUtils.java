package com.app.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MUtils {
    private static Context mApplicationContent;

    public static void initialize(Application app) {
        mApplicationContent = app.getApplicationContext();
    }

    /**
     * dp转px
     */
    public static int dip2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, mApplicationContent.getResources().getDisplayMetrics());
    }

    /**
     * 取屏幕宽度
     * @return
     */
    public static int getScreenWidth(){
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 取屏幕高度
     * @return
     */
    public static int getScreenHeight(){
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.heightPixels-getStatusBarHeight();
    }

    /**
     * 取屏幕高度包含状态栏高度
     * @return
     */
    public static int getScreenHeightWithStatusBar(){
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 取导航栏高度
     * @return
     */
    public static int getNavigationBarHeight() {
        int result = 0;
        int resourceId = mApplicationContent.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mApplicationContent.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 取状态栏高度
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = mApplicationContent.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mApplicationContent.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight() {
        int actionBarHeight = 0;

        final TypedValue tv = new TypedValue();
        if (mApplicationContent.getTheme()
                .resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, mApplicationContent.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }


    /**
     * 关闭输入法
     * @param act
     */
    public static void closeInputMethod(Activity act){
        View view = act.getCurrentFocus();
        if(view!=null){
            ((InputMethodManager)mApplicationContent.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 判断应用是否处于后台状态
     * @return
     */
    public static boolean isBackground() {
        ActivityManager am = (ActivityManager) mApplicationContent.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(mApplicationContent.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否有网络
     * @return
     */
    public static boolean isNetWorkAvilable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mApplicationContent
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 取APP版本号
     * @return
     */
    public static int getAppVersionCode(){
        try {
            PackageManager mPackageManager = mApplicationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplicationContent.getPackageName(),0);
            return _info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * 取APP版本名
     * @return
     */
    public static String getAppVersionName(){
        try {
            PackageManager mPackageManager = mApplicationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplicationContent.getPackageName(),0);
            return _info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
