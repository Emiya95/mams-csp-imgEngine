/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: CommonUtil
 * Author: Emiya
 * Date: 2020/8/4 16:38
 * Description: OpenCV和magick都适用的api工具类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.opencv.core.Mat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.indexer.ByteBufferIndexer;
import org.bytedeco.javacpp.indexer.IntBufferIndexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.core.MatOfByte;
//import org.opencv.imgcodecs.Imgcodecs;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * 〈功能简述〉<br>
 * 〈OpenCV和magick都适用的api工具类〉
 *  <p>
 * @author Emiya
 * @create 2020/8/4 16:38
 * @version 1.0.0
 */
//@Component
@Configuration
@EnableConfigurationProperties(ImageProperties.class)
public class CommonUtil {


    @Autowired
    @Qualifier(value = "imageProperties")
    public void setImageProperties(ImageProperties imageProperties) {
        CommonUtil.imageProperties = imageProperties;
    }

    private static ImageProperties imageProperties;



    private static final Logger log = LogManager.getLogger(CommonUtil.class);


    public static final int CENTER = 0;
    public static final int NORTHWEST = 1;
    public static final int NORTH = 2;
    public static final int NORTHEAST = 3;
    public static final int WEST = 4;
    public static final int EAST = 5;
    public static final int SOUTHWEST = 6;
    public static final int SOUTH = 7;
    public static final int SOUTHEAST = 8;

//    private static Image logo = null;
//
//    private static Mat logoMat = null;
//
//    private static Mat logoMatMask = null;
//
//    public static Image getLogo() {
//        return logo;
//        return imageProperties.get
//    }

//    public static Mat getLogoMat() {
//        return logoMat;
//    }
//
//    public static Mat getLogoMatMask() {
//        return logoMatMask;
//    }

    public static boolean isUseGraphics() {
        return imageProperties.isUseGraphics();
    }

    public static String getGraphicsMagickPath() {
        return imageProperties.getGraphicsMagickPath();
    }

    public static String getImageMagickPath() {
        return imageProperties.getImageMagickPath();
    }

    public static boolean isUseWindows() {
        return imageProperties.isUseWindows();
    }

    public static ImageProperties.LogoProperties getLogos(int num) {
        return imageProperties.getLogoPropertiesList().get(num);
    }

//    public static int getWatermarkHeight() {
//        return imageProperties.getWatermarkHeight();
//    }
//
//    public static String getWhiteLogoFile() {
//        return imageProperties.getWhiteLogoFiles().get(1);
//    }
//
//    public static String getBlackLogoFile() {
//        return imageProperties.getBlackLogoFiles().get(1);
//    }
//
//    public static Mat getWhiteWatermarkMat() {
//        return imageProperties.getWhiteWatermarkMat();
//    }
//    public static Mat getBlackWatermarkMat() {
//        return imageProperties.getBlackWatermarkMat();
//    }
//
//    public static Mat getBlackWatermarkMatMask() {
//        return imageProperties.getBlackWatermarkMatMask();
//    }
//
//    public static Mat getWhiteWatermarkMatMask() {
//        return imageProperties.getWhiteWatermarkMatMask();
//    }

    static {
        try {
//            ImageProperties imageProperties = ApplicationContextHolder.getBean(ImageProperties.class);
//            System.out.println("lgog="+imageProperties.getLogoFile());
//            logo = ImageIO.read(new File(imageProperties.getLogoFile()));
//            logoMat = Imgcodecs.imread(imageProperties.getLogoFile(),Imgcodecs.IMREAD_UNCHANGED);
//            logoMatMask = Imgcodecs.imread(imageProperties.getLogoFile(),Imgcodecs.IMREAD_GRAYSCALE);
//            System.out.println("bb="+logo);
//            System.out.println("bbb="+logoMat);
//            logo = imageProperties.getWatermarkImage();
//            logoMat = imageProperties.getWatermarkMat();
//            logoMatMask = imageProperties.getWatermarkMatMask();
//            System.out.println("bb="+logo);
//            System.out.println("bbb="+logoMat);
        } catch (Exception e) {
            log.error("水印图片读取失败");
            e.printStackTrace();
        }
    }

    public static void  hh() throws IOException {
        System.out.println("lgog="+getLogos(1).getBlackLogoFile());
//        System.out.println("bb="+imageProperties.getBlackWatermarkImage());
        System.out.println("bbb="+getLogos(1).getBlackLogoFile());
    }

    /**
     * 创建目录
     *
     * @param path
     * @return path
     */
    public static String createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        return path;
    }

    /**
     *
     * @description: mat转bufferedImage
     *      <p/>
     * @param source:
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage toBufferedImage(Mat source) {
//        // 顶一个字节数组矩阵
////        MatOfByte mob = new MatOfByte();
//        ByteBuffer mob = new ByteBuffer();
//        mob.array();
//        // 压缩数据到字节矩阵
//        opencv_imgcodecs.imencode(".png", source, mob);
//        // 将矩阵转数组
//        byte[] byteArray = mob.toArray();
//        // 从数组构建一个输入流
//        InputStream input = new ByteArrayInputStream(byteArray);
//        // 从输入流中读取图像
//        try {
//            return ImageIO.read(input);
//        } catch (IOException e) {
//            return null;
//        }
        return null;
    }

    /**
     *
     * @description: 获取图片流
     *      <p/>
     * @param fileName:
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage getImage(String fileName){
        //因为是读取，所以不需要判断是否要建立路径
        File file = new File(fileName);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }

    public static BufferedImage selectArea(BufferedImage bufferedImage, int posX, int posY, int width, int height){
        if (bufferedImage==null){
            log.warn("选取区域错误:源图片流为null");
            return null;
        }
        try {
            return bufferedImage.getSubimage(posX,posY,width,height);
        } catch (Exception e) {
            log.error("选取区域错误:"+e.getMessage());
        }
        return null;
    }

    public static boolean calAreaBlack(Mat mat) throws Exception{
        return false;
//        if (mat.empty()){
//            log.error("计算区域黑度量值错误:源图片所选区域Mat为null");
//            throw new Exception("计算区域黑度量值错误:源图片所选区域Mat为null");
//        }
//        int width = mat.cols();
//        int height = mat.rows();
//        //属于接近黑色的像素点个数
//        long blackNumber = 0;
//        //用于存放像素点,也是存放颜色分量，但是OpenCV的顺序跟jdk读取出来的顺序相反
//        double[] pixel = new double[3];
//        IntBufferIndexer byteBufferIndexer = mat.createIndexer();
//        for (int i = 0 ;i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                //第一种
//                int xsu = byteBufferIndexer.get(i,j);
//                //第二种
//                BytePointer bytePointer = mat.ptr(j,i);
//                int channels = mat.channels();
//                bytePointer.get(channels);
//                //第三种
//                ByteBuffer buffer = mat.getByteBuffer();
//                int index = j * mat.arrayWidth()+ i * mat.arrayChannels();
//                int value = buffer.get(index) & 0xFF;
//
//                // Sets the pixel to a value (greyscale).
//                buffer.put(index, value);
//
//                // Sets the pixel to a value (RGB, stored in BGR order).
//                buffer.put(index, blue);
//                buffer.put(index + 1, green);
//                buffer.put(index + 2, red);
//                pixel = mat.get(i, j).clone();
//                //B
//                pixel[0] = 255 - pixel[0];
//                //G
//                pixel[1] = 255 - pixel[1];
//                //R
//                pixel[2] = 255 - pixel[2];
////                dst.put(i, j, pixel);
//                //人眼感官上的接近黑色（非色深或者明亮度）
//                if ((0.2126*pixel[2] + 0.7152*pixel[1] + 0.0722*pixel[0])<128){
//                    blackNumber++;
//                }
//            }
//        }
//        // 总像素点个数
//        long totalPixelNumber = width * height;
//        // 获取浮点数表示的占比
//        double pixelProportion = (double) blackNumber / totalPixelNumber;
//        //黑色区域超过一半，则返回true，需要白色字体
//        log.info("在没算出来的啊=================="+pixelProportion);
//        return (pixelProportion > 0.5);
    }

    /**
     *
     * @description: 计算似黑色区域的范围是否超过全部范围
     *      <p/>
     * @param bufferedImage:
     * @return boolean
     */
    public static boolean calAreaBlack(BufferedImage bufferedImage) throws Exception {
        if (bufferedImage==null) {
            log.error("计算区域黑度量值错误:源图片流为null");
            throw new Exception("计算区域黑度量值错误:源图片流为null");
        }
        //长宽
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        //横纵坐标起始点
        int minx = bufferedImage.getMinX();
        int miny = bufferedImage.getMinY();
        //属于接近黑色的像素点个数
        long blackNumber = 0;
        // 定义RGB空间,包含RGB颜色分量的数组，元素index由小到大分别为rgb
        int[] rgb = new int[3];
        // 定义HSV空间
//        float[] hsv = new float[3];
        // 开始遍历所有像素点
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                // 当前像素点
                int pixel = bufferedImage.getRGB(i, j);
                //pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
//                int pixel = bufferedImage.getRGB(i, j)+16777216;
                // 获取RGB各值
                rgb[0] = (pixel & 0xff0000) >> 16;//R
                rgb[1] = (pixel & 0xff00) >> 8;//G
                rgb[2] = (pixel & 0xff);//B
                // rgb转hsv
//                Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv);
//                if ((rgb[0]+rgb[1]+rgb[2])/3<128){
//                    blackNumber++;
//                }
                //人眼感官上的接近黑色（非色深或者明亮度）
                if ((0.2126*rgb[0] + 0.7152*rgb[1] + 0.0722*rgb[2])<128){
                    blackNumber++;
                }
            }
        }
        // 总像素点个数
        long totalPixelNumber = width * height;
        // 获取浮点数表示的占比
        double pixelProportion = (double) blackNumber / totalPixelNumber;
        //黑色区域超过一半，则返回true，需要白色字体
        return (pixelProportion > 0.5);
    }

    /**
     * 通过文件名获取文件类型
     *
     * @param fileName 文件名
     */
    public static String getFileType(String fileName) {
        if (fileName == null || "".equals(fileName.trim()) || fileName.lastIndexOf(".") == -1) {
            return null;
        }
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        return type.toLowerCase();
    }


}