package com.cjh.lansync.base;

import android.app.Activity;
import android.content.Context;

public interface IView {
    void initView();

    Context getContext();

    Activity getActivity();
}
