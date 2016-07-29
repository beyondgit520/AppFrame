package com.app.module.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.app.R;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityMultiBinding;
import com.app.utils.MUtils;


public class MultiActivity extends BaseActivity {
    public static final int LOGIN_STEP_1 = 0;
    public static final int LOGIN_STEP_2 = 1;
    public static final int FORGOT_PWD = 2;
    public static final int CHANGE_PWD_1 = 3;
    public static final int CHANGE_PWD_2 = 4;
    public static final int SETTING_NUMBER = 5;
    public static final int SETTING_EMAIL = 6;
    public static final int SETTING_NICKNAME = 7;
    public static final int OTHER = 8;
    public boolean verifiVisible = true;
    public boolean edit1Visible = true;
    public boolean edit2Visible = true;
    public String buttonText;
    private int TYPE = -1;
    private ActivityMultiBinding binding;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_multi);
        buttonText = getString(R.string.next_step);
        switch (TYPE = getIntent().getIntExtra("TYPE", -1)) {
            case LOGIN_STEP_1: {
                binding.toolbar.setTitle(R.string.registered_members);
                break;
            }
            case LOGIN_STEP_2: {
                binding.toolbar.setTitle(R.string.registered_members);
                buttonText = getString(R.string.register_now);
                verifiVisible = false;
                break;
            }
            case FORGOT_PWD: {
                binding.toolbar.setTitle(R.string.forgot_pwd);
                break;
            }
            case CHANGE_PWD_1: {
                break;
            }
            case CHANGE_PWD_2: {
                break;
            }
            case SETTING_NUMBER: {
                break;
            }
            case SETTING_EMAIL: {
                break;
            }
            case SETTING_NICKNAME: {
                break;
            }
            default:
                try {
                    throw new Exception("type 必须传递");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.setAdapter(this);
    }

    public void click(View view) {
        switch (TYPE) {
            case LOGIN_STEP_1: {
                Intent intent = new Intent(mContext, MultiActivity.class);
                intent.putExtra("TYPE", MultiActivity.LOGIN_STEP_2);
                startActivity(intent);
                break;
            }
            case LOGIN_STEP_2: {

                break;
            }
            case FORGOT_PWD: {

                break;
            }
            case CHANGE_PWD_1: {
                break;
            }
            case CHANGE_PWD_2: {
                break;
            }
            case SETTING_NUMBER: {
                break;
            }
            case SETTING_EMAIL: {
                break;
            }
            case SETTING_NICKNAME: {
                break;
            }
            default:
                try {
                    throw new Exception("type 必须传递");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void verifi(View view) {
        MUtils.toast("verifi");
    }
}
