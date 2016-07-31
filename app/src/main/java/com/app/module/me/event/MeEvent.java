package com.app.module.me.event;

/**
 * Created by Administrator on 2016/7/30.
 */
public interface MeEvent {
    /**
     * 点击头像
     */
    void clickAvatar();

    void clickNickName();

    void clickGender();

    void clickEmail();

    void clickMobile();

    /**
     * 点击facebook 绑定
     *
     * @param type 绑定还是解绑
     */
    void clickFacebook(int type);

    void clickGoogle(int type);

    void clickMirror(int type);

    void clickWifi(int type);

    void modifiPwd();
}
