package com.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 李 on 16-7-21.
 */
public class Testbean {
    @SerializedName("start_time")
    @Expose
    public Integer startTime;
    @SerializedName("is_acticity")
    @Expose
    public Integer isActicity;
    @SerializedName("count_list")
    @Expose
    public Integer countList;
    @SerializedName("end_time")
    @Expose
    public Integer endTime;
    @SerializedName("activity_theme")
    @Expose
    public String activityTheme;
}
