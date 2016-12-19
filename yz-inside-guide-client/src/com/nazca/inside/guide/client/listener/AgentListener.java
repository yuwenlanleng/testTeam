/*
 * Copyright(c) 2007-2010 by Yingzhi Tech.
 * All Rights Reserved
 */
package com.nazca.inside.guide.client.listener;

/**
 * 公共的AgentListener
 * @param <T> 
 * @author ijay
 */
public interface AgentListener<T> {
    void onStarted(long seq);

    void onSucceeded(T result, long seq);

    void onFailed(String errMsg, int errCode, long seq);
}
