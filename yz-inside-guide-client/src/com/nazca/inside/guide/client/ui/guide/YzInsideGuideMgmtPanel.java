/*
 * SystemMgmtPanel.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 17:02:54
 */
package com.nazca.inside.guide.client.ui.guide;

import com.nazca.inside.guide.client.ui.sysmodel.*;
import com.nazca.niis.client.ui.tabs.AbstractAppTabPanel;
import com.nazca.usm.client.UsmsMainPanel;
import com.nazca.inside.guide.client.ui.Destructable;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Zhang Chun Nan
 */
public class YzInsideGuideMgmtPanel extends AbstractAppTabPanel implements Destructable {
    private Image activeIcon = InsideGuideResourceUtil.readImage("system-tab-active.png");
    private Image inactiveIcon = InsideGuideResourceUtil.readImage("system-tab-inactive.png");
    private JPanel userPane = null;

    /**
     * Creates new form SystemMgmtPanel
     */
    public YzInsideGuideMgmtPanel() {
        initComponents();
        setTabText("入口管理");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        centerPane = new javax.swing.JPanel();
        yzInsidePanel1 = new com.nazca.inside.guide.client.ui.guide.YzInsidePanel();

        setLayout(new java.awt.BorderLayout());

        centerPane.setLayout(new java.awt.BorderLayout());
        centerPane.add(yzInsidePanel1, java.awt.BorderLayout.CENTER);

        add(centerPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPane;
    private com.nazca.inside.guide.client.ui.guide.YzInsidePanel yzInsidePanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInit() {
        setTabIcon(inactiveIcon);
        userPane = new JPanel();
        UsmsMainPanel usmsMainPane = new UsmsMainPanel();
        usmsMainPane.initAll();
        userPane = usmsMainPane;
    }

    @Override
    public String getComponentUUID() {
        return YzInsideGuideMgmtPanel.class.getName();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onShow() {
        setTabIcon(activeIcon);
    }

    @Override
    public void onHide() {
        setTabIcon(inactiveIcon);
    }
}