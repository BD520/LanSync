package com.cjh.lansync.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

public class BasePresenter implements IPresenter {
    private IView mView;
    private IModel mModel;
    protected Context mContext;
    protected Activity mActivity;

    protected BasePresenter(IView view) {
        this.mView = view;
        this.mModel = new BaseModel(mView, mView.getContext());
        this.mContext = view.getContext();
        this.mActivity = view.getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        mView.initView();
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }
}
