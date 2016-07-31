package com.app.custom;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.R;
import com.app.widget.DividerItemDecoration;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 李 on 16-7-19.
 */
public class CustomMethods {
    // See the sample project how to use ImageLoader correctly.
    static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) // resource or drawable
            .showImageForEmptyUri(R.mipmap.ic_launcher) // resource or drawable
            .showImageOnFail(R.mipmap.ic_launcher) // resource or drawable
            .cacheOnDisk(true)
            .cacheInMemory(true)
            .delayBeforeLoading(300)
            .build();

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        ImageLoader.getInstance().displayImage(url, view, options);
    }

    @BindingAdapter("android:paddingLeft")
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(padding,
                padding * 3,
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("itemDecoration")
    public static void setItemDecoration(RecyclerView recyclerView, boolean enable) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager && enable)
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager
                    .VERTICAL));
    }

    @BindingAdapter("customDecoration")
    public static void setCustomItemDecoration(RecyclerView recyclerView, Drawable decoration) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager)
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager
                    .VERTICAL, decoration));
    }
}
