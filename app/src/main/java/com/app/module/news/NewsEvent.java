package com.app.module.news;

import com.app.module.news.entity.NewsEntity;

/**
 * Created by Administrator on 2016/7/31.
 */
public interface NewsEvent {
    void onItemClick(NewsEntity entity);
}
