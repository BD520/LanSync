package com.cjh.lansync.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.cjh.lansync.R;
import com.cjh.lansync.base.BasePresenter;
import com.cjh.lansync.db.DBConfig;
import com.cjh.lansync.receiver.MainReceiver;
import com.cjh.lansync.receiver.ReceiverConfig;
import com.cjh.lansync.services.LocalSyncService;

import java.io.File;

class MainPresenter extends BasePresenter implements IMainPresenter {
    private final int STEP1_CHECK_PERMISSION = 0;
    private final int STEP2_REQUEST_PERMISSION = 1;
    private final int STEP3_INIT_DATABASE = 2;
    private final int STEP4_START_LOCAL_SYNC_SERVICE = 3;
    private MainHandler mHandler;

    private IMainView mView;
    private IMainModel mModel;
    private int step = 0;
    private String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private int[] permissionPrompts = {
            R.string.phone_state_not_granted,
            R.string.contacts_not_granted,
            R.string.contacts_not_granted,
            R.string.sms_not_granted,
            R.string.sdcard_not_granted,
            R.string.sdcard_not_granted};

    private MainReceiver mReceiver;

    MainPresenter(IMainView view) {
        super(view);
        mView = view;
        mModel = new MainModel(view, mContext);
        mHandler = new MainHandler(this);
        //注册广播接收器

    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        try {
            mModel.appendInfo(mModel.getAppName() + " " + mModel.getSelfAppVersion());
        } catch (PackageManager.NameNotFoundException e) {
            mModel.appendWarning(mModel.getAppName() + " Version unknow");
            mModel.appendError(e.toString());
        }
        mModel.appendWarning(mModel.getWelcome());
        mModel.appendInfo(mModel.getManual());
        mView.log(mModel.getLog());
        IntentFilter filter = new IntentFilter();
        // TODO: 2019/6/9 添加更多广播动作
        filter.addAction(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED);
        filter.addAction(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED);
        filter.addAction(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR);
        mReceiver = new MainReceiver(mHandler);
        mContext.registerReceiver(mReceiver, filter,ReceiverConfig.permission,null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_main_log:
                doNextStep();
                break;
            default:
                break;
        }
    }

    @Override
    public void showInfo(String info) {
        mView.log(mModel.appendInfo(info));
    }

    @Override
    public void showInfo(int strId) {
        mView.log(mModel.appendInfo(getString(strId)));
    }

    @Override
    public void showDebug(String debug) {
        mView.log(mModel.appendDebug(debug));
    }

    @Override
    public void showDebug(int strId) {
        mView.log(mModel.appendDebug(getString(strId)));
    }

    @Override
    public void showWarning(String warning) {
        mView.log(mModel.appendWarning(warning));
    }

    @Override
    public void showWarning(int strId) {
        mView.log(mModel.appendWarning(getString(strId)));
    }

    @Override
    public void showError(String error) {
        mView.log(mModel.appendError(error));
    }

    @Override
    public void showError(int strId) {
        mView.log(mModel.appendError(getString(strId)));
    }

    private void doNextStep() {
        switch (step) {
            //步骤1,检测权限
            case STEP1_CHECK_PERMISSION:
                checkMyPermission();
                break;
            //步骤2，请求权限
            case STEP2_REQUEST_PERMISSION:
                requestMyPermission();
                break;
            //步骤3,创建或打开数据库
            case STEP3_INIT_DATABASE:
                initDataBase();
                break;
            //启动本地数据同步服务，同步本地数据到数据库
            case STEP4_START_LOCAL_SYNC_SERVICE:
                startLocalSyncService();
                break;
            default:
                mView.log(mModel.appendDebug("step:" + step));
                break;
        }
    }

    /**
     * 启动本地数据同步服务.对比数据并同步到本地数据库。
     */
    private void startLocalSyncService() {
        mView.log(mModel.appendInfo(getString(R.string.start_local_database_sync_service)));
        mContext.startService(new Intent(mContext, LocalSyncService.class));
    }

    private void initDataBase() {
        mView.log(mModel.appendInfo(getString(R.string.init_database)));
        try {
            mModel.initDataBase();
            step = STEP4_START_LOCAL_SYNC_SERVICE;
        } catch (Exception e) {
            e.printStackTrace();
            mView.log(mModel.appendError(e.toString()));
        }
    }

    private void requestMyPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mView.log(mModel.appendDebug("尝试请求权限"));
            mView.getActivity().requestPermissions(permissions, 0);
            step = STEP1_CHECK_PERMISSION;
        } else {
            step = STEP3_INIT_DATABASE;
        }
    }

    private void checkMyPermission() {
        mView.log(mModel.appendInfo(getString(R.string.step1_check_permission)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            step = STEP3_INIT_DATABASE;
            for (int i = 0; i < permissions.length; i++) {
                int granted = mContext.checkSelfPermission(permissions[i]);
                if (granted == PackageManager.PERMISSION_DENIED) {
                    mModel.appendWarning(getString(permissionPrompts[i]));
                    step = STEP2_REQUEST_PERMISSION;
                }
            }
        } else {
            mModel.appendWarning(getString(R.string.lower_than_m));
        }
        mModel.appendInfo(getString(R.string.click_to_next_step));
        mView.log(mModel.getLog());
    }

    private String getString(int id) {
        return mContext.getString(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(mReceiver);
    }
}
