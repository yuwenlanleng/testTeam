/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-18 15:47:36
 */
package com.nazca.inside.guide.common.consts;

/**
 *
 * @author liqin
 */
public interface InsideGuideErrorCode {
    // 000000 - 009999 公共错误
    /**
     * 数据库内部失败
     */
    int DB_ERROR = 10;
    /**
     * 数据库提交失败
     */
    int DB_TRANSACTION_ERROR = 12;
    /**
     * 数据库回滚失败
     */
    int DB_ROLLBACK_ERROR = 14;
    /**
     * 数据库无法访问
     */
    int DB_CONNECT_FAILED = 15;
    /**
     * 客户端数据库内部错误
     */
    int CLIENT_DB_ERROR = 16;
    /**
     * 客户端数据库提交失败
     */
    int CLIENT_DB_TRANSACTION_ERROR = 17;
    /**
     * 客户端数据库回滚失败
     */
    int CLIENT_DB_ROLLBACK_ERROR = 18;
    /**
     * 应用服务器内部错误
     */
    int SERVER_ERROR = 20;
    /**
     * 网络链接错误
     */
    int NETWORK_ERROR = 30;
    /**
     * 参数错误
     */
    int ARGUMENTS_ERROR = 40;
    /**
     * 用户名或密码不能为空
     */
    int EMPTY_USERID_OR_PWD = 400;
    /**
     * 用户名或密码错误
     */
    int WRONG_USERID_OR_PWD = 410;
    /**
     * 添加的记录已存在
     */
    int OBJECT_EXIST = 6666;
    /**
     * 查询的记录不存在
     */
    int OBJECT_NOT_EXIST = 7777;
    /**
     * 对象修改冲突
     */
    int OBJECT_MODIFY_CONFLICT = 8000;
    /**
     * 执行线程意外中断
     */
    int THREAD_INTERRUPTED = 8888;
    /**
     * 未知错误
     */
    int UNKNOWN_ERROR = 9999;
    /**
     * 用户认证失败，请重新登录
     */
    int LACK_OF_AUTH = 110;
    /**
     * USMS错误代码起始
     */
    int USMS_ERROR_CODE_START = 990000;
    /**
     * 您没有角色，不能登录
     */
    int LACK_OF_ROLES = 991000;
    /**
     * 您的单位已被删除，不能登录
     */
    int ORG_DELETED = 991010;
    
    /**
     * 文件存在
     */
    int FILE_EXIT = 991060;
    
    /**
     * 文件不存在
     */
    int FILE_NO_EXIT = 991061;
    
    /**
     * 文件名为空 
     */
    int FILE_NAME_NO = 991062;
    
    
}
