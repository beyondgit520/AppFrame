package com.app.module.setting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.app.R;
import com.app.base.APP;
import com.app.base.BaseActivity;
import com.app.databinding.ActivitySettingBinding;
import com.app.utils.MUtils;
import com.app.utils.SPUtils;

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.newsSwitch.setOnCheckedChangeListener(this);
        binding.stockSwitch.setOnCheckedChangeListener(this);
        binding.trafficSwitch.setOnCheckedChangeListener(this);
        binding.calendarSwitch.setOnCheckedChangeListener(this);
        binding.remindSwitch.setOnCheckedChangeListener(this);
        binding.meSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void logOut(View view) {
        MUtils.showSBar(view, "logout");
        SPUtils.put(APP.getContext(), "loginInfo", "");
        APP.setLoginInfo(null);
    }

    public void languageSet(View view) {
        startActivity(new Intent(mContext, LanguageSetActivity.class));
    }

    public void about(View view) {
        startActivity(new Intent(mContext, AboutAppActivity.class));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.news_switch: {
                break;
            }
            case R.id.stock_switch: {
                break;
            }
            case R.id.traffic_switch: {
                break;
            }
            case R.id.calendar_switch: {
                break;
            }
            case R.id.remind_switch: {
                break;
            }
            case R.id.me_switch: {
                break;
            }
        }
    }
}
