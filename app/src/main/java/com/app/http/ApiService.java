package com.app.http;

import com.app.module.login.entity.LoginInfo;
import com.app.module.news.entity.NewsEntity;
import com.app.module.stock.entity.Stockinfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by limin on 2016/7/4. retrofit 接口请求
 */
public interface ApiService {


    @GET("http://apis.baidu.com/songshuxiansheng/news/news")
    Observable<HttpResult<List<NewsEntity>>> getNewsList();


    @GET("http://apis.baidu.com/apistore/stockservice/usastock")
    Observable<HttpResult<Stockinfo>> searchStock(@Query("stockid") String stockids, @Query("list") int list);

    @GET("https://android.app.com/index.php")
    Observable<HttpResult<LoginInfo>> emailLogin(@Query("model") String
                                                         model, @Query("action") String action, @Query("email") String email, @Query("password") String pwd);
}
