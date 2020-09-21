/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: MagickUtil
 * Author: Emiya
 * Date: 2020/8/3 17:53
 * Description: magick的核心api工具类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.magick;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.yanhua.engine.config.CommonUtil;
import com.yanhua.engine.config.ImageProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;
import org.im4java.process.StandardStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yanhua.engine.config.CommonUtil.*;
import static com.yanhua.engine.config.CommonUtil.SOUTHEAST;

/**
 * 〈功能简述〉<br>
 * 〈magick的核心api工具类〉
 *  <p>
 * @author Emiya
 * @create 2020/8/3 17:53
 * @version 1.0.0
 */
public class MagickUtil {

    private static final Logger log = LogManager.getLogger(MagickUtil.class);

//    /**
//     * 是否使用 GraphicsMagick
//     */
//    private static boolean useGraphicsMagickPath;
//
//    @Value("${magick.useGraphics}")
//    public void setUseGraphicsMagickPath(boolean useGraphics) {
//        useGraphicsMagickPath = useGraphics;
//    }
//
//    /**
//     * ImageMagick安装路径
//     */
//    private static String imageMagickPath;
//
//    @Value("${magick.imageMagick.path}")
//    public void setImageMagickPath(String path) {
//        imageMagickPath = path;
//    }
//
//    /**
//     * GraphicsMagick 安装路径
//     */
//    private static String graphicsMagickPath;
//
//    @Value("${magick.graphicsMagick.path}")
//    public void setGraphicsMagickPath(String path) {
//        graphicsMagickPath = path;
//    }

    /**
     * 命令类型
     *
     */
    private enum CommandType {
        /**
         * 转换处理
         */
        CONVERT("转换处理"),
        /**
         * 图片信息
         */
        IDENTIFY("图片信息"),
        /**
         * 图片合成
         */
        COMPOSITE("图片合成");
        private String name;

        CommandType(String name) {
            this.name = name;
        }
    }

    /**
     * 获取 ImageCommand
     *
     * @param command 命令类型
     * @return
     */
    private static ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd;
        switch (command) {
            case IDENTIFY:
                cmd = new IdentifyCmd(CommonUtil.isUseGraphics());
                break;
            case COMPOSITE:
                cmd = new CompositeCmd(CommonUtil.isUseGraphics());
                break;
            default:
                cmd = new ConvertCmd(CommonUtil.isUseGraphics());
        }
        if (CommonUtil.isUseWindows()) {
            cmd.setSearchPath(CommonUtil.isUseGraphics() ? CommonUtil.getGraphicsMagickPath() : CommonUtil.getImageMagickPath());
        }
        return cmd;
    }



    /**
     * 获取图片信息
     *
     * @param srcImagePath 图片路径
     * @return Map {height=, fileLength=, directory=, width=, filename=}
     */
    public static Map<String, Object> getImageInfo(String srcImagePath) {
        IMOperation op = new IMOperation();
        op.format("%w,%h,%d,%f,%b");
        op.addImage(srcImagePath);
        IdentifyCmd identifyCmd = (IdentifyCmd) getImageCommand(CommandType.IDENTIFY);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        try {
            identifyCmd.run(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> cmdOutput = output.getOutput();
        if (cmdOutput.size() != 1) {
            return null;
        }
        String line = cmdOutput.get(0);
        String[] arr = line.split(",");
        Map<String, Object> info = new HashMap<>();
        info.put("width", Integer.parseInt(arr[0]));
        info.put("height", Integer.parseInt(arr[1]));
        info.put("directory", arr[2]);
        info.put("filename", arr[3]);
        info.put("fileLength", Integer.parseInt(arr[4]));
        return info;
    }

    /**
     * 文字水印
     *
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param content 文字内容（不支持汉字）
     * @throws Exception
     */
    public static void addTextWatermark(String srcImagePath, String destImagePath, String content)
            throws Exception {
        IMOperation op = new IMOperation();
        op.font("微软雅黑");
        // 文字方位-东南
        op.gravity("southeast");
        // 文字信息
        op.pointsize(18).fill("#BCBFC8").draw("text 10,10 " + content);
        // 原图
        op.addImage(srcImagePath);
        // 目标
        op.addImage(CommonUtil.createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        cmd.run(op);
    }

    /**
     *
     * @description: 图片水印
     *      <p/>
     * @param logoNum:
     * @param fileName:
     * @param newFile:
     * @param dissolve: 透明度（0-100）
     * @return boolean
     */
    public static boolean addImgWatermark(int logoNum, String fileName, String newFile, Integer dissolve)
            throws Exception {
        try{
            // 原始图片信息
            BufferedImage buffImg = CommonUtil.getImage(fileName);
            if (buffImg==null){
                log.error("原图读取为null:srcImagePath"+fileName);
                throw new Exception("原图读取为null:srcImagePath"+fileName);
            }
            ImageProperties.LogoProperties logoProperties = CommonUtil.getLogos(logoNum);
            int w = logoProperties.getWatermarkWidth();
            int h = logoProperties.getWatermarkHeight();
            BufferedImage area = CommonUtil.selectArea(buffImg,10+33,10+5,w,h);
            if (area == null) {
                log.error("计算区域黑度量值错误:源图片所选区域流为null");
                throw new Exception("计算区域黑度量值错误:源图片所选区域流为null");
            }
//            File file = new File("C:\\Users\\49468\\Desktop\\area2.jpg");
//            ImageIO.write(area,"jpg",file);
            String logo;
            if (CommonUtil.calAreaBlack(area)){
                //选取白色字体
                logo = logoProperties.getWhiteLogoFile();
            }else {
                logo = logoProperties.getBlackLogoFile();
            }
            IMOperation op = new IMOperation();
            // 水印图片位置
            op.geometry(w+33, h+10, 10, 10);
            // 水印透明度
            op.dissolve(dissolve);
            // 水印
            op.addImage(logo);
            op.quality(100.0);
            op.addRawArgs("+profile", "*");
            // 原图
            op.addImage(fileName);
            // 目标
            op.addImage(CommonUtil.createDirectory(newFile));
            ImageCommand cmd = getImageCommand(CommandType.COMPOSITE);
            cmd.run(op);
        }catch (Exception e){
            log.error("添加图片水印失败:"+e.getMessage());
            throw new Exception("添加图片水印失败:"+e.getMessage());
        }
        return true;
    }

    public static String jdImageByGM(String srcPath, String newPath,long desFileSize) throws Exception {

        File srcFileJpg = new File(srcPath);
        long srcFileSizeJpg = srcFileJpg.length();
        //判断大小，如果小于100k,不压缩；反之则压缩
        if (srcFileSizeJpg>desFileSize * 1024) {
            double quality = 100d;
            if (srcFileSizeJpg>0){
                quality = Math.floor((desFileSize * 102400d) /srcFileSizeJpg);
                System.out.println("qua="+quality);
            }
            GMOperation op = new GMOperation();
            op.addImage();
            op.quality(98d);
            op.addRawArgs("+profile","*");
            op.addImage();
            ImageCommand cmd = getImageCommand(CommandType.CONVERT);
            try{
                cmd.run(op, srcPath, newPath);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return newPath;
    }
    /**
     * 非等比压缩
     * @param width 缩放后的图片宽度
     * @param height 缩放后的图片高度
     * @param fileName 源图片路径
     * @param newFile 缩放后图片的路径
     * @param type 1为比例处理，2为大小处理，如（比例：1024x1024,大小：50%x50%）
     */
    public static boolean resizeUnlock(int width, int height, String fileName, String newFile,int type,
                                      String quality) throws Exception {
        try {
            GMOperation op = new GMOperation();
            op.addImage();
            String raw;
            if (type == 1) {
                //按像素
                raw = width + "x" + height + "!";
            } else {
                //按像素百分比
                raw = width + "%x" + height + "%!";
            }
            op.addRawArgs("-resize", raw);
            if (StringUtils.isNotBlank(quality)) {
                op.addRawArgs("-quality", quality);
            }
            //去掉多余信息可以进一步减少图片占用大小,是可以减少,但也就2、3kb吧
            op.addRawArgs("+profile", "*");
            op.addImage();
            ImageCommand cmd = getImageCommand(CommandType.CONVERT);
            //不知道干嘛用
            cmd.setErrorConsumer(StandardStream.STDERR);
            cmd.run(op, fileName, CommonUtil.createDirectory(newFile));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图片非等比压缩失败:" + e.getMessage());
            throw new Exception("图片非等比压缩失败:" + e.getMessage());
        }
        return true;
    }

    public static String getCoordinate(int position)throws Exception{
        String s;
        switch (position){
            case NORTHWEST:
                //西北左上
                s= "northwest";
                break;
            case NORTH:
                //北，中上
                s= "north";
                break;
            case NORTHEAST:
                //东北右上
                s= "northeast";
                break;
            case WEST:
                //西左中
                s= "west";
                break;
            case EAST:
                //东右中
                s="east";
                break;
            case SOUTHWEST:
                //西南左下
                s= "southwest";
                break;
            case SOUTH:
                //南下中
                s= "south";
                break;
            case SOUTHEAST:
                //东南右下
                s= "southeast";
                break;
            default:
                s= "center";
                break;
        }
        return s;
    }

    /**
     *
     * @description: 等比压缩，选择重点位置裁剪
     *      <p/>
     * @param width:
     * @param height:
     * @param fileName:
     * @param newFile:
     * @param quality:
     * @return boolean
     */
    public static boolean resizeLock(int width, int height, String fileName, String newFile,
                                      double quality,int position) throws Exception {
        try {
            GMOperation op = new GMOperation();
            op.addImage();
            BufferedImage bufferedImage = CommonUtil.getImage(fileName);
            float width1 = bufferedImage.getWidth();
            float height1 = bufferedImage.getHeight();
            float scale = Math.max(width / width1, height / height1);
            //scale比例
            op.resize(Math.round(width1 * scale), Math.round(height1 * scale));
            String coordinate = getCoordinate(position);
            op.gravity(coordinate);
            String raws = width + "x" + height;
            op.addRawArgs("-extent", raws);
            op.quality(quality);
//            op.addRawArgs("+profile", "*");
            //去掉多余信息可以进一步减少图片占用大小,是可以减少,但也就2、3kb吧
            op.addImage();
            ImageCommand cmd = getImageCommand(CommandType.CONVERT);
            //不知道干嘛用
            cmd.setErrorConsumer(StandardStream.STDERR);
            cmd.run(op, fileName, CommonUtil.createDirectory(newFile));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图片等比压缩失败:" + e.getMessage());
            throw new Exception("图片等比压缩失败:" + e.getMessage());
        }
        return true;
    }
    public static boolean resizeLock2(int width, int height, String fileName, String newFile,
                                      double quality) throws Exception {
        try {
            IMOperation op = new IMOperation();
            op.addImage(fileName);
//            op.addImage();
            BufferedImage bufferedImage = CommonUtil.getImage(fileName);
            float width1 = bufferedImage.getWidth();
            float height1 = bufferedImage.getHeight();
            float scale = Math.max(width / width1, height / height1);
            //scale比例
            op.resize(Math.round(width1 * scale), Math.round(height1 * scale));
            op.gravity("center");
            String raws = width + "x" + height;
            op.addRawArgs("-extent", raws);
//            op.quality(quality);
//            op.addRawArgs("+profile", "*");
            //去掉多余信息可以进一步减少图片占用大小,是可以减少,但也就2、3kb吧
//            op.addImage();
//            ImageCommand cmd = getImageCommand(CommandType.CONVERT);
//            //不知道干嘛用
//            cmd.setErrorConsumer(StandardStream.STDERR);
//            cmd.run(op, fileName, newFile);
            // 原始图片信息
            BufferedImage buffImg = CommonUtil.getImage(fileName);
            if (buffImg==null){
                log.error("原图读取为null:srcImagePath"+fileName);
                throw new Exception("原图读取为null:srcImagePath"+fileName);
            }
            ImageProperties.LogoProperties logoProperties = CommonUtil.getLogos(1);
            int w = logoProperties.getWatermarkWidth();
            int h = logoProperties.getWatermarkHeight();
            BufferedImage area = CommonUtil.selectArea(buffImg,5,5,w,h);
            if (area == null) {
                log.error("计算区域黑度量值错误:源图片所选区域流为null");
                throw new Exception("计算区域黑度量值错误:源图片所选区域流为null");
            }
            String logo;
            if (CommonUtil.calAreaBlack(area)){
                //选取白色字体
                logo = logoProperties.getWhiteLogoFile();
            }else {
                logo = logoProperties.getBlackLogoFile();
            }
//            IMOperation op = new IMOperation();
            // 水印图片位置
            op.geometry(w, h, 5, 5);
            // 水印透明度
            op.dissolve(100);
            // 水印
            op.addImage(logo);
            // 原图
            op.addImage(fileName);
            op.quality(quality);
            op.addRawArgs("+profile", "*");
            // 目标
            op.addImage(CommonUtil.createDirectory(newFile));
            ImageCommand cmd = getImageCommand(CommandType.COMPOSITE);
            cmd.setErrorConsumer(StandardStream.STDERR);
            cmd.run(op);
        } catch (Exception e) {
            log.error("图片等比压缩失败:" + e.getMessage());
            throw new Exception("图片等比压缩失败:" + e.getMessage());
        }
        return true;
    }

    /**
     *
     * @description: 轮询式压缩图片质量
     *      <p/>
     * @param fileName:
     * @param desFileSize:
     * @return boolean
     */
    public static boolean pollCompress(String fileName,long desFileSize) throws Exception{
        GMOperation op1;
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        //不知道干嘛用
        cmd.setErrorConsumer(StandardStream.STDERR);
        try{
            File newExistFile = new File(fileName);
            long size = newExistFile.length();
            int n = 0;
            double quality = 95d;
            boolean flag = true;
            while(desFileSize>0.0&&size>desFileSize*1024){
                ++n;
                if (n>2){
                    if (flag){
                        //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
                        quality = Math.floor((desFileSize * 102400d) /size);
                        quality = Math.min(quality, 50d);
                        log.info("质量差距过大，生成新quality="+quality);
                        flag = false;
                    }else {
                        quality = quality/2;
                        log.info("质量差距过大，生成最终quality="+quality);
                    }
                }
                if (n > 5){
                    log.warn("质量差距过大，压缩无法实现");
                    return false;
                }
                op1 = new GMOperation();
                op1.addImage();
                op1.quality(quality);
                op1.addRawArgs("+profile","*");
                op1.addImage();
                cmd.run(op1,fileName,fileName);
                size = newExistFile.length();
                log.info("得到图片大小："+size);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("图片轮询压缩质量失败:"+e.getMessage());
            throw new Exception("图片轮询压缩质量失败:"+e.getMessage());
        }
        return true;
    }


    /** * 根据坐标裁剪图片
     * @param srcPath 要裁剪图片的路径
     * @param newPath 裁剪图片后的路径
     * @param x 起始横坐标
     * @param y 起始挫坐标
     * @param x1 结束横坐标
     * @param y1 结束挫坐标
     */
    public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1)
            throws Exception {
        int width = x1 - x; int height = y1 - y;
        GMOperation op = new GMOperation();
        op.addImage(srcPath);
        /**
         * width：裁剪的宽度
         * height：裁剪的高度
         * x：裁剪的横坐标
         * y：裁剪的挫坐标
         */
        op.crop(width, height, x, y);
        op.addImage(newPath);
        ImageCommand convert = getImageCommand(CommandType.CONVERT);
        //linux下不要设置此值，不然会报错
        try{
            convert.run(op, srcPath, newPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 给图片加水印
     * @param srcPath 源图片路径
     */
    public static void addImgText(String srcPath) throws Exception {
//        IMOperation op = new IMOperation();
        GMOperation op = new GMOperation();
        op.font("宋体").gravity("southeast").pointsize(18).fill("#BCBFC8").draw("text 100,100 co188.com");
        op.addImage();
        op.addImage();
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        try{
            cmd.run(op, srcPath, srcPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * jdk压缩图片-质量压缩
     *
     * @param fileName 被压缩文件路径
     * @param quality 压缩质量比例
     * @return
     * @throws Exception
     */
    public static void jdkResize(String fileName, float quality) throws Exception {
        // 目标文件
        File resizedFile = new File(fileName);
        // 压缩
        Image targetImage = ImageIO.read(resizedFile);
        int width = targetImage.getWidth(null);
        int height = targetImage.getHeight(null);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.drawImage(targetImage, 0, 0, width, height, null);
        g.dispose();

        String ext = CommonUtil.getFileType(resizedFile.getName());
        // 如果是jpg
        if ("jpg".equals(ext) || "jpeg".equals(ext)) {
            // jpeg格式的对输出质量进行设置
            FileOutputStream out = new FileOutputStream(resizedFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
            jep.setQuality(quality, false);
            encoder.setJPEGEncodeParam(jep);
            encoder.encode(image);
            out.close();
        } else {
            ImageIO.write(image, ext, resizedFile);
        }

    }

    /**
     * 去除Exif信息，可减小文件大小
     *
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @throws Exception
     */
    public static void removeProfile(String srcImagePath, String destImagePath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.profile("*");
        op.addImage(CommonUtil.createDirectory(destImagePath));
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        cmd.run(op);
    }


    /**
     * 将图片分割为若干小图
     *
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param width 指定宽度（默认为完整宽度）
     * @param height 指定高度（默认为完整高度）
     * @return 小图路径
     * @throws Exception
     */
    public static java.util.List<String> subsection(String srcImagePath, String destImagePath, Integer width,
                                                    Integer height) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcImagePath);
        op.crop(width, height);
        op.addImage(CommonUtil.createDirectory(destImagePath));

        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        cmd.run(op);

        return getSubImages(destImagePath);
    }

    /**
     * 旋转图片
     *
     * @param srcImagePath 源图片路径
     * @param destImagePath 目标图片路径
     * @param angle 旋转的角度
     * @return
     * @throws Exception
     */
    public static void rotate(String srcImagePath, String destImagePath, Double angle)
            throws Exception {
        File sourceFile = new File(srcImagePath);
        if (!sourceFile.exists() || !sourceFile.canRead() || !sourceFile.isFile()) {
            return;
        }

        BufferedImage buffimg = ImageIO.read(sourceFile);
        int w = buffimg.getWidth();
        int h = buffimg.getHeight();
        // 目标图片
        // if (w > h) { //如果宽度不大于高度则旋转过后图片会变大
        ImageCommand cmd = getImageCommand(CommandType.CONVERT);
        IMOperation operation = new IMOperation();
        operation.addImage(srcImagePath);
        operation.rotate(angle);
        operation.addImage(destImagePath);
        cmd.run(operation);
        // }
    }

    /**
     * 获取图片分割后的小图路径
     *
     * @param destImagePath 目标图片路径
     * @return 小图路径
     */
    private static java.util.List<String> getSubImages(String destImagePath) {
        // 文件所在目录
        String fileDir = destImagePath.substring(0, destImagePath.lastIndexOf(File.separatorChar));
        // 文件名称
        String fileName = destImagePath
                .substring(destImagePath.lastIndexOf(File.separatorChar) + 1);
        // 文件名（无后缀）
        String n1 = fileName.substring(0, fileName.lastIndexOf("."));
        // 后缀
        String n2 = fileName.replace(n1, "");

        List<String> fileList = new ArrayList<String>();
        String path = null;
        for (int i = 0;; i++) {
            path = fileDir + File.separatorChar + n1 + "-" + i + n2;
            if (new File(path).exists()) {
                fileList.add(path);
            } else {
                break;
            }
        }
        return fileList;
    }

}