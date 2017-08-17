package com.chh.potoyang.first.view;

import com.chh.potoyang.User;

/**
 * Created by potoyang on 2017/8/7.
 */

public interface LoginView extends IView {

    void loginSuccess(User data);

    void loginError(String msg);
}
