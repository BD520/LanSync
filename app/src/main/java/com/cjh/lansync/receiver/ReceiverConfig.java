package com.cjh.lansync.receiver;

public interface ReceiverConfig {
    String permission = "com.android.permission.RECV_LS_BROADCAST";
    /**
     * 本地数据同步服务请求联系人失败,cursor为null
     */
    String ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_ERROR = "com.cjh.lansync.100";
    /**
     * 本地数据同步服务开始请求联系人
     */
    String ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_STARTED = "com.cjh.lansync.101";
    /**
     * 本地数据同步服务请求联系人完成
     */
    String ACTION_LOCAL_SYNC_SERVICE_CONTACTS_QUERY_FINISHED = "com.cjh.lansync.102";
}
