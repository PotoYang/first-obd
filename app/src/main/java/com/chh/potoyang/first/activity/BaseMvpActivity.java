package com.chh.potoyang.first.activity;

import android.os.Bundle;

import com.chh.potoyang.first.presenter.BasePresenter;

/**
 * Created by potoyang on 2017/8/7.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        presenter.attachView(null);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView(false);
        super.onDestroy();
    }

    public abstract P createPresenter();
}
