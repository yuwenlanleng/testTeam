/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-04-06 12:18:06
 */
package com.nazca.inside.guide.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务器端返回的同步结果
 *
 * @author fred
 */
public class ServerSyncResult implements Serializable {
    private static final long serialVersionUID = -6053557151195774870L;
    private Class claz;
    /**
     * 本次同步的数据
     */
    private List<Object> objList = new ArrayList<Object>(0);
    /**
     * 服务器端剩余的未同步的数量
     */
    private int availableCount = 0;
    /**
     * 最后数据的索引值
     */
    private int lastDataIdx = 0;
    /**
     * 最新修改时间
     */
    private Date maxModifyTime = null;

    public Date getMaxModifyTime() {
        return maxModifyTime;
    }

    public void setMaxModifyTime(Date maxModifyTime) {
        this.maxModifyTime = maxModifyTime;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public Class getClaz() {
        return claz;
    }

    public void setClaz(Class claz) {
        this.claz = claz;
    }

    public List<Object> getObjList() {
        return objList;
    }

    public void setObjList(List<Object> objList) {
        this.objList = objList;
    }

    public int getLastDataIdx() {
        return lastDataIdx;
    }

    public void setLastDataIdx(int lastDataIdx) {
        this.lastDataIdx = lastDataIdx;
    }
}
