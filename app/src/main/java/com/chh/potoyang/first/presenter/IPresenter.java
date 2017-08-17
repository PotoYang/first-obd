package com.chh.potoyang.first.presenter;

import com.chh.potoyang.first.view.IView;

/**
 * Created by potoyang on 2017/8/7.
 */

public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detachView(boolean retainInstance);

}
