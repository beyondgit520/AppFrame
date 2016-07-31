package com.app.module.news.entity;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/30.
 */
public class NewsEntity extends BaseObservable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image_url")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String description;
    @SerializedName("publish_time")
    @Expose
    private Integer publishTime;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * @param id
     * @param title
     * @param publishTime
     * @param description
     * @param image
     * @param url
     */
    public NewsEntity(Integer id, String image, String title, String description, Integer publishTime, String url) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.publishTime = publishTime;
        this.url = url;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The publishTime
     */
    public Integer getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime The publish_time
     */
    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
