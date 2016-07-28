package com.app.module.wifi;

import android.databinding.DataBindingUtil;
import android.net.wifi.ScanResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ItemWifiBinding;

import java.util.List;

/**
 * Created by 李 on 16-7-28.
 */
public class WifiAdapter extends BaseRecyclerViewAdapter<ScanResult, WifiAdapter.WifiHolder> {

    public WifiAdapter(List<ScanResult> datas) {
        super(datas);
    }

    @Override
    public WifiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        super.onCreateViewHolder(parent, viewType);
        ItemWifiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout
                .item_wifi, parent, false);
        WifiHolder holder = new WifiHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override
    public void onBindViewHolder(WifiHolder holder, int position) {
        holder.binding.setResult(datas.get(position));
        holder.binding.setEvent((WifiEvent) mContext);
    }

    public static class WifiHolder extends RecyclerView.ViewHolder {
        public ItemWifiBinding binding;

        public WifiHolder(View itemView) {
            super(itemView);
        }
    }
}
