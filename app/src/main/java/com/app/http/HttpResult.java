package com.app.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by limin on 2016/7/4.
 */
public class HttpResult<T> {
    @SerializedName("ret") @Expose public Integer ret;
    @SerializedName("timestamp") @Expose public Integer timestamp;
    @SerializedName("msg") @Expose public String msg;
    /**
     * 实际的data
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
