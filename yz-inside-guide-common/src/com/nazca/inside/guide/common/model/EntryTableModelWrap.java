/*
 * EntryTableModelWrap.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 15:10:42
 */
package com.nazca.inside.guide.common.model;

import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import java.io.Serializable;
import java.util.Comparator;

/**
 *入口管理分装类
 * @author pengruirui
 */
public class EntryTableModelWrap implements Serializable, Comparator<EntryTableModelWrap>{
    private static final long serialVersionUID = 2775686074631539393L;
    
    /**
     * 确定唯一性的ID
     */
    private String id;
    /**
     * 向导类型
     */
   private EntryTypeEnum guideType;
    /**
     * 数量
     */
   private String count;

  
    public EntryTypeEnum getGuideType() {
        return guideType;
    }

    public void setGuideType(EntryTypeEnum guideType) {
        this.guideType = guideType;
    }



    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public int compare(EntryTableModelWrap o1, EntryTableModelWrap o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

   
    
  


    
}
