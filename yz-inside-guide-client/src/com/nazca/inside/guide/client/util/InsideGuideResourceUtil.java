/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-04-23 14:23:05
 */

package com.nazca.inside.guide.client.util;

import com.nazca.ui.GraphicsTool;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author chenjianan
 */
public class InsideGuideResourceUtil {
    public static BufferedImage readImage(String fileName){
        return GraphicsTool.readImage(InsideGuideResourceUtil.class.getResource("/com/nazca/inside/guide/client/ui/res/" + fileName));
    }
    
     public static String getImageURL(String imageName) {
        return "/com/nazca/inside/guide/client/ui/res/" + imageName;
    }
    
      public static ImageIcon buildImageIcon(String imageName) {
        try {
            return new ImageIcon(InsideGuideResourceUtil.class.getResource(getImageURL(imageName)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public static ImageIcon readIcon(String fileName){
        return new ImageIcon(InsideGuideResourceUtil.class.getResource("/com/nazca/inside/guide/client/ui/res/" + fileName));
    }
    
    public static InputStream readStream(String fileName){
        return InsideGuideResourceUtil.class.getResourceAsStream("/com/nazca/inside/guide/client/ui/res/" + fileName);
    }
}
