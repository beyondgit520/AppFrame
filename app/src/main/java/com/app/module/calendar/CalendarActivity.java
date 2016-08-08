package com.app.module.calendar;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.base.BaseRecyclerViewAdapter;
import com.app.databinding.ActivityCalendarBinding;
import com.app.databinding.ItemCalendarBinding;
import com.app.utils.Logger;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[]{
            Calendars._ID,                           // 0
            Calendars.ACCOUNT_NAME,                  // 1
            Calendars.CALENDAR_DISPLAY_NAME          // 2
    };
    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";
    private ActivityCalendarBinding binding;
    private CalendarAdapter adapter = new CalendarAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        binding.refreshLayout.setOnRefreshListener(this);
//        query();
        checkUser();
//        check();
    }

    private void checkUser() {
        Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);

        System.out.println("Count: " + userCursor.getCount());
        Logger.d(tag, "Count: " + userCursor.getCount());

//        for (userCursor.moveToFirst(); !userCursor.isAfterLast(); userCursor.moveToNext()) {
//            Logger.d(tag, "name: " + userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME")));
//
//
//            String userName1 = userCursor.getString(userCursor.getColumnIndex("name"));
//            String userName0 = userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME"));
//            Logger.d(tag, "NAME: " + userName1 + " -- ACCOUNT_NAME: " + userName0);
//        }
        userCursor.moveToFirst();
        Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null, null, null, null);
//        if (eventCursor.getCount() > 0) {
//            eventCursor.moveToFirst();            //注意：这里与添加事件时的账户相对应，都是向最后一个账户添加
//            String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
//            Logger.d(tag, eventTitle);
//        }

        while (eventCursor.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            Logger.d(tag, "EVENT:");
            for (int i = 0; i < eventCursor.getCount(); i++) {
                Logger.d(tag, "event:" + eventCursor.getString(eventCursor.getColumnIndex("title")));
            }
            // Get the field values
/*            calID = eventCursor.getLong(eventCursor.getColumnIndex("title"));
            displayName = eventCursor.getString(eventCursor.getColumnIndex("dtend"));
            accountName = eventCursor.getString(eventCursor.getColumnIndex("dtstart"));
            Logger.d(tag, "\ncalID:"+calID + "\ndisplayName:" + displayName + "\naccountName:" + accountName + "\n");*/
            // Do something with the values...

        }
    }

    private void check() {
        Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null, null, null, null);
//        if (eventCursor.getCount() > 0) {
//            eventCursor.moveToFirst();            //注意：这里与添加事件时的账户相对应，都是向最后一个账户添加
//            String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
//            Logger.d(tag, eventTitle);
//        }

        while (eventCursor.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;

            // Get the field values
            calID = eventCursor.getLong(eventCursor.getColumnIndex("title"));
            displayName = eventCursor.getString(eventCursor.getColumnIndex("dtend"));
            accountName = eventCursor.getString(eventCursor.getColumnIndex("dtstart"));
            Logger.d(tag, "\ncalID:" + calID + "\ndisplayName:" + displayName + "\naccountName:" + accountName + "\n");
            // Do something with the values...

        }
    }

    private void query() {
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = Uri.parse(calanderEventURL);//Calendars.CONTENT_URI;

        String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("

                + Calendars.ACCOUNT_TYPE + " = ?))";

        String[] selectionArgs = new String[]{"plnana0604@gmail.com", "com.google"};

// Submit the query and get a Cursor object back.

        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
//        cur = cr.query(uri, null, null, null, null);

        // Use the cursor to step through the returned records
        Logger.d(tag, "查询日历");
        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            Logger.d(tag, "\ncalID:" + calID + "\ndisplayName:" + displayName + "\naccountName:" + accountName + "\n");
            // Do something with the values...

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stock_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mirror) {
            MUtils.toast("mirror");
            return true;
        } else if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(mContext, AddEventActivity.class));
            return true;
        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        binding.refreshLayout.setRefreshing(false);
    }

    private static class CalendarHolder extends RecyclerView.ViewHolder {
        private ItemCalendarBinding binding;

        public CalendarHolder(ItemCalendarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class CalendarAdapter extends BaseRecyclerViewAdapter<Object, CalendarHolder> {
        public CalendarAdapter(List<Object> datas) {
            super(datas);
        }

        @Override
        public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            super.onCreateViewHolder(parent, viewType);
            ItemCalendarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_calendar, parent, false);
            return new CalendarHolder(binding);
        }

        @Override
        public void onBindViewHolder(CalendarHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}
