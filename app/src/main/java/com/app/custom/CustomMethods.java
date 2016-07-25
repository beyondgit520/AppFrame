package com.app.custom;

import android.databinding.BindingAdapter;
import android.view.View;

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
}
