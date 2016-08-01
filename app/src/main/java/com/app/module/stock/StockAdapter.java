package com.app.module.stock;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ItemStockBinding;
import com.app.module.stock.entity.StockEntity;
import com.app.module.stock.event.StockListEvent;

import java.util.List;

/**
 * Created by 李 on 16-8-1.
 */
public class StockAdapter extends BaseRecyclerViewAdapter<StockEntity, StockAdapter.StockHolder> {

    private StockListEvent event;

    public StockAdapter(List<StockEntity> datas) {
        super(datas);
    }

    @Override
    public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        ItemStockBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_stock, parent,
                false);
        StockHolder holder = new StockHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override
    public void onBindViewHolder(StockHolder holder, int position) {
        holder.binding.setEntity(datas.get(position));
        if (event != null) {
            holder.binding.setEvent(event);
        }
    }

    public static class StockHolder extends RecyclerView.ViewHolder {
        public ItemStockBinding binding;

        public StockHolder(View itemView) {
            super(itemView);
        }
    }
}
