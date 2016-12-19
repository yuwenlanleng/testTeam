/*
 * Copyright(c) 2007-2011 by Yingzhi Tech All Rights Reserved
 *
 * Created at 2011-10-05 10:05:13
 */
package com.nazca.inside.guide.client.db;

import com.nazca.util.StringUtil;
import com.nazca.inside.guide.client.ClientDataCache;
import com.nazca.inside.guide.common.enums.RecordState;
import com.nazca.inside.guide.common.jpa.BasicCodable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Zhang Chun Nan
 */
public class BasicCodeDBHelper extends AbstractService4JPA {
    /**
     * 描述基础代码+名称，注意该方法增加了缓存机制
     *
     * @param <T>
     * @param claz
     * @param code
     * @return
     */
    public <T> String describeBasicCodable(Class<T> claz, String code, boolean showCode) {
        if (StringUtil.isEmpty(code)) {
            return "";
        } else {
            if (showCode) {
                return code + ' ' + getBasicCodeName(claz, code);
            } else {
                return getBasicCodeName(claz, code);
            }
        }
    }

    /**
     * 获取基础代码名称，注意该方法增加了缓存机制
     *
     * @param <T>
     * @param claz
     * @param code
     * @return
     */
    public <T> String getBasicCodeName(Class<T> claz, String code) {
        if (code == null) {
            return "";
        }
        String name = ClientDataCache.getBasicCodeName(claz, code);
        if (name == null) {
            EntityManager em = EMFactoryControler.createEM();
            try {
                TypedQuery<String> query = em.createQuery("select name from "
                        + claz.getSimpleName()
                        + " where code = ?", String.class).
                        setParameter(1, code);
                query.setMaxResults(1);
                List<String> list = query.getResultList();
                if (!list.isEmpty()) {
                    name = list.get(0);
                }
            } catch (Throwable ex) {
                dealException(em, ex);
            } finally {
                closeSession(em);
            }
            ClientDataCache.setBasicCodeName(claz, code, name);
        }
        return name;
    }

    public <T> Map<String, String> getAllBasicCodeMap(Class<T> claz) {
        EntityManager em = EMFactoryControler.createEM();
        Query query = em.createQuery("select code, name from " + claz.getSimpleName()
                    + " where recordState=?").setParameter(1, RecordState.normal);
        Map<String, String> map = new HashMap<String, String>();
        for (Object[] array : (List<Object[]>) query.getResultList()) {
            map.put((String) array[0], (String) array[1]);
        }
        return map;
    }

    public <T> T findObject(Class<T> claz, String code) {
        T result = null;
        if (!StringUtil.isEmpty(code)) {
            EntityManager em = EMFactoryControler.createEM();
            try {
                result = em.find(claz, code);
            } catch (Throwable ex) {
                dealException(em, ex);
            } finally {
                closeSession(em);
            }
        }
        return result;
    }

    public <T> BasicCodable getBasicCodeByCode(Class<T> claz, String code) {
        BasicCodable basic = null;
        if (!StringUtil.isEmpty(code)) {
            EntityManager em = EMFactoryControler.createEM();
            try {
                basic = (BasicCodable) em.find(claz, code);
            } catch (Throwable ex) {
                dealException(em, ex);
            } finally {
                closeSession(em);
            }
        }
        return basic;
    }

    public <T> List getBasicCodes(Class<T> claz) {
        EntityManager em = EMFactoryControler.createEM();
        try {
            TypedQuery<T> qu = em.createQuery("from "
                    + claz.getSimpleName()
                    + " where recordState=? order by code", claz).
                    setParameter(1, RecordState.normal);
            return qu.getResultList();
        } catch (Throwable ex) {
            dealException(em, ex);
            return null;
        } finally {
            closeSession(em);
        }
    }
}
