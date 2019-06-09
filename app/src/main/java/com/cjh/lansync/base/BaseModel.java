package com.cjh.lansync.base;

import android.content.Context;

public class BaseModel implements IModel {
    protected Context mContext;
    private IView mView;

    protected BaseModel(IView view, Context context) {
        this.mView = view;
        this.mContext = context;
    }
}
