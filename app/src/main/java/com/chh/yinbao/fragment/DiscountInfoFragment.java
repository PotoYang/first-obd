package com.chh.yinbao.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chh.potoyang.config.ActivityURL;
import com.chh.potoyang.config.UserData;
import com.chh.yinbao.R;
import com.chh.yinbao.util.MyToast;
import com.chh.potoyang.utils.ArouterUtils;
import com.chh.potoyang.utils.SharedPreferencesUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by potoyang on 2017/8/9.
 */

public class DiscountInfoFragment extends BaseFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_discount_info, container, false);
            ButterKnife.bind(this, rootView);
            init();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void init() {

    }

    @OnClick(R.id.ivCall)
    public void ivCallClick() {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "18328595452"));
        startActivity(phoneIntent);
    }

    @OnClick(R.id.ivWeb)
    public void ivWebClick() {
        ArouterUtils.startActivity(ActivityURL.WebExtendActivity);
    }

    @OnClick(R.id.btnLogout)
    public void btnLogoutClick() {
        SharedPreferencesUtils.removeValue(getContext(), UserData.USER_PWD);
        MyToast.show(getContext(), "注销成功");
        ArouterUtils.startActivity(ActivityURL.LoginActivity);
    }
}
