package com.chh.potoyang.first.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.utils.TextUtils;
import com.chh.potoyang.User;
import com.chh.potoyang.config.ActivityURL;
import com.chh.potoyang.config.UserData;
import com.chh.potoyang.first.FirstApplication;
import com.chh.potoyang.first.R;
import com.chh.potoyang.first.presenter.LoginPresenter;
import com.chh.potoyang.first.util.TSnackbarUtils;
import com.chh.potoyang.first.view.LoginView;
import com.chh.potoyang.utils.AppManager;
import com.chh.potoyang.utils.ArouterUtils;
import com.chh.potoyang.utils.LogUtils;
import com.chh.potoyang.utils.SharedPreferencesUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by potoyang on 2017/8/7.
 */

@Route(path = ActivityURL.LoginActivity)
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.etLoginMobile)
    EditText etLoginMobile;
    @Bind(R.id.etLoginPassword)
    EditText etLoginPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.rememberPwd)
    CheckBox rememberPwd;

    private LoginPresenter userPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        showExitLayout(getApplicationContext());
        init();
    }

    private void init() {
        setToolBarLeftText(getString(R.string.exit));
        setToolbarTitleText(getString(R.string.login_title));
        setToolBarRightText(getString(R.string.login_register), ActivityURL.RegisterActivity);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null && !TextUtils.isEmpty(bundle.getString("msg"))) {
//            MyToast.show(getApplicationContext(), bundle.getString("msg"));
//        }
        String mobile = SharedPreferencesUtils.getDecryptValue(getApplicationContext(), UserData.USER_NAME);
        if (!TextUtils.isEmpty(mobile)) {
            etLoginMobile.setText(mobile);
        }
    }

    public void loginClick(View view) {
        userPresenter.userLogin(etLoginMobile.getText().toString().trim(), etLoginPassword.getText().toString().trim(), rememberPwd.isChecked());
    }

    public void registerClick(View view) {
        ArouterUtils.startActivity(ActivityURL.RegisterActivity);
    }

    public void findPwdClick(View view) {
        ArouterUtils.startActivity(ActivityURL.FindPwdActivity);
    }

    @OnClick(R.id.ivWechat)
    public void weChatClick() {
        if (!FirstApplication.iwxapi.isWXAppInstalled()) {
            TSnackbarUtils.showTSnackbar(btnLogin, "您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_first_test";
        FirstApplication.iwxapi.sendReq(req);
//        Final SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "wechat_sdk_demo_test";
//        api.sendReq(req);
    }

    @Override
    public void showLoadDataDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideLoadDataDialog() {
        hideDialog();
    }

    @Override
    public void loginSuccess(final User data) {
        if (data.getIdCard() == null || data.getCarNo() == null) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("登录成功，需进行信息绑定")
                    .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userData", data);
                            ArouterUtils.startActivity(bundle, ActivityURL.BinInfoActivity);
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ArouterUtils.startActivity(ActivityURL.MainActivity);
            finishWithAnim();
        }
        if ((123 & 112) == 0){}
//        ArrayList;
//        LinkedList;
//        TreeMap;
//        LinkedHashMap;
    }

    @Override
    public void loginError(String msg) {
        TSnackbarUtils.showTSnackbar(btnLogin, "登录失败:" + msg);
        LogUtils.i(TAG, "登录失败:" + msg);
    }

    @Override
    public LoginPresenter createPresenter() {
        userPresenter = new LoginPresenter(this, this);
        return userPresenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AppManager.getAppManager().finishAllActivity();
        return super.onKeyDown(keyCode, event);
    }
}
