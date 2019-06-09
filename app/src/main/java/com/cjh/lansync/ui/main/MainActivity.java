package com.cjh.lansync.ui.main;

import android.text.SpannableStringBuilder;
import android.widget.EditText;

import com.cjh.lansync.R;
import com.cjh.lansync.base.BaseActivity;
import com.cjh.lansync.base.IPresenter;

public class MainActivity extends BaseActivity implements IMainView {

    private EditText mEtLog;
    private IMainPresenter mPresenter;

    @Override
    protected IPresenter getPresenter() {
        mPresenter = new MainPresenter(this);
        return mPresenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mEtLog = findViewById(R.id.et_main_log);
        mEtLog.setOnClickListener(mPresenter);
    }


    @Override
    public void log(SpannableStringBuilder info) {
        mEtLog.setText(info);
        mEtLog.setSelection(info.length());
        mEtLog.requestFocus();
    }
}
