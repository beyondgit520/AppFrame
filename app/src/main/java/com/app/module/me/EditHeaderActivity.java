package com.app.module.me;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.GalleryActivity;
import com.app.custom.CustomMethods;
import com.app.databinding.ActivityEditHeaderBinding;
import com.app.utils.RxBus;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.functions.Action1;

public class EditHeaderActivity extends BaseActivity {
    private ActivityEditHeaderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_header);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_ok);
        return super.onCreateOptionsMenu(menu);

    }

    public void selectHeader(View view) {
        startActivity(new Intent(this, GalleryActivity.class));
        RxBus.getDefault().toObservable(PhotoInfo.class).subscribe(new Action1<PhotoInfo>() {
            @Override public void call(PhotoInfo photoInfo) {
                ImageLoader.getInstance().displayImage("file://" + photoInfo.getPhotoPath(), binding.header, CustomMethods.headOptions);
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_settings) {
            // TODO: 2016/8/4 提交头像 
        }
        return super.onOptionsItemSelected(item);
    }
}
