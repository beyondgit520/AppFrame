package com.app.module.calendar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityAddEventBinding;
import com.app.module.calendar.viewmodel.AddEventViewModel;

import java.util.Calendar;
import java.util.TimeZone;

public class AddEventActivity extends BaseActivity {
    private ActivityAddEventBinding binding;
    private AddEventViewModel viewModel;
    private String calanderURL;
    private String calanderEventURL;
    private String calanderRemiderURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_event);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = new AddEventViewModel(mContext);
        binding.setViewModel(viewModel);
    }


    private void addCalender() {
        calanderURL = "content://com.android.calendar/calendars";
        calanderEventURL = "content://com.android.calendar/events";
        calanderRemiderURL = "content://com.android.calendar/reminders";
        //获取要出入的gmail账户的id
        String calId = "";
        Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null,
                null, null, null);
        if (null != userCursor && userCursor.getCount() > 0) {
            userCursor.moveToFirst();
            calId = userCursor.getString(userCursor.getColumnIndex("_id"));
        }
        ContentValues event = new ContentValues();
        //标题
        event.put(CalendarContract.Events.TITLE, "自己创建的提醒");
        //内容
        event.put("description", "一个产品能获得成功，是因为它真正帮人们解决了某个问题。这听上去过于基础，但这的的确确是打造一个好产品最为重要的因素。");
        //插入到账户
        event.put("calendar_id", calId);


        //开始时间
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, 14);
        long start = mCalendar.getTime().getTime();
        long stbrt = System.currentTimeMillis();
        //截止时间，如果需要设置一周或者一个月可以改截止日期即可
        mCalendar.set(Calendar.HOUR_OF_DAY, 15);
        long end = mCalendar.getTime().getTime();

        //起始时间
        event.put(CalendarContract.Events.DTSTART, start);
        //截止时间
        event.put(CalendarContract.Events.DTEND, end);
        //控制是否事件触发报警，提醒如下
        event.put(CalendarContract.Events.HAS_ALARM, 1);
        //设置时区,否则会报错
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        //设置一个全天事件的条目
        //event.put("allDay", 1); // 0 for false, 1 for true
        //设置地理位置
        //event.put(CalendarContract.Events.EVENT_LOCATION, "Event Location");

        Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
        long id = Long.parseLong(newEvent.getLastPathSegment());
        ContentValues values = new ContentValues();
        values.put("event_id", id);
        //提前10分钟有提醒
        values.put(CalendarContract.Reminders.MINUTES, 10);
        values.put(CalendarContract.Reminders.EVENT_ID, id);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        ContentResolver cr1 = getContentResolver(); // 为刚才新添加的event添加reminder
        //这里在6.0中检查下权限信息
        cr1.insert(CalendarContract.Reminders.CONTENT_URI, values); // 调用这个方法返回值是一个Uri
        Toast.makeText(this, "插入事件成功!!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_ok);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            addCalender();
        } else {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
