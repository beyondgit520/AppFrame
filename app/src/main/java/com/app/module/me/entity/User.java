package com.app.module.me.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/30.
 */
public class User extends BaseObservable {
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_phone")
    @Expose
    private String mobilePhone;
    @SerializedName("facebook_bind")
    @Expose
    private Integer facebookBind;
    @SerializedName("google_bind")
    @Expose
    private Integer googleBind;
    @SerializedName("mirror_bind")
    @Expose
    private Integer mirrorBind;
    @SerializedName("wifi_bind")
    @Expose
    private Integer wifiBind;

    public User(String nickname, String avatar, Integer gender, String email, String mobilePhone, Integer facebookBind, Integer googleBind, Integer mirrorBind, Integer wifiBind) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.facebookBind = facebookBind;
        this.googleBind = googleBind;
        this.mirrorBind = mirrorBind;
        this.wifiBind = wifiBind;
    }

    /**
     * @return The nickname
     */
    @Bindable
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return The avatar
     */
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return The gender
     */
    @Bindable
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return The email
     */
    @Bindable
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The mobilePhone
     */
    @Bindable
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone The mobile_phone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return The facebookBind
     */
    @Bindable
    public Integer getFacebookBind() {
        return facebookBind;
    }

    /**
     * @param facebookBind The facebook_bind
     */
    public void setFacebookBind(Integer facebookBind) {
        this.facebookBind = facebookBind;
    }

    /**
     * @return The googleBind
     */
    @Bindable
    public Integer getGoogleBind() {
        return googleBind;
    }

    /**
     * @param googleBind The google_bind
     */
    public void setGoogleBind(Integer googleBind) {
        this.googleBind = googleBind;
    }

    /**
     * @return The mirrorBind
     */
    @Bindable
    public Integer getMirrorBind() {
        return mirrorBind;
    }

    /**
     * @param mirrorBind The mirror_bind
     */
    public void setMirrorBind(Integer mirrorBind) {
        this.mirrorBind = mirrorBind;
    }

    /**
     * @return The wifiBind
     */
    @Bindable
    public Integer getWifiBind() {
        return wifiBind;
    }

    /**
     * @param wifiBind The wifi_bind
     */
    public void setWifiBind(Integer wifiBind) {
        this.wifiBind = wifiBind;
    }

    @Override public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone=" + mobilePhone +
                '}';
    }
}
