package com.app.module.calendar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityCalendarBinding;
import com.app.databinding.ItemCalendarBinding;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityCalendarBinding binding;
    private CalendarAdapter adapter = new CalendarAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        binding.refreshLayout.setOnRefreshListener(this);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stock_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
            return true;
        } else if (item.getItemId() == R.id.action_add) {
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override public void onRefresh() {
        binding.refreshLayout.setRefreshing(false);
    }

    private static class CalendarHolder extends RecyclerView.ViewHolder {
        private ItemCalendarBinding binding;

        public CalendarHolder(ItemCalendarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class CalendarAdapter extends BaseRecyclerViewAdapter<Object, CalendarHolder> {
        public CalendarAdapter(List<Object> datas) {
            super(datas);
        }

        @Override public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            ItemCalendarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_calendar, parent, false);
            return new CalendarHolder(binding);
        }

        @Override public void onBindViewHolder(CalendarHolder holder, int position) {

        }

        @Override public int getItemCount() {
            return 10;
        }
    }
}
