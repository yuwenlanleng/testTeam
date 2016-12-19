/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-04-06 12:18:06
 */
package com.nazca.inside.guide.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务器端返回的同步结果
 * @author fred
 */
public class ServerSyncQuery implements Serializable {
    private static final long serialVersionUID = -8564685338199485129L;
    private Class claz;
    /**
     * 最近修改时间
     */
    private Date modifyTime;
    /**
     * 开始索引
     */
    private int startIdx = 0;

    public Class getClaz() {
        return claz;
    }

    public void setClaz(Class claz) {
        this.claz = claz;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }
}
