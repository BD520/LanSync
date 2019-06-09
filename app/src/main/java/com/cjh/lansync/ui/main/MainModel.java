package com.cjh.lansync.ui.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.cjh.lansync.R;
import com.cjh.lansync.base.BaseApplication;
import com.cjh.lansync.base.BaseModel;
import com.cjh.lansync.db.DBConfig;
import com.cjh.lansync.db.LocalSQLiteOpenHelper;

public class MainModel extends BaseModel implements IMainModel {
    private IMainView mView;
    SpannableStringBuilder mSpannableBuilder;
    private int colorInfo;
    private int colorDebug;
    private int colorWarning;
    private int colorError;
    private LocalSQLiteOpenHelper mSqLiteOpenHelper;

    MainModel(IMainView view, Context context) {
        super(view, context);
        mView = view;

        colorInfo = context.getResources().getColor(R.color.log_info);
        colorDebug = context.getResources().getColor(R.color.log_debug);
        colorWarning = context.getResources().getColor(R.color.log_warning);
        colorError = context.getResources().getColor(R.color.log_error);

        mSpannableBuilder = new SpannableStringBuilder();
        mSpannableBuilder.append(getArrow());
    }

    private SpannableString getArrow() {
        SpannableString arrow = new SpannableString(">>  ");
        arrow.setSpan(new ForegroundColorSpan(colorInfo), 0, arrow.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return arrow;
    }

    private SpannableString getSpannableString(String text, int color) {
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new ForegroundColorSpan(color),
                0, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return ss;
    }

    @Override
    public String getSelfAppVersion() throws PackageManager.NameNotFoundException {
        return "V" + mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
    }


    @Override
    public SpannableStringBuilder appendInfo(String info) {
        mSpannableBuilder.append(getSpannableString(info, colorInfo)).append("\n").append(getArrow());
        return mSpannableBuilder;
    }

    @Override
    public SpannableStringBuilder appendDebug(String debug) {
        if (BaseApplication.IS_DEBUG)
            mSpannableBuilder.append(getSpannableString(debug, colorDebug)).append("\n").append(getArrow());
        return mSpannableBuilder;
    }

    @Override
    public SpannableStringBuilder appendWarning(String warning) {
        mSpannableBuilder.append(getSpannableString(warning, colorWarning)).append("\n").append(getArrow());
        return mSpannableBuilder;
    }

    @Override
    public SpannableStringBuilder appendError(String error) {
        mSpannableBuilder.append(getSpannableString(error, colorError)).append("\n").append(getArrow());
        return mSpannableBuilder;
    }


    @Override
    public String getAppName() {
        return mContext.getResources().getString(R.string.app_name);
    }

    @Override
    public String getWelcome() {
        return mContext.getResources().getString(R.string.welcome);
    }

    @Override
    public String getManual() {
        return mContext.getResources().getString(R.string.manual);
    }

    @Override
    public SpannableStringBuilder getLog() {
        return mSpannableBuilder;
    }

    @Override
    public void initDataBase() throws Exception{
            mSqLiteOpenHelper = new LocalSQLiteOpenHelper(mContext, DBConfig.DBName, DBConfig.VERSION);
            SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
            db.close();
    }

}
