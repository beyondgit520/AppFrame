package com.app.http;


import com.app.Testbean;
import com.app.base.BaseActivity;
import com.app.module.news.entity.NewsEntity;
import com.app.module.stock.entity.Stockinfo;
import com.app.utils.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by limin on 2016/7/4. 网络请求类
 */
public class HttpMethods {

    public static final String BASE_URL = "http://apis.baidu.com/";

    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private OkHttpClient.Builder builder;
    private ApiService apiService;

    private HttpMethods() {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("apikey", "ea08a0a01fbf0eee3928f561c6cd08e0")
                        .build();
                long t1 = System.nanoTime();
                Logger.d("retrofit", String.format("Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Logger.d("retrofit", String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        });
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpMethods getInstance() {
        return SinglenHolder.INSTANCE;
    }

    /**
     * 请求方法开始
     */


    public void getStyleList(Subscriber<Testbean> subscriber, BaseActivity activity, int p, int ps, int sort) {
        toSubscribe(apiService.getStyleList(p, ps, sort).map(new HttpResultFunc<Testbean>())
                , subscriber, activity);
    }

    public void getNewsList(Subscriber<List<NewsEntity>> subscriber, BaseActivity activity) {
        toSubscribe(apiService.getNewsList().map(new HttpResultFunc<List<NewsEntity>>())
                , subscriber, activity);
    }

    public void searchStock(Subscriber<Stockinfo> subscriber, BaseActivity activity, String stockids, int list) {
        toSubscribe(apiService.searchStock(stockids, list).map(new HttpResultFunc<Stockinfo>())
                , subscriber, activity);
    }

    /**
     * 请求方法结束
     */

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s, BaseActivity activity) {
        o.subscribeOn(Schedulers.io())
                .lift(new BindActivityOperator<T>(activity))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 创建单例
     */
    private static class SinglenHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.ret != 0) {
                throw new ApiException(tHttpResult.msg);
            }
            return tHttpResult.getData();
        }
    }

    private class ActivityFunc<T> implements Func1<T, Observable<HttpResult>> {


        @Override
        public Observable<HttpResult> call(T t) {
            return null;
        }
    }
}
