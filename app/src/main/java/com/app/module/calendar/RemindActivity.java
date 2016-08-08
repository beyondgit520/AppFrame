package com.app.module.calendar;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityRemindBinding;
import com.app.databinding.ItemRemindBinding;
import com.app.module.calendar.entitiy.RemindEntity;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class RemindActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityRemindBinding binding;
    private List<RemindEntity> remindEntities = new ArrayList<>();
    private RemindAdapter adapter = new RemindAdapter(remindEntities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_remind);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.refreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(mContext, AllRemindActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override public void onRefresh() {
        binding.refreshLayout.setRefreshing(false);
    }

    private class RemindAdapter extends BaseRecyclerViewAdapter<RemindEntity, RecyclerView.ViewHolder> {

        public RemindAdapter(List<RemindEntity> datas) {
            super(datas);
        }

        @Override public int getItemViewType(int position) {
            if (position == 10) {
                return -1;
            }
            return super.getItemViewType(position);
        }

        @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            RecyclerView.ViewHolder holder;
            if (viewType == -1) {
                ImageView imageView = new ImageView(mContext);
                imageView.setImageResource(R.drawable.ic_add);
                int padding = MUtils.dip2px(getResources().getDimension(R.dimen.small_padding));
                imageView.setPadding(padding, padding, padding, padding);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        startActivity(new Intent(mContext, AddRemindActivity.class));
                    }
                });
                holder = new FooterHolder(imageView);
            } else {
                ItemRemindBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_remind, parent, false);
                holder = new RemindHolder(binding);
            }
            return holder;
        }

        @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) == -1) {

            } else {

            }
        }

        @Override public int getItemCount() {
            return 10 + 1;
        }
    }

    private class RemindHolder extends RecyclerView.ViewHolder {
        private ItemRemindBinding binding;

        public RemindHolder(ItemRemindBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

}
