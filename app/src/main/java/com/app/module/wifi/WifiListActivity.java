package com.app.module.wifi;

import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.utils.Logger;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends BaseActivity {
    private List<ScanResult> results = new ArrayList<>();
    private WifiAdmin wifiAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wifiAdmin = new WifiAdmin(mContext);
        wifiAdmin.openWifi();
        wifiAdmin.startScan();
        results = wifiAdmin.getWifiList();
        Logger.d(tag, wifiAdmin.lookUpScan().toString());
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0xf) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MUtils.toast("允许");
            } else {
                MUtils.toast("拒绝");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
