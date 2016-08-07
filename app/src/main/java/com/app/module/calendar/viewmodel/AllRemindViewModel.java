package com.app.module.calendar.viewmodel;

import android.content.Context;

import com.app.module.calendar.entitiy.RemindEntity;

/**
 * Created by Administrator on 2016/8/7.
 */
public class AllRemindViewModel {

    private Context context;
    private RemindEntity entity;
    private onDeleteLinstener linstener;
    private int position = -1;

    public AllRemindViewModel(Context context, RemindEntity entity, int position, onDeleteLinstener linstener) {
        this.context = context;
        this.entity = entity;
        this.position = position;
        this.linstener = linstener;
    }

    public onDeleteLinstener getLinstener() {
        return linstener;
    }

    public RemindEntity getEntity() {
        return entity;
    }

    public int getPosition() {
        return position;
    }

    public interface onDeleteLinstener {
        void onDelete(RemindEntity entity, int position);
    }
}
