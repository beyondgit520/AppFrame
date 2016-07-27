package com.app.module.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.MainActivity;
import com.app.R;
import com.app.base.APP;
import com.app.base.BaseActivity;

import java.util.Locale;


public class LanguageSetActivity extends BaseActivity {
    private RadioGroup radioGroup;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = getSharedPreferences("language", MODE_PRIVATE);
        radioGroup = (RadioGroup) findViewById(R.id.language_rg);
        if (APP.getSetLocale().equals(Locale.CHINA)) {
            ((AppCompatRadioButton) radioGroup.getChildAt(1)).setChecked(true);
        } else {
            ((AppCompatRadioButton) radioGroup.getChildAt(3)).setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.zh_rb:
                        changAppLanguage(getApplicationContext().getResources(), Locale.CHINESE.getLanguage());
                        Toast.makeText(mContext, Locale.CHINESE.getLanguage(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.en_rb:
                        changAppLanguage(getApplicationContext().getResources(), Locale.ENGLISH.getLanguage());
                        Toast.makeText(mContext, Locale.getDefault().getLanguage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void changAppLanguage(Resources resources, String language) {
        Configuration configuration = resources.getConfiguration();
        if (language.equals(Locale.CHINESE.getLanguage())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(Locale.CHINA);
            } else {
                configuration.locale = Locale.CHINA;
            }
            preferences.edit().putString("language", "china").apply();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(Locale.ENGLISH);
            } else {
                configuration.locale = Locale.ENGLISH;
            }
            preferences.edit().putString("language", "default").apply();
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
