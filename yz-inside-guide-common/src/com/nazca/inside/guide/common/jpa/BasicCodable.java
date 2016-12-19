/*
 * BasicCodable.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-19 18:06:12
 */
package com.nazca.inside.guide.common.jpa;

import com.nazca.inside.guide.common.enums.RecordState;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础代码
 *
 * @author Zhang Chun Nan
 */
public interface BasicCodable extends Serializable {
    public String getCode();

    public void setCode(String code);

    public String getName();

    public void setName(String name);

    public RecordState getRecordState();

    public void setRecordState(RecordState recordState);

    public Date getCreateTime();

    public void setCreateTime(Date createTime);

    public String getCreater();

    public void setCreater(String creater);

    public Date getModifyTime();

    public void setModifyTime(Date modifyTime);

    public String getModifier();

    public void setModifier(String modifier);
    
    //-------Major-------
    public String getLevelCode();

    public void setLevelCode(String levelCode);
  
}
