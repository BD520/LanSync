package com.cjh.lansync.base;

import android.app.Application;

public class BaseApplication extends Application {
    public static boolean IS_DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
