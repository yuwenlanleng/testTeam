/*
 * Jk2cMmgtResourceUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-24 15:24:56
 */
package com.nazca.inside.guide.client.util;

import com.nazca.ui.GraphicsTool;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author pengruirui
 */
public class Jk2cMmgtResourceUtil {
    public static BufferedImage readImage(String fileName){
        return GraphicsTool.readImage(Jk2cMmgtResourceUtil.class.getResource("/com/nazca/inside/guide/client/ui/res/" + fileName));
    }
    
    public static ImageIcon readIcon(String fileName){
        return new ImageIcon(Jk2cMmgtResourceUtil.class.getResource("/com/nazca/inside/guide/client/ui/res/" + fileName));
    }
    
    public static InputStream readStream(String fileName){
        return Jk2cMmgtResourceUtil.class.getResourceAsStream("/com/nazca/inside/guide/client/ui/res/" + fileName);
    }
}
