package com.app.module.me;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityMeBinding;

public class MeActivity extends BaseActivity {
    private ActivityMeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_me);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}
