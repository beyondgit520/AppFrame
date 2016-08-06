package com.app.module.calendar.viewmodel;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.app.BR;
import com.app.R;

import java.util.Calendar;

/**
 * Created by 李 on 16-8-6.
 */
public class AddEventViewModel extends BaseObservable {
    private Context context;
    private String startTime;
    private String endTime;
    private String repeat;
    private String travelTime;
    private String remind;

    public AddEventViewModel(Context context) {
        this.context = context;
    }

    public void selectStartTime() {
        Dialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                Dialog timeDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        setStartTime(year + "/" + monthOfYear + "/" + dayOfMonth + " " + i + ":" + i1);
                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
                timeDialog.show();
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void selectEndTime() {
        Dialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                Dialog timeDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        setEndTime(year + "/" + monthOfYear + "/" + dayOfMonth + " " + i + ":" + i1);
                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
                timeDialog.show();
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void selectRepeat() {
        AlertDialog.Builder b = new AlertDialog.Builder(context, R.style.AppTheme_Dialog_Alert);
        b.setTitle("Repeat");
        final int[] select = {-1};
        b.setSingleChoiceItems(new String[]{"不重复", "每天", "每周", "每月", "每年"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                select[0] = which;
            }
        });
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setRepeat(select[0] + "");
            }
        });
        b.show();
    }

    public void selectTravelTime() {
        Dialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                Dialog timeDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        setTravelTime(year + "/" + monthOfYear + "/" + dayOfMonth + " " + i + ":" + i1);
                    }
                }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
                timeDialog.show();
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public void selectRemind() {
        AlertDialog.Builder b = new AlertDialog.Builder(context, R.style.AppTheme_Dialog_Alert);
        b.setTitle("Remind");
        final int[] select = {-1};
        b.setSingleChoiceItems(new String[]{"5分钟前", "10分钟前", "半小时前", "1小时前", "1天前"}, 0, new DialogInterface
                .OnClickListener
                () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                select[0] = which;
            }
        });
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setRemind(select[0] + "");
            }
        });
        b.show();
    }

    @Bindable
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        notifyPropertyChanged(BR.startTime);
    }

    @Bindable
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        notifyPropertyChanged(BR.endTime);
    }

    @Bindable
    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
        notifyPropertyChanged(BR.repeat);
    }

    @Bindable
    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
        notifyPropertyChanged(BR.travelTime);
    }

    @Bindable
    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
        notifyPropertyChanged(BR.remind);
    }
}
