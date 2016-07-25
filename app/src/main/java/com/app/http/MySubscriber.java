package com.app.http;

import android.widget.Toast;

import com.app.base.APP;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import rx.Subscriber;

/**
 * Created by 李 on 16-7-20.
 */
public abstract class MySubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(APP.getContext(), "网络中断，请检查您的网络状态SocketTimeoutException", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(APP.getContext(), "网络中断，请检查您的网络状态ConnectException", Toast.LENGTH_SHORT).show();
        } else if (e instanceof UnknownHostException) {
            Toast.makeText(APP.getContext(), "网络中断，请检查您的网络状态UnknownHostException", Toast.LENGTH_SHORT).show();
        }else if (e instanceof UnknownServiceException) {
            Toast.makeText(APP.getContext(), "网络中断，请检查您的网络状态UnknownServiceException", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(APP.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
