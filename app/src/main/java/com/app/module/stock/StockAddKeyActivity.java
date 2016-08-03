package com.app.module.stock;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.app.R;
import com.app.base.BaseActivity;

public class StockAddKeyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_add_key);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
