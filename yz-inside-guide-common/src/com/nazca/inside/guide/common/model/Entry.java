/*
 * Entry.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 15:46:03
 */
package com.nazca.inside.guide.common.model;

import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author pengruirui
 */
public class Entry implements Serializable {
    private static final long serialVersionUID = -9071112265609263200L;
    /**
     * 确定唯一性的ID
     */
    private String id;    
    
     /**
     * 确定图片
     */
    private String uploadPicture;   

    public String getUploadPicture() {
        return uploadPicture;
    }

    public void setUploadPicture(String uploadPicture) {
        this.uploadPicture = uploadPicture;
    }
    
 
        /**
     * 确定修改页面的sort
     */
    private String changeSort;

    public String getChangeSort() {
        return changeSort;
    }

    public void setChangeSort(String changeSort) {
        this.changeSort = changeSort;
    }

    /**
     * 向导类型
     */
    private EntryTypeEnum guideType;
        
    public EntryTypeEnum getGuideType() {
        return guideType;
    }

    public void setGuideType(EntryTypeEnum guideType) {
        this.guideType = guideType;
    }

    /**
     * 网站名称、工具名称或者项目名称
     */
    private String name;
    /**
     * 项目描述
     */
    private String des;
    /**
     * 内网入口URL
     */
    private String innerUrl;
    /**
     * 外网入口URL
     */
    private String outerUrl;
    /**
     * 苹果系统平台下载URL
     */
    private String downloadUrlForIos;
    /**
     * 安卓系统平台下载URL
     */
    private String downloadUrlForAndroid;
    /**
     * 电脑端下载URL
     */
    private String downloadUrlForPc;
    /**
     * 用于排序的字段
     */
    private double sortOrder;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getInnerUrl() {
        return innerUrl;
    }

    public void setInnerUrl(String innerUrl) {
        this.innerUrl = innerUrl;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public String getDownloadUrlForIos() {
        return downloadUrlForIos;
    }

    public void setDownloadUrlForIos(String downloadUrlForIos) {
        this.downloadUrlForIos = downloadUrlForIos;
    }

    public String getDownloadUrlForAndroid() {
        return downloadUrlForAndroid;
    }

    public void setDownloadUrlForAndroid(String downloadUrlForAndroid) {
        this.downloadUrlForAndroid = downloadUrlForAndroid;
    }

    public String getDownloadUrlForPc() {
        return downloadUrlForPc;
    }

    public void setDownloadUrlForPc(String downloadUrlForPc) {
        this.downloadUrlForPc = downloadUrlForPc;
    }

    public double getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(double sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entry{" + "id=" + id + ", guideType=" + guideType + ", name="
                + name + ", des=" + des + ", innerUrl=" + innerUrl
                + ", outerUrl=" + outerUrl + ", downloadUrlForIos="
                + downloadUrlForIos + ", downloadUrlForAndroid="
                + downloadUrlForAndroid + ", downloadUrlForPc="
                + downloadUrlForPc + ", sortOrder=" + sortOrder + '}';
    }

}
