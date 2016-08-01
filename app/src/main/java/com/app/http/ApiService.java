package com.app.http;

import com.app.Testbean;
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
    /**
     * @param p    分页
     * @param ps   每页条数
     * @param sort 排序1：最新，2：活动
     * @return
     */
    @GET("stylebook/public_list")
    Observable<HttpResult<Testbean>> getStyleList(@Query("p") int p, @Query("ps") int ps, @Query("sort") int sort);


    @GET("http://apis.baidu.com/songshuxiansheng/news/news")
    Observable<HttpResult<List<NewsEntity>>> getNewsList();


    @GET("http://apis.baidu.com/apistore/stockservice/usastock")
    Observable<HttpResult<Stockinfo>> searchStock(@Query("stockid") String stockids, @Query("list") int list);
}
