package com.nazca.inside.guide.client.ui.laf;

/**
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 */
import com.nazca.ui.GraphicsTool;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author Zhang Chunnan
 */
public class TitlePanelUI extends PanelUI {
    private static final Color titleShadowColor = Color.decode("#4f5b67");
    private static final Color bgTopColor = new Color(0x78a3cd);
    private static final Color bgBtmColor = new Color(0xd8e7fc);
    private static BufferedImage logo = null;
    private static BufferedImage btTiao = null;

    static {
        try {
            btTiao = InsideGuideResourceUtil.readImage("bt-line.png");
            logo = InsideGuideResourceUtil.readImage("logo-small.png");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public TitlePanelUI() {
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints orgs = g2.getRenderingHints();
        GraphicsTool.setQuanlifiedGraphics(g2);
        int width = c.getWidth();
        int height = c.getHeight();
        g2.setPaint(new GradientPaint(0, 0, bgTopColor, 0, height, bgBtmColor));
        g2.fillRect(0, 0, width, height);
        g2.drawImage(logo, 14, 3, c);
        //绘制下部条；
        g2.drawImage(btTiao, 0, height - btTiao.getHeight(), width, btTiao.getHeight(), c);
        g2.setColor(titleShadowColor);
        g2.drawLine(0, height - 1, width, height - 1);
        g2.setRenderingHints(orgs);
        super.paint(g, c);
    }
}
