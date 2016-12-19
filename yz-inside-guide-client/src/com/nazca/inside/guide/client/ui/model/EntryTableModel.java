/*
 * EntryTableModel.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 15:07:42
 */
package com.nazca.inside.guide.client.ui.model;

import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.ui.model.AbstractSimpleObjectTableModel;

/**
 *
 * @author pengruirui
 */
public class EntryTableModel extends AbstractSimpleObjectTableModel<Entry>{
    public static final int NAME = 0;
    public static final int TYPE = 1;
    public static final int DES = 2;
    public static final int INNERURL = 3;
    public static final int OUTERURL = 4;
    public static final int DOWNLOADURLFORIOS = 5;
    public static final int DOWNLOADURLFORANDROID = 6;
    public static final int DOWNLOADURLFORPC = 7;
    private static final String[] COLS
            = new String[]{"名称", "类别", "描述", "内网入口", "外网入口", "IOS下载", "Android下载", "URL下载"};

    public EntryTableModel() {
        super(COLS);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Entry etmw=dataList.get(rowIndex);
	switch (columnIndex) {
            
	   case TYPE:	
               if(etmw!=null){
		    if (etmw.getGuideType()== EntryTypeEnum.web) {
                        return "服务资源";
		    } else if (etmw.getGuideType() == EntryTypeEnum.sys) {
			return "管理系统";
		    }else if (etmw.getGuideType() == EntryTypeEnum.doc) {
			return "常用文档";
		    } else {
			return "全部";
		    }
             }
	    case NAME:
		return etmw.getName() != null ? etmw.getName(): null;
	    case DES:
		return etmw.getDes() != null ? etmw.getDes(): null;
	    case INNERURL:
		return etmw.getInnerUrl() != null ? etmw.getInnerUrl() : null;
	    case OUTERURL:
		return etmw.getOuterUrl() != null ? etmw.getOuterUrl() : null;
	    case DOWNLOADURLFORIOS:
		return etmw.getDownloadUrlForIos()!=null? etmw.getDownloadUrlForIos():null;
           case DOWNLOADURLFORANDROID:
		return etmw.getDownloadUrlForAndroid()!=null? etmw.getDownloadUrlForAndroid():null;
            case DOWNLOADURLFORPC:
		return etmw.getDownloadUrlForPc()!=null? etmw.getDownloadUrlForPc():null;
	    default:
		return "";
	}
    }
    }
 
    
    
    

