/*
 * GuideItemConst.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 14:48:13
 */
package com.nazca.inside.guide.consts;

/**
 * 向导项目表
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
    public interface GuideItemTable{ 
         String TABLE_NAME = "guideitem";
         /**
          * 向导类型
          */
         String GUIDE_TYPE = "guide_type";
           /**
            * 上传图片
           */
         String UPLOAD_PICTURE="upload_picture";
         
         /**
          * 用于排序的字段
          */
         String SORT_ORDER = "sort_order";
         /**
          * 确定唯一性的ID
          */
         String ID ="id";
         /**
          * 网站名称、工具名称或者项目名称
          */
         String NAME = "name";
         /**
          * 项目描述
          */
         String DES = "des";
         /**
          * 内网入口URL
          */
         String INNER_URL = "inner_url";
         /**
          * 外网入口URL
          */
         String OUTER_URL = "outer_url";
         /**
          * 苹果系统平台下载URL
          */
         String DOWNLOAD_URL_FORIOS = "download_url_forios";
         /**
          * 安卓系统平台下载URL
          */
         String DOWNLOAD_URL_FORANDROID = "download_url_forandroid";
         /**
          * 电脑端下载URL
          */
         String DOWNLOAD_URL_FORPC = "download_url_forpc";
    }

