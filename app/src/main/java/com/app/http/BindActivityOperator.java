package com.app.http;

import com.app.base.BaseActivity;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 李 on 16-7-21.
 */
public class BindActivityOperator <T> implements Observable.Operator<T, T> {

    private BaseActivity activity;
    private ActivityLifecycle unsubscribeOn;

    public BindActivityOperator(BaseActivity activity) {
        this(activity, ActivityLifecycle.OnDestroy);
    }

    public BindActivityOperator(BaseActivity activity, ActivityLifecycle unsubscribeOn) {
        this.activity = activity;
        this.unsubscribeOn = unsubscribeOn;
    }

    @Override
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        activity.addSubscriber(subscriber, unsubscribeOn);
        return subscriber;
    }
}
