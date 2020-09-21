/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: Windows
 * Author: Emiya
 * Date: 2020/7/31 17:29
 * Description: win系统展示图片
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.open;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * 〈功能简述〉<br>
 * 〈win系统展示图片〉
 *  <p>
 * @author Emiya
 * @create 2020/7/31 17:29
 * @version 1.0.0
 */
public class Windows {
//    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
////        System.load(System.getProperty("user.dir")+"");
//    }
//    public static void main(String[] args) {
//        Mat image = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\0731.jpg",Imgcodecs.IMREAD_UNCHANGED);
//        Images.showImage(image,"飞飞飞");
//    }
    public static void showWindow(BufferedImage image, String str){
        if(str==null){
            str = "";
        }
        JFrame frame = new JFrame(str);
        frame.setBounds(100,100,image.getWidth(),image.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        JLabel label = new JLabel();
        label.setBounds(0,0,image.getWidth(),image.getHeight());
        frame.getContentPane().add(label);
        label.setIcon(new ImageIcon(image));
    }


}