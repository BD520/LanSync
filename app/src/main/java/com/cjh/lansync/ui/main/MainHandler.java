package com.cjh.lansync.ui.main;

import android.os.Handler;
import android.os.Message;

import com.cjh.lansync.R;

public class MainHandler extends Handler {
    private IMainPresenter mPresenter;

    public static final int WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED = 100;
    public static final int WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR = 101;
    public static final int WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED = 102;


    MainHandler(IMainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED:
                mPresenter.showInfo(R.string.local_contacts_sync_started);
                break;
            case WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED:
                mPresenter.showInfo(R.string.local_contacts_sync_finished);
                break;
            case WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR:
                mPresenter.showInfo(R.string.local_contacts_sync_error);
                break;
            default:
                break;
        }
    }
}
