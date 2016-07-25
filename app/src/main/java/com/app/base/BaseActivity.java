package com.app.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import com.app.http.ActivityLifecycle;
import com.app.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/7/3.
 */
public class BaseActivity extends AppCompatActivity {
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected Context mContext;
    protected String tag;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        tag = this.getClass().getSimpleName();
        progressDialog = new ProgressDialog(mContext);
    }

    @Override
    protected void onDestroy() {
        deleteList.clear();
        for (SubscriberWrapper wrapper : subscribers) {
            if (wrapper.unsubscribeOn == ActivityLifecycle.OnDestroy) {
                if (wrapper.subscriber != null && !wrapper.subscriber.isUnsubscribed()) {
                    wrapper.subscriber.unsubscribe();
                    Logger.d(tag, "wrapper.subscriber.unsubscribe()");
                }
                deleteList.add(wrapper);
            }
        }
        subscribers.removeAll(deleteList);
        super.onDestroy();
    }


    private List<SubscriberWrapper> subscribers;
    private List<SubscriberWrapper> deleteList;

    public void addSubscriber(Subscriber subscriber, ActivityLifecycle unsubscribeOn) {
        if (subscribers == null) {
            subscribers = new ArrayList<>();
            deleteList = new ArrayList<>();
        }
        subscribers.add(new SubscriberWrapper(subscriber, unsubscribeOn));
    }

    private class SubscriberWrapper {
        Subscriber subscriber;
        ActivityLifecycle unsubscribeOn;

        public SubscriberWrapper(Subscriber subscriber, ActivityLifecycle unsubscribeOn) {
            this.subscriber = subscriber;
            this.unsubscribeOn = unsubscribeOn;
        }
    }
}
