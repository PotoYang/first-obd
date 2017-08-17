package com.chh.potoyang.first.presenter;

import android.content.Context;

import com.chh.potoyang.Token;
import com.chh.potoyang.User;
import com.chh.potoyang.config.UserData;
import com.chh.potoyang.first.view.LoginView;
import com.chh.potoyang.service.account.AccountService;
import com.chh.potoyang.service.account.AccountServiceImpl;
import com.chh.potoyang.service.http.HttpCallBack;

/**
 * Created by potoyang on 2017/8/7.
 */

public class LoginPresenter extends BasePresenter {
    private LoginView userView;
    private AccountService accountService;
    private Context context;

    public LoginPresenter(Context context, LoginView userView) {
        this.userView = userView;
        this.context = context;
        accountService = new AccountServiceImpl(context);
    }

    public void userLogin(String mobile, String password, boolean isRememberPwd) {
        userView.showLoadDataDialog("正在登录");
        HttpCallBack<Token> callBack = new HttpCallBack<Token>() {
            @Override
            public void onSuccess(Token token) {
                userView.hideLoadDataDialog();
                getUserInfo();
//                userView.loginSuccess();
            }

            @Override
            public void onError(int state, String message) {
                userView.hideLoadDataDialog();
                userView.loginError(message);
            }
        };
        accountService.login(mobile, password, isRememberPwd, callBack);
        this.objReferenceList.add(callBack);
    }

    private void getUserInfo() {
        String token = UserData.getToken(context);
        HttpCallBack<User> callBack = new HttpCallBack<User>() {
            @Override
            public void onSuccess(User data) {
                userView.loginSuccess(data);
            }

            @Override
            public void onError(int state, String message) {
//                userView.loginSuccess(data);
            }
        };
        accountService.getUserInfo(token, callBack);
    }
}
