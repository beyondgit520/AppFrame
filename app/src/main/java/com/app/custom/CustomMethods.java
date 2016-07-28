package com.app.custom;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.widget.DividerItemDecoration;

/**
 * Created by 李 on 16-7-19.
 */
public class CustomMethods {
/*    @BindingAdapter("imageUrl")
    public static void loadImage(SimpleDraweeView view, String url) {
        Uri uri = Uri.parse(url);
        view.setImageURI(uri);
    }*/

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
