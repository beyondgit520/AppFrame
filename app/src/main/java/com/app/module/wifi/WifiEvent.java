package com.app.module.wifi;

import android.net.wifi.ScanResult;
import android.view.View;

/**
 * Created by 李 on 16-7-28.
 */
public interface WifiEvent {
    void onItemClick(View view, ScanResult result);
}
