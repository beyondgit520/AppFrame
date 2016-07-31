package com.app.module.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.app.R;
import com.app.base.BaseActivity;

public class EditHeaderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
