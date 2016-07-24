package com.app.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Limin on 2016/7/3.
 */
public class BaseFragment extends Fragment{
    protected Context mContext;
    protected String tag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        tag = this.getClass().getSimpleName();
    }
}
