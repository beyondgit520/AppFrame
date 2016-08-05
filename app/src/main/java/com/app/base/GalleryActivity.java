package com.app.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.R;
import com.app.utils.MUtils;
import com.app.utils.RxBus;

import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class GalleryActivity extends BaseActivity implements GalleryFinal.OnHanlderResultCallback {
    private final int REQUEST_CODE_GALLERY = 0x1;
    private final int REQUEST_CODE_CAMERA = 0x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void photo(final View view) {
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setCropSquare(true)
                .setForceCrop(true)
                .setCropHeight(300)
                .setCropWidth(300)
                .build();
        switch (view.getId()) {
            case R.id.cancel_button:
                finish();
                break;
            case R.id.from_gallery:
                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, this);
                break;
            case R.id.from_camera:
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, this);
                break;
        }

    }

    @Override public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
        RxBus.getDefault().post(resultList.get(0));
        finish();
    }

    @Override public void onHanlderFailure(int requestCode, String errorMsg) {
        MUtils.toast(errorMsg);
        finish();
    }
}
