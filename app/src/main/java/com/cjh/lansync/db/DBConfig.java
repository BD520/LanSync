package com.cjh.lansync.db;

public interface DBConfig {
    String DBName = "data.db3";
    int VERSION = 1;
    String TABLE_CONTACTS = "contacts";
    String TABLE_SMS = "sms";
    String TABLE_APPS = "apps";
    String TABLE_DCIM = "dcim";
    String TABLE_MEDIA = "media";
    // TODO: 2019/6/9  创建电话号表sql语句
    String CREATE_TABLE_CONTACTS = "create table " + ContactsTable.TABLE_NAME + "(" + ContactsTable.COLUMN_ID + " integer primary key autoincrement ," +
            ContactsTable.COLUMN_NAME + " text ," + ContactsTable.COLUMN_FIRST_NAME + " text ," + ContactsTable.COLUMN_MIDDLE_NAME + " text ," +
            ContactsTable.COLUMN_LAST_NAME + " text ," + ContactsTable.COLUMN_PHONE1 + " text ," + ContactsTable.COLUMN_PHONE2 + " text ," +
            ContactsTable.COLUMN_PHONE3 + " text ," + ContactsTable.COLUMN_PHONE4 + " text)";

    String CREATE_TABLE_SMS = "create table " + SmsTable.TABLE_NAME + "(" + SmsTable.COLUMN_ID + " integer primary key autoincrement ," +
            SmsTable.COLUMN_TYPE + " integer ," + SmsTable.COLUMN_PHONE_NUMBER + " text ," + SmsTable.COLUMN_CONTENT + " text ," +
            SmsTable.COLUMN_TIME + " text)";

    String CREATE_TABLE_APPS = "create table " + AppsTable.TABLE_NAME + "(" +
            AppsTable.COLUMN_ID + " integer primary key autoincrement , " +
            AppsTable.COLUMN_PACKAGE_NAME + " text ," +
            AppsTable.COLUMN_APP_NAME + " text ," +
            AppsTable.COLUMN_APP_VERSION + " text)";

    String CREATE_TABLE_DCIM = "create table " + DcimTable.TABLE_NAME + "(" +
            DcimTable.COLUMN_ID + " integer primary key autoincrement , " +
            DcimTable.COLUMN_NAME + " text ," +
            DcimTable.COLUMN_PATH + " text ," +
            DcimTable.COLUMN_MD5 + " text ," +
            DcimTable.COLUMN_SIZE + " text)";

    String CREATE_TABLE_MEDIA = "create table " + MediaTable.TABLE_NAME + "(" +
            MediaTable.COLUMN_ID + " integer primary key autoincrement , " +
            MediaTable.COLUMN_NAME + " text ," +
            MediaTable.COLUMN_PATH + " text ," +
            MediaTable.COLUMN_MD5 + " text ," +
            MediaTable.COLUMN_SIZE + " text)";

}
