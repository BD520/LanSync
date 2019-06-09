package com.cjh.lansync.ui.main;

import android.content.pm.PackageManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import com.cjh.lansync.base.IModel;

public interface IMainModel extends IModel {
    String getSelfAppVersion() throws PackageManager.NameNotFoundException;

    SpannableStringBuilder appendInfo(String info);

    SpannableStringBuilder appendDebug(String debug);

    SpannableStringBuilder appendWarning(String warning);

    SpannableStringBuilder appendError(String error);

    String getAppName();

    String getWelcome();

    String getManual();

    SpannableStringBuilder getLog();

    void initDataBase() throws Exception;
}
