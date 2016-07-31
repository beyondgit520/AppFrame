package com.app.module.news;

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
import com.app.base.WebActivity;
import com.app.databinding.ActivityNewsBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.news.entity.NewsEntity;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, NewsEvent {
    private NewsAdapter adapter;
    private List<NewsEntity> datas = new ArrayList<>();
    private ActivityNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new NewsAdapter(datas);
        adapter.setEvent(this);
        binding.refreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        getData(true);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!binding.refreshLayout.isRefreshing() && newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).findLastVisibleItemPosition() == datas.size() - 1) {
                    getData(false);
                }

            }
        });
    }

    private void getData(final boolean isRefresh) {
        binding.refreshLayout.setRefreshing(true);
        HttpMethods.getInstance().getNewsList(new MySubscriber<List<NewsEntity>>() {
            @Override public void onCompleted() {
                binding.refreshLayout.setRefreshing(false);
            }

            @Override public void onError(Throwable e) {
                super.onError(e);
                binding.refreshLayout.setRefreshing(false);
            }

            @Override public void onNext(List<NewsEntity> newsEntities) {
                if (isRefresh) datas.clear();
                datas.addAll(newsEntities);
                adapter.notifyDataSetChanged();
            }
        }, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(mContext, AddKeyActivity.class));
        } else if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onRefresh() {
        getData(true);
    }

    @Override public void onItemClick(NewsEntity entity) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", entity.getUrl());
        intent.putExtra("title", entity.getTitle());
        startActivity(intent);
    }
}
