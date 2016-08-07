package com.app.module.calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityAllRemindBinding;
import com.app.databinding.ItemAllRemindBinding;
import com.app.module.calendar.entitiy.RemindEntity;
import com.app.module.calendar.viewmodel.AllRemindViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllRemindActivity extends BaseActivity implements AllRemindViewModel.onDeleteLinstener {
    private ActivityAllRemindBinding binding;
    private List<RemindEntity> remindEntities = new ArrayList<>();
    private AllRemindAdapter adapter = new AllRemindAdapter(remindEntities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_remind);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        for (int i = 0; i < 10; i++) {
            RemindEntity e = new RemindEntity(i, "title" + i, 1420041600l, "content" + i);
            remindEntities.add(e);
        }
        binding.searchRecyclerview.setHasFixedSize(true);
        binding.searchRecyclerview.setAdapter(adapter);
        binding.searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(binding.searchEt.getText())) {
//                        search(binding.searchEt.getText().toString(), 1);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_add);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(mContext, AddRemindActivity.class));
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override public void onDelete(final RemindEntity entity, final int position) {
        AlertDialog.Builder buider = new AlertDialog.Builder(mContext);
        buider.setMessage("确定删除");
        buider.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        buider.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                remindEntities.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(0, remindEntities.size());
                dialogInterface.dismiss();
            }
        });
        buider.show();
    }

    private static class AllRemindHolder extends RecyclerView.ViewHolder {
        private ItemAllRemindBinding binding;

        public AllRemindHolder(ItemAllRemindBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class AllRemindAdapter extends BaseRecyclerViewAdapter<RemindEntity, AllRemindHolder> {
        public AllRemindAdapter(List<RemindEntity> datas) {
            super(datas);
        }

        @Override public AllRemindHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            ItemAllRemindBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_all_remind, parent, false);
            return new AllRemindHolder(binding);
        }

        @Override public void onBindViewHolder(AllRemindHolder holder, int position) {
            holder.binding.setModel(new AllRemindViewModel(mContext, datas.get(position), position, AllRemindActivity.this));
        }
    }
}
