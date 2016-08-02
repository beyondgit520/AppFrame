package com.app.module.stock.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李 on 16-8-1.
 */
public class Stockinfo {
    @SerializedName("stockinfo")
    @Expose
    private List<StockEntity> stockinfo = new ArrayList<>();

    public List<StockEntity> getStockinfo() {
        return stockinfo;
    }

    public void setStockinfo(List<StockEntity> stockinfo) {
        this.stockinfo = stockinfo;
    }
}
