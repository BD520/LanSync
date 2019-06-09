package com.cjh.lansync.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

public interface IPresenter {
    void onCreate(Bundle savedInstance);

    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    boolean onOptionsItemSelected(MenuItem item);

    void onConfigurationChanged(Configuration newConfig);
}
