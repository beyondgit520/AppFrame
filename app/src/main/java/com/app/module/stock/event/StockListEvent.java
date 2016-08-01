package com.app.module.stock.event;

import com.app.module.stock.entity.StockEntity;

/**
 * Created by 李 on 16-8-1.
 */
public interface StockListEvent {
    void clickItem(StockEntity entity);
}
