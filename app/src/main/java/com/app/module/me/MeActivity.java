package com.app.module.me;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityMeBinding;
import com.app.module.login.LoginActivity;
import com.app.module.login.MultiActivity;
import com.app.module.me.entity.User;
import com.app.module.me.event.MeEvent;
import com.app.module.wifi.WifiListActivity;

public class MeActivity extends BaseActivity implements MeEvent {
    private ActivityMeBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_me);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user = new User("beyond", "", 1, "sdds@qq.com", "123", 0, 1, 0, 1);
        binding.setUser(user);
        binding.setEvent(this);
        user.setMobilePhone("12478996789");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickAvatar() {
        startActivity(new Intent(mContext, EditHeaderActivity.class));
    }

    @Override
    public void clickNickName() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.SETTING_NICKNAME);
        startActivity(intent);
    }

    @Override
    public void clickGender() {
        Intent intent = new Intent(mContext, EditGenderActivity.class);
        intent.putExtra("gender", user.getGender().intValue());
        startActivity(intent);
    }

    @Override
    public void clickEmail() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.SETTING_EMAIL);
        startActivity(intent);
    }

    @Override
    public void clickMobile() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.SETTING_NUMBER);
        startActivity(intent);
    }

    @Override
    public void clickFacebook(int type) {
        startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    public void clickGoogle(int type) {
        startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    public void clickMirror(int type) {
        startActivity(new Intent(mContext, SetMirrorActivity.class));
    }

    @Override
    public void clickWifi(int type) {
        startActivity(new Intent(mContext, WifiListActivity.class));
    }

    @Override
    public void modifiPwd() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.CHANGE_PWD_1);
        startActivity(intent);
    }
}
