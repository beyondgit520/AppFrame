package com.app.module.login.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/5.
 */
public class LoginInfo {
    @SerializedName("sessionkey")
    @Expose
    public String sessionkey;
    @SerializedName("member_point")
    @Expose
    public String memberPoint;
    @SerializedName("member_id")
    @Expose
    public String memberId;
    @SerializedName("group_id")
    @Expose
    public String groupId;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("member_level")
    @Expose
    public String memberLevel;
    @SerializedName("extension")
    @Expose
    public Extension extension;
    @SerializedName("login_type")
    @Expose
    public String loginType;

    public class Extension {

        @SerializedName("member_info")
        @Expose
        public UserInfo memberInfo;

    }
}
