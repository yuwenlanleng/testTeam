/*
 * EntryMgmtPanel.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 12:12:20
 */
package com.nazca.inside.guide.client.ui.sysmodel;

import com.nazca.inside.guide.client.ui.guide.YzInsidePanel;
import com.nazca.inside.guide.client.ui.Destructable;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import com.nazca.niis.client.ui.tabs.AbstractAppTabPanel;
import com.nazca.usm.client.UsmsMainPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author pengruirui
 */
public class EntryMgmtPanel extends AbstractAppTabPanel implements Destructable {
    private Image activeIcon
            = InsideGuideResourceUtil.readImage("system-tab-active.png");
    private Image inactiveIcon
            = InsideGuideResourceUtil.readImage("system-tab-inactive.png");
    private JPanel userPane = null;

    /**
     * Creates new form EntryMgmtPanel
     */
    public EntryMgmtPanel() {
        initComponents();
        setTabText("入口管理");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        nNavToolBar1 = new com.nazca.ui.NNavToolBar();
        userBtn = new javax.swing.JToggleButton();
        centerPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        refreshBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        modifyBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        upBtn = new javax.swing.JButton();
        downBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        nNavToolBar1.setRollover(true);

        userBtn.setText("英智入口管理");
        userBtn.setFocusable(false);
        userBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        userBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userBtnActionPerformed(evt);
            }
        });
        nNavToolBar1.add(userBtn);

        add(nNavToolBar1, java.awt.BorderLayout.NORTH);

        centerPanel.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(-1);
        jSplitPane1.setResizeWeight(0.4);
        jSplitPane1.setLeftComponent(jPanel1);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        refreshBtn.setText("刷新");
        refreshBtn.setFocusable(false);
        refreshBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(refreshBtn);

        addBtn.setText("增加");
        addBtn.setFocusable(false);
        addBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(addBtn);

        modifyBtn.setText("修改");
        modifyBtn.setFocusable(false);
        modifyBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modifyBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(modifyBtn);

        deleteBtn.setText("删除");
        deleteBtn.setFocusable(false);
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(deleteBtn);

        upBtn.setText("上移");
        upBtn.setFocusable(false);
        upBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        upBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(upBtn);

        downBtn.setText("下移");
        downBtn.setFocusable(false);
        downBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(downBtn);

        jPanel2.add(jToolBar1);

        jSplitPane1.setRightComponent(jPanel2);

        centerPanel.add(jSplitPane1, java.awt.BorderLayout.PAGE_START);

        add(centerPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void userBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userBtnActionPerformed
        centerPanel.removeAll();
//        centerPanel.add(centerPanel, BorderLayout.CENTER);
        YzInsidePanel yzInsidePanel = new YzInsidePanel();
        centerPanel.add(yzInsidePanel, BorderLayout.CENTER);
        centerPanel.validate();
        centerPanel.repaint();
    }//GEN-LAST:event_userBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton downBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton modifyBtn;
    private com.nazca.ui.NNavToolBar nNavToolBar1;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton upBtn;
    private javax.swing.JToggleButton userBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onInit() {
        setTabIcon(inactiveIcon);
        userPane = new JPanel();
        UsmsMainPanel usmsMainPane = new UsmsMainPanel();
        usmsMainPane.initAll();
        userPane = usmsMainPane;
        userBtn.doClick();
    }

    @Override
    public String getComponentUUID() {
        return SystemMgmtPanel.class.getName();
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