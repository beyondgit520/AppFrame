package com.app.module.news;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityWeatherBinding;

public class WeatherActivity extends BaseActivity {
    private ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] datas = new String[]{"callie", "dsfsdf"};
        binding.citySpinner.setAdapter(new ArrayAdapter<>(mContext, R.layout.support_simple_spinner_dropdown_item, datas));
        binding.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stock_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(mContext, AddCityActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_mirror) {
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
