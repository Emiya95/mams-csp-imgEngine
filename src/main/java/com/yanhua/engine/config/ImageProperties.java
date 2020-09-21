/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: OpenCvProperties
 * Author: Emiya
 * Date: 2020/7/30 18:12
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.config;

import com.yanhua.engine.open.Images;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.core.Core;
//import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.core.util.Loader.getClassLoader;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

/**
 * 〈功能简述〉<br>
 * 〈〉图片引擎的初始化配置类，初始化各个必要参数以及校验
 *  <p>
 * @author Emiya
 * @create 2020/7/30 18:12
 * @version 1.0.0
 */
@Component("imageProperties")
@ConfigurationProperties(prefix = "image")
public class ImageProperties {
    private static final Logger log = LogManager.getLogger(ImageProperties.class);

    /**
     * dll动态链接库路径
     */
    private String libraryPath;

    /**
     * 是否使用GraphicsMagick
     */
    private boolean useGraphics;

    /**
     * Magick的安装路径
     */
    private String imageMagickPath;

    /**
     * Magick的安装路径
     */
    private String graphicsMagickPath;

    //===========================水印logo==============================

    private List<LogoProperties> logoPropertiesList = new ArrayList<>();

    public String getLibraryPath() {
        return libraryPath;
    }

    public void setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
    }

    public boolean isUseGraphics() {
        return useGraphics;
    }

    public void setUseGraphics(boolean useGraphics) {
        this.useGraphics = useGraphics;
    }

    public String getImageMagickPath() {
        return imageMagickPath;
    }

    public void setImageMagickPath(String imageMagickPath) {
        this.imageMagickPath = imageMagickPath;
    }

    public String getGraphicsMagickPath() {
        return graphicsMagickPath;
    }

    public void setGraphicsMagickPath(String graphicsMagickPath) {
        this.graphicsMagickPath = graphicsMagickPath;
    }

    public List<LogoProperties> getLogoPropertiesList() {
        return logoPropertiesList;
    }

    public void setLogoPropertiesList(List<LogoProperties> logoPropertiesList) {
        this.logoPropertiesList = logoPropertiesList;
    }

    public boolean isUseWindows() {
        return useWindows;
    }

    public void setUseWindows(boolean useWindows) {
        this.useWindows = useWindows;
    }

    public static class LogoProperties {
        /**
         * 水印图片-黑 路径
         */
        private String blackLogoFile;

        /**
         * 水印图片-黑
         */
//    private Image blackWatermarkImage;

        /**
         * 水印图片-黑 mat格式
         */
        private Mat blackWatermarkMat;

        /**
         * 水印图片-黑 mat格式 mask灰度图
         */
        private Mat blackWatermarkMatMask;

        /**
         * 水印图片-白 路径
         */
        private String whiteLogoFile;

        /**
         * 水印图片-白
         */
//    private Image whiteWatermarkImage;

        /**
         * 水印图片-白 mat格式
         */
        private Mat whiteWatermarkMat;

        /**
         * 水印图片-白 mat格式 mask灰度图
         */
        private Mat whiteWatermarkMatMask;

        /**
         * 预期水印图片的字体重点区域宽
         */
        private int watermarkWidth;

        /**
         * 预期水印图片的字体重点区域高
         */
        private int watermarkHeight;

        public String getBlackLogoFile() {
            return blackLogoFile;
        }

        public void setBlackLogoFile(String blackLogoFile) {
            this.blackLogoFile = blackLogoFile;
        }

        public String getWhiteLogoFile() {
            return whiteLogoFile;
        }

        public void setWhiteLogoFile(String whiteLogoFile) {
            this.whiteLogoFile = whiteLogoFile;
        }

        public Mat getBlackWatermarkMat() {
            return blackWatermarkMat;
        }

        public void setBlackWatermarkMat(Mat blackWatermarkMat) {
            this.blackWatermarkMat = blackWatermarkMat;
        }

        public Mat getBlackWatermarkMatMask() {
            return blackWatermarkMatMask;
        }

        public void setBlackWatermarkMatMask(Mat blackWatermarkMatMask) {
            this.blackWatermarkMatMask = blackWatermarkMatMask;
        }

        public Mat getWhiteWatermarkMat() {
            return whiteWatermarkMat;
        }

        public void setWhiteWatermarkMat(Mat whiteWatermarkMat) {
            this.whiteWatermarkMat = whiteWatermarkMat;
        }

        public Mat getWhiteWatermarkMatMask() {
            return whiteWatermarkMatMask;
        }

        public void setWhiteWatermarkMatMask(Mat whiteWatermarkMatMask) {
            this.whiteWatermarkMatMask = whiteWatermarkMatMask;
        }

        public int getWatermarkWidth() {
            return watermarkWidth;
        }

        public void setWatermarkWidth(int watermarkWidth) {
            this.watermarkWidth = watermarkWidth;
        }

        public int getWatermarkHeight() {
            return watermarkHeight;
        }

        public void setWatermarkHeight(int watermarkHeight) {
            this.watermarkHeight = watermarkHeight;
        }
    }

    /**
     * 是否是window环境
     */
    private boolean useWindows;

    @Bean
    public void init() {
        //dll的绝对路径 第一种
//        System.load(path);
        log.info("========================ImageProperties配置注入start=========================");
//        URL url = ClassLoader.getSystemResource("opencv/x64/opencv_java341.dll");
//        System.load(url.getPath());
//        System.out.println("url========="+url.getPath());
        //第二种
//        System.out.println("libpath"+System.getProperty("java.library.path"));
        log.info("path1 = "+System.getProperty("java.library.path"));
        //dll文件名  第三种
        try{
            log.info("path2 = "+libraryPath);
            log.info("path3 = "+Core.NATIVE_LIBRARY_NAME);
//            System.loadLibrary("libopencv_java341");
//            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//            System.load(libraryPath);
//            nu.pattern.OpenCV.loadLocally();
            //导入dll
//            String relativelyPath = System.getProperty("user.dir");
//            System.load(relativelyPath + "\\opencv_java340-x64.dll");
//            blackWatermarkImage = ImageIO.read(new File(blackLogoFile));
            //根据读取的logo路径生成原图和灰图的OpenCV mat
            for (LogoProperties logo: logoPropertiesList){
                log.info("黑字logo路径："+logo.getBlackLogoFile());
                log.info("白字logo路径："+logo.getWhiteLogoFile());
                logo.setBlackWatermarkMat(imread(logo.getBlackLogoFile()));
                logo.setBlackWatermarkMatMask(imread(logo.getBlackLogoFile(),IMREAD_GRAYSCALE));
                //白色
                logo.setWhiteWatermarkMat(imread(logo.getWhiteLogoFile()));
                logo.setWhiteWatermarkMatMask(imread(logo.getWhiteLogoFile(),IMREAD_GRAYSCALE));
            }
            //todo:为读取的黑白logo图做其宽高验证是否和配置文件里的宽高一致。如果不一致就抛出异常
            //判断是否为Windows环境
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                setUseWindows(true);
                useWindows = true;
            } else {
                useWindows = false;
            }
            log.info("useWindows="+useWindows);
            log.info("初始化准备完毕!");
//        }catch (IOException e){
//            log.error("水印图片加载失败");
//            e.printStackTrace();
        }catch (Exception e){
            log.error("初始化配置加载失败");
            e.printStackTrace();
        }
    }




}