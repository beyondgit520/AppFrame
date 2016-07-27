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
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.app.base.APP;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/23.
 */
public class MUtils {
    private static Context mApplicationContent;

    public static void initialize(Application app) {
        mApplicationContent = app.getApplicationContext();
    }

    public static void toast(@NonNull String text) {
        Toast.makeText(mApplicationContent, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(@StringRes int resId) {
        toast(mApplicationContent.getString(resId));
    }

    private static Snackbar showSnackBar(@NonNull View view, @NonNull CharSequence text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
/*        LinearLayout snackbarView = (LinearLayout) snackbar.getView();//获取SnackBar布局View实例
        TextView textView = ((TextView) snackbarView.findViewById(R.id.snackbar_text));//获取文本View实例
        Button button = (Button) snackbarView.findViewById(R.id.snackbar_action);//获取按钮View实例
        button.setTextColor(Color.parseColor("#FF4081"));
        snackbarView.setBackgroundColor(Color.parseColor("#3F51B5"));//更改背景颜色
        textView.setTextColor(Color.WHITE);//更改文本颜色*/
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showSBar(@NonNull View view, @NonNull CharSequence text, int duration) {
        return showSnackBar(view, text, duration);
    }

    public static Snackbar showSBar(@NonNull View view, @StringRes int resId, int duration) {
        return showSnackBar(view, view.getResources().getString(resId), duration);
    }

    public static Snackbar showSBar(@NonNull View view, @StringRes int resId) {
        return showSnackBar(view, view.getResources().getString(resId), Snackbar.LENGTH_SHORT);
    }

    public static Snackbar showSBar(@NonNull View view, @NonNull CharSequence text) {
        return showSnackBar(view, text, Snackbar.LENGTH_SHORT);
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
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.heightPixels - getStatusBarHeight();
    }

    /**
     * 取屏幕高度包含状态栏高度
     *
     * @return
     */
    public static int getScreenHeightWithStatusBar() {
        DisplayMetrics dm = mApplicationContent.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 取导航栏高度
     *
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
     *
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
     *
     * @param act
     */
    public static void closeInputMethod(Activity act) {
        View view = act.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) mApplicationContent.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 判断应用是否处于后台状态
     *
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
     *
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
     *
     * @return
     */
    public static int getAppVersionCode() {
        try {
            PackageManager mPackageManager = mApplicationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplicationContent.getPackageName(), 0);
            return _info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /**
     * 取APP版本名
     *
     * @return
     */
    public static String getAppVersionName() {
        try {
            PackageManager mPackageManager = mApplicationContent.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(mApplicationContent.getPackageName(), 0);
            return _info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 是否是设置值
     *
     * @return 是否是设置值
     */
    public static boolean isSetValue() {
        Locale currentLocale = APP.getContext().getResources().getConfiguration().locale;
        return currentLocale.equals(APP.getSetLocale());
    }
}
