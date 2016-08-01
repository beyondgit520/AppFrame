package com.app.module.stock.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 李 on 16-8-1.
 */
public class StockEntity {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("currentPrice")
    @Expose
    private Double currentPrice;
    @SerializedName("growthPercent")
    @Expose
    private Double growthPercent;
    @SerializedName("growth")
    @Expose
    private Double growth;
    @SerializedName("openningPrice")
    @Expose
    private Double openningPrice;
    @SerializedName("closingPrice")
    @Expose
    private Double closingPrice;
    @SerializedName("hPrice")
    @Expose
    private Double hPrice;
    @SerializedName("lPrice")
    @Expose
    private Double lPrice;
    @SerializedName("52hPrice")
    @Expose
    private Double _52hPrice;
    @SerializedName("52lPrice")
    @Expose
    private Double _52lPrice;
    @SerializedName("totalNumber")
    @Expose
    private Long totalNumber;
    @SerializedName("tenTotalNumber")
    @Expose
    private Long tenTotalNumber;
    @SerializedName("marketValue")
    @Expose
    private Long marketValue;
    @SerializedName("eps")
    @Expose
    private Double eps;
    @SerializedName("pe")
    @Expose
    private Double pe;
    @SerializedName("beta")
    @Expose
    private Double beta;
    @SerializedName("bonus")
    @Expose
    private Double bonus;
    @SerializedName("yields")
    @Expose
    private Double yields;
    @SerializedName("stockNum")
    @Expose
    private Long stockNum;
    @SerializedName("after-hoursTradePrice")
    @Expose
    private Double afterHoursTradePrice;
    @SerializedName("after-hoursTradeGrowth")
    @Expose
    private Double afterHoursTradeGrowth;
    @SerializedName("after-hoursTradeGrowthPercent")
    @Expose
    private Double afterHoursTradeGrowthPercent;
    @SerializedName("after-hoursTradeNum")
    @Expose
    private Integer afterHoursTradeNum;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The currentPrice
     */
    public Double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice The currentPrice
     */
    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return The growthPercent
     */
    public Double getGrowthPercent() {
        return growthPercent;
    }

    /**
     * @param growthPercent The growthPercent
     */
    public void setGrowthPercent(Double growthPercent) {
        this.growthPercent = growthPercent;
    }

    /**
     * @return The growth
     */
    public Double getGrowth() {
        return growth;
    }

    /**
     * @param growth The growth
     */
    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    /**
     * @return The openningPrice
     */
    public Double getOpenningPrice() {
        return openningPrice;
    }

    /**
     * @param openningPrice The openningPrice
     */
    public void setOpenningPrice(Double openningPrice) {
        this.openningPrice = openningPrice;
    }

    /**
     * @return The closingPrice
     */
    public Double getClosingPrice() {
        return closingPrice;
    }

    /**
     * @param closingPrice The closingPrice
     */
    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

    /**
     * @return The hPrice
     */
    public Double getHPrice() {
        return hPrice;
    }

    /**
     * @param hPrice The hPrice
     */
    public void setHPrice(Double hPrice) {
        this.hPrice = hPrice;
    }

    /**
     * @return The lPrice
     */
    public Double getLPrice() {
        return lPrice;
    }

    /**
     * @param lPrice The lPrice
     */
    public void setLPrice(Double lPrice) {
        this.lPrice = lPrice;
    }

    /**
     * @return The _52hPrice
     */
    public Double get52hPrice() {
        return _52hPrice;
    }

    /**
     * @param _52hPrice The 52hPrice
     */
    public void set52hPrice(Double _52hPrice) {
        this._52hPrice = _52hPrice;
    }

    /**
     * @return The _52lPrice
     */
    public Double get52lPrice() {
        return _52lPrice;
    }

    /**
     * @param _52lPrice The 52lPrice
     */
    public void set52lPrice(Double _52lPrice) {
        this._52lPrice = _52lPrice;
    }

    /**
     * @return The totalNumber
     */
    public Long getTotalNumber() {
        return totalNumber;
    }

    /**
     * @param totalNumber The totalNumber
     */
    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * @return The tenTotalNumber
     */
    public Long getTenTotalNumber() {
        return tenTotalNumber;
    }

    /**
     * @param tenTotalNumber The tenTotalNumber
     */
    public void setTenTotalNumber(Long tenTotalNumber) {
        this.tenTotalNumber = tenTotalNumber;
    }

    /**
     * @return The marketValue
     */
    public Long getMarketValue() {
        return marketValue;
    }

    /**
     * @param marketValue The marketValue
     */
    public void setMarketValue(Long marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * @return The eps
     */
    public Double getEps() {
        return eps;
    }

    /**
     * @param eps The eps
     */
    public void setEps(Double eps) {
        this.eps = eps;
    }

    /**
     * @return The pe
     */
    public Double getPe() {
        return pe;
    }

    /**
     * @param pe The pe
     */
    public void setPe(Double pe) {
        this.pe = pe;
    }

    /**
     * @return The beta
     */
    public Double getBeta() {
        return beta;
    }

    /**
     * @param beta The beta
     */
    public void setBeta(Double beta) {
        this.beta = beta;
    }

    /**
     * @return The bonus
     */
    public Double getBonus() {
        return bonus;
    }

    /**
     * @param bonus The bonus
     */
    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    /**
     * @return The yields
     */
    public Double getYields() {
        return yields;
    }

    /**
     * @param yields The yields
     */
    public void setYields(Double yields) {
        this.yields = yields;
    }

    /**
     * @return The stockNum
     */
    public Long getStockNum() {
        return stockNum;
    }

    /**
     * @param stockNum The stockNum
     */
    public void setStockNum(Long stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * @return The afterHoursTradePrice
     */
    public Double getAfterHoursTradePrice() {
        return afterHoursTradePrice;
    }

    /**
     * @param afterHoursTradePrice The after-hoursTradePrice
     */
    public void setAfterHoursTradePrice(Double afterHoursTradePrice) {
        this.afterHoursTradePrice = afterHoursTradePrice;
    }

    /**
     * @return The afterHoursTradeGrowth
     */
    public Double getAfterHoursTradeGrowth() {
        return afterHoursTradeGrowth;
    }

    /**
     * @param afterHoursTradeGrowth The after-hoursTradeGrowth
     */
    public void setAfterHoursTradeGrowth(Double afterHoursTradeGrowth) {
        this.afterHoursTradeGrowth = afterHoursTradeGrowth;
    }

    /**
     * @return The afterHoursTradeGrowthPercent
     */
    public Double getAfterHoursTradeGrowthPercent() {
        return afterHoursTradeGrowthPercent;
    }

    /**
     * @param afterHoursTradeGrowthPercent The after-hoursTradeGrowthPercent
     */
    public void setAfterHoursTradeGrowthPercent(Double afterHoursTradeGrowthPercent) {
        this.afterHoursTradeGrowthPercent = afterHoursTradeGrowthPercent;
    }

    /**
     * @return The afterHoursTradeNum
     */
    public Integer getAfterHoursTradeNum() {
        return afterHoursTradeNum;
    }

    /**
     * @param afterHoursTradeNum The after-hoursTradeNum
     */
    public void setAfterHoursTradeNum(Integer afterHoursTradeNum) {
        this.afterHoursTradeNum = afterHoursTradeNum;
    }

}
