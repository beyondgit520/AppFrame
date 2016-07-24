package com.app.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/7/3.
 */
public class BaseActivity extends AppCompatActivity {
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected Context mContext;
    protected String tag;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        tag = this.getClass().getSimpleName();
        progressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
