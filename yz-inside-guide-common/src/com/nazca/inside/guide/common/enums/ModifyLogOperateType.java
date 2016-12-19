/*
 * ModifyLogOperateType.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-09 14:45:12
 */
package com.nazca.inside.guide.common.enums;

/**
 * 修改日志表（系统）
 * 操作类型
 * @author Lijin
 */
public enum ModifyLogOperateType {
    add(){
        @Override
        public String toString() {
            return "增加";
        }
    },
    
    upd(){
        @Override
        public String toString() {
            return "修改";
        }
        
    },
    
    del(){

        @Override
        public String toString() {
            return "删除";
        }
        
    },
    
    els(){
        @Override
        public String toString() {
            return "其他";
        }
    }
}
