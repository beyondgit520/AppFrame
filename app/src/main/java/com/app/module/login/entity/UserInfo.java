package com.app.module.login.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/5.
 */
public class UserInfo {
    @SerializedName("member_info_id")
    @Expose
    public String memberInfoId;
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("birthday")
    @Expose
    public String birthday;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("face_big_img")
    @Expose
    public String faceBigImg;
    @SerializedName("face_small_img")
    @Expose
    public String faceSmallImg;
    @SerializedName("last_update_time")
    @Expose
    public String lastUpdateTime;

}
