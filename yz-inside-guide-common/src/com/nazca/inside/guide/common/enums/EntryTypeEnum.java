/*
 * EntryTypeEnum.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-29 09:18:31
 */
package com.nazca.inside.guide.common.enums;

/**
 *
 * @author pengruirui
 */
public enum EntryTypeEnum {
    select{
         @Override
        public String toString() {
            return "—请选择—";
        }
    },

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
