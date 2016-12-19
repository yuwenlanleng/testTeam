/*
 * LocationComboxRenderer.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-25 17:58:27
 */
package com.nazca.inside.guide.client.Renderer;

import com.nazca.inside.guide.common.model.Entry;
import com.nazca.ui.laf.blueocean.NazcaListDefaultCellRenderer;
import com.nazca.util.StringUtil;
import java.awt.Component;
import javax.swing.JList;

/**
 *
 * @author pengruirui
 */
public class LocationComboxRenderer extends NazcaListDefaultCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       if (value instanceof Entry) {
            Entry base = (Entry) value;
            value = base.getName(); 
    }
   return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.    }
}
}