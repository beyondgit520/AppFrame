package com.app.module.stock;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityStockEditBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.stock.entity.StockEntity;
import com.app.module.stock.entity.Stockinfo;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class StockEditActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private List<StockEntity> entities = new ArrayList<>();
    private StockEditAdapter adapter = new StockEditAdapter(entities);
    private ActivityStockEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_edit);
        setSupportActionBar(binding.toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.refreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        getdata(true);
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
                if (isRefresh) entities.clear();
                entities.addAll(stockinfo.getStockinfo());
                adapter.notifyDataSetChanged();
            }
        }, this, "sina,bidu,shi,sohu,nok,aapl,msft,goog,amzn,intc", 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stock_menu, menu);
        menu.getItem(1).setIcon(R.drawable.ic_ok);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        else if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(mContext, StockSearchActivity.class));
        } else if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("ok");
            startActivity(new Intent(mContext, StockAddKeyActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        getdata(true);
    }
}
