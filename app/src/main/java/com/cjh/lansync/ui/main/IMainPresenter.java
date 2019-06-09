package com.cjh.lansync.ui.main;

import android.view.View;

import com.cjh.lansync.base.IPresenter;

public interface IMainPresenter extends IPresenter, View.OnClickListener {

    @Override
    void onClick(View v);

    void showInfo(String info);

    void showInfo(int strId);

    void showDebug(String debug);

    void showDebug(int strId);

    void showWarning(String warning);

    void showWarning(int strId);

    void showError(String error);

    void showError(int strId);
}
