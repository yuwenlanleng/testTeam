/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-29 09:44:33
 */
package com.nazca.inside.guide.client.listener;

import java.util.EventListener;

/**
 * 用于同步的listener
 * @author liqin
 */
public interface SyncServerListener extends EventListener {
    /**
     * 开始同步
     */
    public void onSyncStart();

    /**
     * 同步完成
     * @param isReallySynced 是否真正发生了同步
     */
    public void onSyncDone(boolean isReallySynced);

    /**
     * 同步失败
     */
    public void onSyncFailed(int errorCode, String msg);
}
