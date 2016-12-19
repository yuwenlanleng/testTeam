/*
 * EntryManageTableConsts.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 16:35:53
 */
package com.nazca.inside.guide.common.table.consts;

/**
 *
 * @author pengruirui
 */
public enum EntryManageTableConsts {
     TABLE_NAME("guideitem",0),
     
      /**
      * 修改者
      */
     MODIFIER("modifier",128),
     
      /**
      * 上传图片
      */
    UPLOAD_PICTURE("upload_picture",128),
      /**
      * 修改时间
      */
     MODIFY_TIME("modify_time",0),
     
      /**
      * 创建者
      */
     CREATOR("creator",128),
     
      /**
      * 创建时间
      */
     CREATE_TIME("create_time",0),
     

     /**
      * 向导类型
      */
     GUIDE_TYPE("guide_type",64),
     /**
      * 用于排序的字段
      */
     SORT_ORDER ("sort_order",0),
     /**
      * 确定唯一性的ID
      */
     ID("id",64),
     /**
      * 网站名称、工具名称或者项目名称
      */
     NAME("name",64),
     /**
      * 项目描述
      */
     DES("des",128),
     /**
      * 内网入口URL
      */
     INNER_URL ("inner_url",128),
     /**
      * 外网入口URL
      */
     OUTER_URL ("outer_url",128),
     /**
      * 苹果系统平台下载URL
      */
    DOWNLOAD_URL_FORIOS("download_url_forios",128),
     /**
      * 安卓系统平台下载URL
      */
    DOWNLOAD_URL_FORANDROID ("download_url_forandroid",128),
     /**
      * 电脑端下载URL
      */
    DOWNLOAD_URL_FORPC("download_url_forpc",128);

    private String name;
    private int length;

    private EntryManageTableConsts(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }


    public int getLength() {
        return length;
    }

}
