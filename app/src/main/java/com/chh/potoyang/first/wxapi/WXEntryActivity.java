package com.chh.potoyang.first.wxapi;

import android.os.Bundle;

import com.chh.potoyang.first.FirstApplication;
import com.chh.potoyang.first.R;
import com.chh.potoyang.first.activity.BaseActivity;
import com.chh.potoyang.first.util.MyToast;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by potoyang on 2017/8/17.
 */

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirstApplication.iwxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        MyToast.show(getApplicationContext(), resp.errCode);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.code_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.code_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.code_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.code_unsupported;
                break;
            default:
                result = R.string.code_unknown;
                break;
        }

        MyToast.show(getApplicationContext(), result);
    }
}
