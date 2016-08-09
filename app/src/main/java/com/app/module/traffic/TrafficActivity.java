package com.app.module.traffic;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityTrafficBinding;
import com.app.databinding.ItemIndexTrafficBinding;
import com.app.module.traffic.entity.NavigationEntity;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class TrafficActivity extends BaseActivity {
    private ActivityTrafficBinding binding;
    private List<NavigationEntity> navigationEntities = new ArrayList<>();
    private TrafficAdapter adapter = new TrafficAdapter(navigationEntities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_traffic);
        setSupportActionBar(binding.toolbar);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(mContext, AddNavigationActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private static class TrafficHolder extends RecyclerView.ViewHolder {
        private ItemIndexTrafficBinding binding;

        public TrafficHolder(ItemIndexTrafficBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class TrafficAdapter extends BaseRecyclerViewAdapter<NavigationEntity, TrafficHolder> {
        public TrafficAdapter(List<NavigationEntity> datas) {
            super(datas);
        }

        @Override
        public TrafficHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            ItemIndexTrafficBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout
                    .item_index_traffic, parent, false);
            return new TrafficHolder(binding);
        }

        @Override
        public void onBindViewHolder(TrafficHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}
