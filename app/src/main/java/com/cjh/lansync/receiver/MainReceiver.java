package com.cjh.lansync.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cjh.lansync.ui.main.MainHandler;

public class MainReceiver extends BroadcastReceiver {
    MainHandler mHandler;

    MainReceiver() {
        super();
    }

    public MainReceiver(MainHandler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        System.out.println(action);
        switch (action) {
            case ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED:
                mHandler.sendEmptyMessage(MainHandler.WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED);
                break;
            case ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR:
                mHandler.sendEmptyMessage(MainHandler.WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR);
                break;
            case ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED:
                mHandler.sendEmptyMessage(MainHandler.WHAT_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED);
                break;
            default:
                break;
        }
    }
}
