package com.app.module.stock;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityStockSearchBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.stock.entity.StockEntity;
import com.app.module.stock.entity.Stockinfo;

import java.util.ArrayList;
import java.util.List;

public class StockSearchActivity extends BaseActivity {
    private ActivityStockSearchBinding binding;
    private List<StockEntity> stockEntities = new ArrayList<>();
    private StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_search);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(binding.searchEt.getText())) {
                        search(binding.searchEt.getText().toString(), 1);
                        return true;
                    }
                }
                return false;
            }
        });
        adapter = new StockAdapter(stockEntities);
        binding.searchRecyclerview.setHasFixedSize(true);
        binding.searchRecyclerview.setAdapter(adapter);
    }

    public void click(View view) {

    }

    private void search(String ids, int list) {
        HttpMethods.getInstance().searchStock(new MySubscriber<Stockinfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(Stockinfo stockinfo) {
                stockEntities.clear();
                stockEntities.addAll(stockinfo.getStockinfo());
                adapter.notifyDataSetChanged();
            }
        }, this, ids, list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
