package com.chh.yinbao.service.account;

import com.chh.yinbao.Token;
import com.chh.yinbao.User;
import com.chh.yinbao.service.http.HttpCallBack;

/**
 * Created by potoyang on 2017/8/7.
 */

public interface AccountService {
    //登录
    void login(String mobile, String password, boolean isRememberPwd, HttpCallBack<Token> callBack);

    void loginOut(HttpCallBack<String> callBack);

    //发送注册短信
    void sendRegisterSmsCode(String mobile, HttpCallBack<Object> callBack);

    //注册
//    void register(String mobile, String checkCode, String userPwd, String userPwdAgain, HttpCallBack<Object> callBack);
    void register(String mobile, String password1, String password2, HttpCallBack<Object> callBack);

    //找回密码
    void sendFindPwdSmsCode(String mobile, HttpCallBack<Object> callBack);

    //确认短信验证码
    void verifyFindPwdSmsCode(String userName, String checkCode, HttpCallBack<Object> callBack);

    //通过短信重置密码
    void resetPwd(String userName, String newPwd, String newPwdAgain, String smsCode, HttpCallBack<Object> callBack);

    //登录成功后重置密码
    void updatePwd(String oldPwd, String newPwd, String newPwdAgain, HttpCallBack<Object> callBack);

    void getUserInfo(String token, HttpCallBack<User> callBack);

    //绑定用户信息
    void bindInfo(User user, String name, String idCard, String carNo, String token, HttpCallBack<Object> callBack);
}
