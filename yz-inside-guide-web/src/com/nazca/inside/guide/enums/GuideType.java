/*
 * GuideType.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 11:11:08
 */
package com.nazca.inside.guide.enums;

/**
 * 创建GuideType三种类型的枚举集合
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public enum GuideType {
    web {
        @Override
        public String toString() {
            return "服务资源";
        }
    },
    sys {
        @Override
        public String toString() {
            return "管理系统";
        }
    },
    doc {
        @Override
        public String toString() {
            return "常用文档";
        }
    }
}
