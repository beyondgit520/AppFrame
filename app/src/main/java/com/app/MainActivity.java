package com.app;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.base.APP;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityMainBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.calendar.AddEventActivity;
import com.app.module.calendar.CalendarActivity;
import com.app.module.calendar.RemindActivity;
import com.app.module.main.AttentionAdapter;
import com.app.module.me.MeActivity;
import com.app.module.news.NewsActivity;
import com.app.module.news.NewsEvent;
import com.app.module.news.entity.NewsEntity;
import com.app.module.setting.SettingActivity;
import com.app.module.stock.StockActivity;
import com.app.utils.MUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private RecyclerView attentionView;
    private AttentionAdapter attentionAdapter;
    private List<NewsEntity> attentionDatas = new ArrayList<>();
    private Handler timeHandler = new Handler();
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            getAttention();
            timeHandler.postDelayed(this, 10 * 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer_indicator);
        toolbar.setTitle(R.string.menu);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
                MUtils.toast(APP.getLoginInfo() == null ? "未登录" : "已经登录");
            }
        });
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);
        attentionView = (RecyclerView) findViewById(R.id.recyclerView);
        attentionView.setHasFixedSize(true);
        attentionAdapter = new AttentionAdapter(attentionDatas);
        attentionView.setAdapter(attentionAdapter);
        attentionAdapter.setEvent(new NewsEvent() {
            @Override
            public void onItemClick(NewsEntity entity) {
                MUtils.toast("未实现");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        timeHandler.post(timeRunnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timeHandler.removeCallbacks(timeRunnable);
    }

    private void getAttention() {
        HttpMethods.getInstance().getNewsList(new MySubscriber<List<NewsEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(List<NewsEntity> newsEntities) {
                attentionDatas.clear();
                attentionDatas.addAll(newsEntities);
                attentionAdapter.notifyDataSetChanged();
            }
        }, this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            MUtils.toast(R.string.share);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_personal: {
                startActivity(new Intent(mContext, MeActivity.class));
                break;
            }
            case R.id.nav_news: {
                startActivity(new Intent(mContext, NewsActivity.class));
                break;
            }
            case R.id.nav_stock: {
                startActivity(new Intent(mContext, StockActivity.class));
                break;
            }
            case R.id.nav_traffic: {
                MUtils.toast("未实现");
                break;
            }
            case R.id.nav_calendar: {
                startActivity(new Intent(mContext, CalendarActivity.class));
                break;
            }
            case R.id.nav_remind: {
                startActivity(new Intent(mContext, RemindActivity.class));
                break;
            }
            case R.id.nav_settings: {
                startActivity(new Intent(mContext, SettingActivity.class));
            }

            break;
        }
        transaction.commit();
//        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
