/*
 * LoginPanelLayout.java
 * 
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-03-14 21:21:32
 */
package com.nazca.inside.guide.client.ui.layout;

import com.nazca.niis.client.ui.LinkContainer;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JComponent;

/**
 *
 * @author Zhang Chunnan
 */
public class LoginPanelLayout implements LayoutManager {

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    public void layoutContainer(Container parent) {
        LinkContainer links = null;
        JComponent login = null;
        Component[] comps = parent.getComponents();
        for (Component c : comps) {
            JComponent jp = (JComponent) c;
            if (jp.getName() != null && jp.getName().equals("login")) {
                login = jp;
            }
            if (c instanceof LinkContainer) {
                links = (LinkContainer) c;
            }
        }
        int x = (int) (parent.getWidth() - 10 - links.getPreferredSize().getWidth());
        int y = (int) (parent.getHeight() - 10 - links.getPreferredSize().getHeight());
        links.setBounds(x, y, links.getPreferredSize().width, links.getPreferredSize().height);

        int loginw = (int) login.getPreferredSize().getWidth();
        int loginh = (int) login.getPreferredSize().getHeight() + 20;
        login.setBounds(parent.getWidth() - loginw - (parent.getWidth() - loginw) / 2, parent.getHeight() * 120 / 600 + 100, loginw, loginh);
    }
}
