package com.cjh.lansync.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cjh.lansync.db.DBConfig;
import com.cjh.lansync.db.LocalSQLiteOpenHelper;
import com.cjh.lansync.receiver.ReceiverConfig;


/**
 * 本地数据同步服务,对比本地数据库并更新
 */
public class LocalSyncService extends IntentService {

    LocalSyncService() {
        super(LocalSyncService.class.getSimpleName());
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public LocalSyncService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打开数据库
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(this, DBConfig.DBName, DBConfig.VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        //读取系统联系人,判断是否在数据库中存在
        updateContacts(db);
        //读取系统短信
        //读取APP目录
        //读取DCIM目录
        //读取Media uri
    }

    private void updateContacts(SQLiteDatabase db) {
        System.out.println("更新联系人");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            sendBroadcast(new Intent(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED), ReceiverConfig.permission);
            while (cursor.moveToNext()) {
                for (String name : cursor.getColumnNames()) {
                    // TODO: 2019/6/9 保存联系人数据
//                    Log.d(LocalSyncService.class.getSimpleName(), name);
                }
            }
            cursor.close();
            sendBroadcast(new Intent(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED), ReceiverConfig.permission);
        } else {
            sendBroadcast(new Intent(ReceiverConfig.ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR), ReceiverConfig.permission);
        }
        db.close();

    }

}
