package com.app.module.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.utils.MUtils;

public class EditGenderActivity extends BaseActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gender);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup = (RadioGroup) findViewById(R.id.gender_rg);
        radioGroup.check(getIntent().getIntExtra("gender", 0) == 0 ? R.id.male_rb : R.id.female_rb);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male_rb) {

                } else {

                }
            }
        });
    }

    public void submit(View view) {
        MUtils.toast(radioGroup.getCheckedRadioButtonId() == R.id.male_rb ? "male" : "female");
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
