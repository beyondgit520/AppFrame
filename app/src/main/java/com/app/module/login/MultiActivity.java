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
    public static final int FORGOT_PWD_1 = 2;
    public static final int CHANGE_PWD_1 = 3;
    public static final int CHANGE_PWD_2 = 4;
    public static final int SETTING_NUMBER = 5;
    public static final int SETTING_EMAIL = 6;
    public static final int SETTING_NICKNAME = 7;
    public static final int FORGOT_PWD_2 = 8;
    public boolean verifiVisible = true;
    public boolean edit1Visible = true;
    public boolean edit2Visible = true;
    public String buttonText;
    public String hint1;
    public String hint2;
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
                hint1 = getString(R.string.enter_phone_email);
                hint2 = getString(R.string.enter_verification_code);
                break;
            }
            case LOGIN_STEP_2: {
                binding.toolbar.setTitle(R.string.registered_members);
                buttonText = getString(R.string.register_now);
                hint1 = getString(R.string.enter_pwd);
                hint2 = getString(R.string.confirm_password_again);
                verifiVisible = false;
                break;
            }
            case FORGOT_PWD_1: {
                binding.toolbar.setTitle(R.string.forgot_pwd);
                hint1 = getString(R.string.enter_phone_email);
                hint2 = getString(R.string.enter_verification_code);
                break;
            }
            case FORGOT_PWD_2: {
                binding.toolbar.setTitle(R.string.change_pwd);
                buttonText = getString(R.string.confirm);
                hint1 = getString(R.string.please_set_your_pwd);
                hint2 = getString(R.string.confirm_password_again);
                verifiVisible = false;
                break;
            }
            case CHANGE_PWD_1: {
                binding.toolbar.setTitle(R.string.change_pwd);
                hint1 = getString(R.string.enter_binding_mobile_email);
                hint2 = getString(R.string.enter_verification_code);
                break;
            }
            case CHANGE_PWD_2: {
                binding.toolbar.setTitle(R.string.change_pwd);
                buttonText = getString(R.string.save);
                hint1 = getString(R.string.please_set_your_pwd);
                hint2 = getString(R.string.confirm_password_again);
                verifiVisible = false;
                break;
            }
            case SETTING_NUMBER: {
                binding.toolbar.setTitle(R.string.title_my_mobile_phone);
                buttonText = getString(R.string.save);
                hint1 = getString(R.string.enter_your_bind_mobile);
                hint2 = getString(R.string.confirm_password_again);
                break;
            }
            case SETTING_EMAIL: {
                binding.toolbar.setTitle(R.string.set_email);
                buttonText = getString(R.string.save);
                hint1 = getString(R.string.enter_your_bind_email);
                hint2 = getString(R.string.enter_verification_code);
                break;
            }
            case SETTING_NICKNAME: {
                binding.toolbar.setTitle(R.string.modify_nickname);
                buttonText = getString(R.string.submit);
                hint1 = "";
                edit2Visible = false;
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
            case FORGOT_PWD_1: {
                Intent intent = new Intent(mContext, MultiActivity.class);
                intent.putExtra("TYPE", MultiActivity.FORGOT_PWD_2);
                startActivity(intent);
                break;
            }
            case FORGOT_PWD_2: {

                break;
            }
            case CHANGE_PWD_1: {
                Intent intent = new Intent(mContext, MultiActivity.class);
                intent.putExtra("TYPE", MultiActivity.CHANGE_PWD_2);
                startActivity(intent);
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
