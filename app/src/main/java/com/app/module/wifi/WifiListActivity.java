package com.app.module.wifi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityWifiListBinding;
import com.app.utils.MUtils;
import com.app.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends BaseActivity implements WifiEvent {
    private List<ScanResult> results = new ArrayList<>();
    private WifiAdmin wifiAdmin;
    private WifiAdapter adapter;
    private ActivityWifiListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wifi_list);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wifiAdmin = new WifiAdmin(mContext);
        wifiAdmin.openWifi();
        wifiAdmin.startScan();
        results.clear();
        results.addAll(wifiAdmin.getWifiList());
        adapter = new WifiAdapter(results);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_edit);
        menu.getItem(0).setTitle("");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(mContext, BindWifiActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, ScanResult result) {
        Intent intent = new Intent(mContext, BindWifiActivity.class);
        intent.putExtra("ScanResult", result);
        startActivity(intent);
    }
}
