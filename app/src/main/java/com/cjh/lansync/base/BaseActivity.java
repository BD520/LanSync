package com.cjh.lansync.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cjh.lansync.R;

/**
 * 流程：
 * 1.APP启动，检测权限，请求权限
 * 2.检测是否为首次运行
 * 3.启动数据更新服务（创建打开数据库 & 更新通讯录，短信，APP信息，图片，音乐）并更新UI
 * 5.注册接收器，监听通讯录修改，收到的短信，发出的短信，包安装，卸载，更新
 * 4.启动同步服务（检测网络状态，创建ServerSocket 12890,定时发送UDP上线广播）并更新UI
 * 5.总有一个是先上线的，先上线的ServerSocket处于监听状态并接收广播
 * 6.收到广播的客户端检测是否已连接并主动建立Socket连接到发送广播的客户端 更新UI显示服务器上线
 * 7.客户端连接到服务器通过OutputStream后发送同步指令 更新UI显示同步指令
 * 8.服务器端接收到客户端后等候InputStream输入指令  更新UI显示指令和状态
 * 9.服务器段处理接收到的指令并通过客户端的OutputStream 响应处理结果 更新UI显示状态
 * <p>
 * 功能1:同步通讯录
 * 功能2:同步短信
 * 功能3:同步APP
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {
    private IPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = getPresenter();
        //BasePresenter调用initView();
        if (mPresenter != null) mPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPresenter != null) mPresenter.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mPresenter != null) return mPresenter.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mPresenter != null) mPresenter.onConfigurationChanged(newConfig);
    }

    protected IPresenter getPresenter() {
        return new BasePresenter(this);
    }

    protected int getLayoutResId() {
        return R.layout.activity_base;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
