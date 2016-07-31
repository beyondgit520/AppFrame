package com.app.module.news;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ItemNewsBinding;
import com.app.module.news.entity.NewsEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NewsAdapter extends BaseRecyclerViewAdapter<NewsEntity, NewsAdapter.NewsHolder> {
    private NewsEvent event;

    public NewsAdapter(List<NewsEntity> datas) {
        super(datas);
    }

    public void setEvent(NewsEvent event) {
        this.event = event;
    }

    @Override public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        ItemNewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_news, parent, false);
        NewsHolder holder = new NewsHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override public void onBindViewHolder(NewsHolder holder, int position) {
        holder.binding.setNews(datas.get(position));
        if (event != null) holder.binding.setEvent(event);
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        public ItemNewsBinding binding;

        public NewsHolder(View itemView) {
            super(itemView);
        }
    }
}
