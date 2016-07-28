package com.app.module.wifi;

import android.databinding.DataBindingUtil;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityBindWifiBinding;
import com.app.utils.MUtils;

public class BindWifiActivity extends BaseActivity {
    private ScanResult result;
    private ActivityBindWifiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_wifi);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Parcelable parcelable = getIntent().getParcelableExtra("ScanResult");
        if (parcelable != null) {
            result = (ScanResult) parcelable;
            binding.wifiNameTil.getEditText().setText(result.SSID);
            binding.wifiPwdTil.getEditText().requestFocus();
        }
    }

    public void binding(View view) {
        String ssid = binding.wifiNameTil.getEditText().getText().toString();
        String pwd = binding.wifiPwdTil.getEditText().getText().toString();
        MUtils.toast(ssid + ":" + pwd);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
