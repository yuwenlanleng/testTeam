/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-26 16:48:33
 */
package com.nazca.inside.guide.client.db;

import com.nazca.io.httprpc.HttpRPCException;
import javax.persistence.EntityManager;
import com.nazca.inside.guide.common.consts.InsideGuideErrorCode;

/**
 *
 * @author fred
 */
public class AbstractService4JPA {
    protected HttpRPCException dealException(EntityManager em, Throwable ex) {
        if (ex instanceof HttpRPCException) {
            try{
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                return (HttpRPCException) ex;
            } catch (Throwable th) {
                th.printStackTrace();
                return new HttpRPCException("数据库操作失败", InsideGuideErrorCode.CLIENT_DB_ROLLBACK_ERROR, ex);
            }
        } else {
            try {
                ex.printStackTrace();
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                return new HttpRPCException("数据库操作失败",
                        InsideGuideErrorCode.CLIENT_DB_TRANSACTION_ERROR);
            } catch (Throwable th) {
                th.printStackTrace();
                return new HttpRPCException("数据库操作失败",
                        InsideGuideErrorCode.CLIENT_DB_ROLLBACK_ERROR);
            }
        }
    }
    
    protected void closeSession(EntityManager em) {
        if (em != null) {
            try {
                em.close();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }
}
