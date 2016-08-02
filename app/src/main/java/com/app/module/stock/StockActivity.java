package com.app.module.stock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityStockBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.stock.entity.StockEntity;
import com.app.module.stock.entity.Stockinfo;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityStockBinding binding;
    private List<StockEntity> stockEntities = new ArrayList<>();
    private StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.refreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setHasFixedSize(true);
        adapter = new StockAdapter(stockEntities);
        binding.recyclerView.setAdapter(adapter);
        getdata(true);
        binding.header.post(new Runnable() {
            @Override public void run() {
                binding.recyclerView.setPadding(MUtils.dip2px(16), binding.header.getHeight(), MUtils.dip2px(16), MUtils.dip2px(16));
            }
        });
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                float currentY = binding.header.getTranslationY();
                float result = currentY - dy;
                result = result > 0 ? 0 : result;
                result = result <= -binding.header.getHeight() ? -binding.header.getHeight() : result;
                if (dy > 0 || ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).findFirstVisibleItemPosition() == 0) {
                    binding.header.setTranslationY(result);
                }
            }
        });
    }

    private void getdata(final boolean isRefresh) {
        binding.refreshLayout.setRefreshing(true);
        HttpMethods.getInstance().searchStock(new MySubscriber<Stockinfo>() {
            @Override
            public void onCompleted() {
                binding.refreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                binding.refreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(Stockinfo stockinfo) {
                if (isRefresh) stockEntities.clear();
                stockEntities.addAll(stockinfo.getStockinfo());
                adapter.notifyDataSetChanged();
            }
        }, this, "sina,bidu,shi,sohu,nok,aapl,msft,goog,amzn,intc", 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stock_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        else if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(mContext, StockSearchActivity.class));
        } else if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        getdata(true);
    }
}
