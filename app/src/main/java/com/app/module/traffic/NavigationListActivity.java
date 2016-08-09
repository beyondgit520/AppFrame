package com.app.module.traffic;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityNavigationListBinding;
import com.app.databinding.ItemNavListBinding;
import com.app.module.traffic.entity.NavigationEntity;

import java.util.ArrayList;
import java.util.List;

public class NavigationListActivity extends BaseActivity {
    private ActivityNavigationListBinding binding;
    private List<NavigationEntity> navigationEntities = new ArrayList<>();
    private NavAdapter adapter = new NavAdapter(navigationEntities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_list);
        setSupportActionBar(binding.toolbar);
        binding.searchRecyclerview.setHasFixedSize(true);
        binding.searchRecyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_upload_mirror);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_settings) {
//            // TODO: 16-8-6 提交事件
//            MUtils.toast("mirror");
//        } else {
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private static class NavHolder extends RecyclerView.ViewHolder {
        private ItemNavListBinding binding;

        public NavHolder(ItemNavListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class NavAdapter extends BaseRecyclerViewAdapter<NavigationEntity, NavHolder> {
        private int mSelectedItem = -1;

        public NavAdapter(List<NavigationEntity> datas) {
            super(datas);
        }

        @Override
        public NavHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            ItemNavListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout
                    .item_nav_list, parent, false);
            return new NavHolder(binding);
        }

        @Override
        public void onBindViewHolder(NavHolder holder, final int position) {
            holder.binding.radioButton.setChecked(position == mSelectedItem);
            holder.binding.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = position;
                    notifyDataSetChanged();
                }
            });
            holder.binding.deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder buider = new AlertDialog.Builder(mContext);
                    buider.setMessage("确定删除");
                    buider.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    buider.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            datas.remove(position);
//                            adapter.notifyItemRemoved(position);
//                            adapter.notifyItemRangeChanged(0, datas.size());
                            dialogInterface.dismiss();
                        }
                    });
                    buider.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }
}
