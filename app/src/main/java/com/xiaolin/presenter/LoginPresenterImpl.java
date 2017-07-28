package com.xiaolin.presenter;

import android.content.Context;
import android.content.Intent;

import com.xiaolin.app.Constants;
import com.xiaolin.bean.LoginBean;
import com.xiaolin.model.LoginModelImpl;
import com.xiaolin.model.imodel.ILoginModel;
import com.xiaolin.model.listener.OnLoginListener;
import com.xiaolin.presenter.ipresenter.ILoginPresenter;
import com.xiaolin.ui.LoginActivity;
import com.xiaolin.ui.MainActivity;
import com.xiaolin.ui.iview.ILoginView;
import com.xiaolin.utils.DebugUtil;
import com.xiaolin.utils.SPUtils;

/**
 * prenter层，负责与act对接
 * 负责数据传递
 */

public class LoginPresenterImpl implements ILoginPresenter, OnLoginListener {
    private static final String TAG = "login";

    private Context context;
    private ILoginView iLoginView;
    private ILoginModel iLoginModel;


    public LoginPresenterImpl(Context context, ILoginView iLoginView) {
        this.context = context;
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }

    // ILoginPresenter接口
    @Override
    public void pLogin(String storeName, String userName, String password, String IP) {
        iLoginView.showProgress();
        //model获取数据,由OnLoginListener接口回调处理结果
        iLoginModel.mLogin(storeName, userName, password, IP, this);
    }

    //OnLoginListener接口
    @Override
    public void onLoginSuccess(LoginBean bean) {
        iLoginView.hideProgress();

        //将返回数据保存
        SPUtils.putString(Constants.STORE_ID, bean.getStoreId());
        SPUtils.putString(Constants.STORE_USER_ID, bean.getStoreUserId());
        SPUtils.putString(Constants.EMPLOYEE_ID, bean.getEmployeeId());

        SPUtils.putString(Constants.TODAY_DATE, bean.getTodayDate());
        SPUtils.putString(Constants.DEVICEID, bean.getDeviceId());
        SPUtils.putString(Constants.EMPLOYEENAME, bean.getEmployeeName());

        SPUtils.putString(Constants.FIRSTATTEND, bean.getFirstAttend());
        SPUtils.putString(Constants.LASTATTEND, bean.getLastAttend());
        SPUtils.putString(Constants.YESTODY_DATE, bean.getYesterdayState());
        DebugUtil.d(TAG, "LoginPresenterImpl--缓存数据");
        //界面跳转
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((LoginActivity) context).finish();
    }

    //OnLoginListener接口，处理登陆失败
    @Override
    public void onLoginFailed(String msg, Exception e) {
        iLoginView.hideProgress();
        iLoginView.showDialogFailed(msg, e);
    }
}
