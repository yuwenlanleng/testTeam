/*
 * ModifyLog.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-09 14:35:54
 */
package com.nazca.inside.guide.common.jpa;

import com.nazca.usm.model.USMSUser;
import com.nazca.inside.guide.common.enums.ModifyLogOperateType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
/**
 * 修改日志表（系统）
 *
 * @author Lijin
 */
public class ModifyLog implements Serializable {
    private static final long serialVersionUID = -4783489210435665279L;
    /**
     * id
     */
    private String id;
    /**
     * 模块id
     */
    private String moduleId;
    /**
     * 子模块id
     */
    private String subModuleId;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作类型
     */
    private ModifyLogOperateType operateType;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 操作人
     */
    private USMSUser operator;
    /**
     * 操作人ip
     */
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(String subModuleId) {
        this.subModuleId = subModuleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModifyLogOperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(ModifyLogOperateType operateType) {
        this.operateType = operateType;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public USMSUser getOperator() {
        return operator;
    }

    public void setOperator(USMSUser operator) {
        this.operator = operator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModifyLog other = (ModifyLog) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   
}
