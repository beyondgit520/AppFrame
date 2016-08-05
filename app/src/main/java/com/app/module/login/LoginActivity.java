package com.app.module.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.app.R;
import com.app.base.APP;
import com.app.base.BaseActivity;
import com.app.databinding.ActivityLoginBinding;
import com.app.http.HttpMethods;
import com.app.http.MySubscriber;
import com.app.module.login.entity.LoginInfo;
import com.app.utils.Logger;
import com.app.utils.MUtils;
import com.app.utils.SPUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1;
    private ActivityLoginBinding binding;
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setAdapter(this);
        initFaceBook();
        initGoogle();
        binding.userPsdTil.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!TextUtils.isEmpty(binding.userNameTil.getEditText().getText()) && !TextUtils.isEmpty(binding
                            .userPsdTil.getEditText().getText())) {
                        signIn();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void signIn() {
        String email = binding.userNameTil.getEditText().getText().toString();
        String pwd = binding.userPsdTil.getEditText().getText().toString();
        if (!TextUtils.isDigitsOnly(email)) {
            if (!MUtils.isEmail(email)) {
                MUtils.toast("邮箱格式不对");
                return;
            }
        }
        if (pwd.length() < 6) {
            MUtils.toast("密码最少6位");
            return;
        }

        HttpMethods.getInstance().emailLogin(new MySubscriber<LoginInfo>() {
            @Override public void onStart() {
                super.onStart();
                progressDialog.show();
            }

            @Override public void onCompleted() {
                progressDialog.dismiss();
            }

            @Override public void onError(Throwable e) {
                super.onError(e);
                progressDialog.dismiss();
            }

            @Override public void onNext(LoginInfo loginInfo) {
                APP.setLoginInfo(loginInfo);
                SPUtils.put(APP.getContext(), "loginInfo", new Gson().toJson(loginInfo));
            }
        }, this, email, pwd);
    }

    private void initFaceBook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final String accessToken = loginResult.getAccessToken().getToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Logger.d(tag, "facebook:\n" + object.toString());
                        MUtils.showSBar(binding.loginBt, object.toString(), Snackbar.LENGTH_INDEFINITE);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,first_name,gender,last_name");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                Logger.d(tag, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
            }
        });
    }

    private void initGoogle() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        binding.signInButton.setScopes(gso.getScopeArray());
        binding.signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });
    }

    public void facebookSignIN() {
        // 添加facebook访问权限
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
    }

    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void registrer() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.LOGIN_STEP_1);
        startActivity(intent);
    }

    public void forgotPwd() {
        Intent intent = new Intent(mContext, MultiActivity.class);
        intent.putExtra("TYPE", MultiActivity.FORGOT_PWD_1);
        startActivity(intent);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Logger.d(tag, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
            Logger.d(tag, "handleSignInResult:" + acct.getId() + "\n" + acct.getDisplayName() + "\n" + acct.getIdToken());
            MUtils.showSBar(binding.loginBt, acct.getId() + acct.getDisplayName(), Snackbar.LENGTH_INDEFINITE);
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
