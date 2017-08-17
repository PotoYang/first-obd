package com.chh.potoyang.first.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.chh.potoyang.Token;
import com.chh.potoyang.config.ActivityURL;
import com.chh.potoyang.config.UserData;
import com.chh.potoyang.service.account.AccountService;
import com.chh.potoyang.service.account.AccountServiceImpl;
import com.chh.potoyang.service.http.HttpCallBack;
import com.chh.potoyang.utils.ArouterUtils;
import com.chh.potoyang.utils.SharedPreferencesUtils;

import butterknife.ButterKnife;

/**
 * Created by potoyang on 2017/8/10.
 */

public class LauncherActivity extends BaseActivity {
    private final String TAG = LauncherActivity.class.getSimpleName();
    AccountService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        init();
    }

    private void init() {
        userService = new AccountServiceImpl(getApplicationContext());
        UserData.cleanAppData(getApplicationContext());
        initApp();
    }

    private void initApp() {
//        requestCheckAppVersion();
        login();
    }

    private void login() {
        String username = SharedPreferencesUtils.getDecryptValue(getApplicationContext(), UserData.USER_NAME);
        String password = SharedPreferencesUtils.getDecryptValue(getApplicationContext(), UserData.USER_PWD);
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            user_login(username, password);
        } else {
//            toLoginActivity();
            ArouterUtils.startActivity(ActivityURL.LoginActivity);
        }
    }

    private void user_login(String username, String password) {
        HttpCallBack<Token> callBack = new HttpCallBack<Token>() {
            @Override
            public void onSuccess(Token data) {
                toHomeActivity();
            }

            @Override
            public void onError(int state, String message) {
                ArouterUtils.startActivity(ActivityURL.LoginActivity);
            }
        };
        userService.login(username, password, true, callBack);
    }

    private void toHomeActivity() {
        ArouterUtils.startActivity(ActivityURL.MainActivity);
    }
}
