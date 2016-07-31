package com.app.module.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ItemMainAttentionBinding;
import com.app.module.news.NewsEvent;
import com.app.module.news.entity.NewsEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
public class AttentionAdapter extends BaseRecyclerViewAdapter<NewsEntity, AttentionAdapter.AttentionHolder> {
    private NewsEvent event;

    public AttentionAdapter(List<NewsEntity> datas) {
        super(datas);
    }

    public void setEvent(NewsEvent event) {
        this.event = event;
    }

    @Override public AttentionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        ItemMainAttentionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_main_attention, parent, false);
        AttentionHolder holder = new AttentionHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override public void onBindViewHolder(AttentionHolder holder, int position) {
        holder.binding.setNews(datas.get(position));
        if (event != null) holder.binding.setEvent(event);
    }

    public static class AttentionHolder extends RecyclerView.ViewHolder {
        public ItemMainAttentionBinding binding;

        public AttentionHolder(View itemView) {
            super(itemView);
        }
    }
}
