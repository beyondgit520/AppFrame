package com.app.module.stock;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ItemStockEditBinding;
import com.app.module.stock.entity.StockEntity;

import java.util.List;

/**
 * Created by 李 on 16-8-3.
 */
public class StockEditAdapter extends BaseRecyclerViewAdapter<StockEntity, StockEditAdapter.EditHolder> {

    public StockEditAdapter(List<StockEntity> datas) {
        super(datas);
    }

    @Override
    public EditHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        ItemStockEditBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout
                .item_stock_edit, parent, false);
        EditHolder holder = new EditHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override
    public void onBindViewHolder(EditHolder holder, int position) {
        holder.binding.setEntity(datas.get(position));
        holder.binding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setMessage("delete");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public static class EditHolder extends RecyclerView.ViewHolder {
        public ItemStockEditBinding binding;

        public EditHolder(View itemView) {
            super(itemView);
        }
    }
}
