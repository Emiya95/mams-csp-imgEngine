/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: openCvUtil
 * Author: Emiya
 * Date: 2020/8/2 23:04
 * Description: OpenCV的核心api工具类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.open;

import com.yanhua.engine.config.CommonUtil;
import com.yanhua.engine.config.ImageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.opencv.core.Mat;
import org.bytedeco.javacpp.IntPointer;
//import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;


import java.io.File;

import static com.yanhua.engine.config.CommonUtil.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.opencv.imgproc.Imgproc.INTER_LINEAR;

/**
 * 〈功能简述〉<br>
 * 〈OpenCV的核心api工具类〉
 *  <p>
 * @author Emiya
 * @create 2020/8/2 23:04
 * @version 1.0.0
 */
public class OpenCvUtil {



    //项目启动监听listener会自动引入默认环境下的dll动态链接库

//    private static final Log logger = LogFactory.getLog(openCvUtil.class);

    private static final Logger log = LogManager.getLogger(OpenCvUtil.class);


    /**
     *
     * @description: 根据文件路径读取并转换为Mat
     *      <p/>
     * @param fileName:
     * @param imRead:
     * @return org.opencv.core.Mat
     */
    public static Mat readMat(String fileName,Integer imRead) throws Exception {
        Mat mat =null;
        if (imRead==null){
            mat =  imread(fileName, IMREAD_UNCHANGED );
        }else {
            mat =  imread(fileName, imRead );
        }
        if (mat.empty()){
            log.error("图片路径不正确:fileName"+fileName);
            throw new Exception("图片路径不正确");
        }
        return mat;
    }

    public static Mat crop(int width, int height, Mat fileMat,int position)throws Exception{
        Mat mat;
        switch (position){
            case NORTHWEST:
                //西北左上
                mat= northWestCrop(width, height, fileMat);
                break;
            case NORTH:
                //北，中上
                mat= northCrop(width,height,fileMat);
                break;
            case NORTHEAST:
                //东北右上
                mat= northEastCrop(width, height, fileMat);
                break;
            case WEST:
                //西左中
                mat= westCrop(width, height, fileMat);
                break;
            case EAST:
                //东右中
                mat= eastCrop(width, height, fileMat);
            break;
            case SOUTHWEST:
                //西南左下
                mat= southWestCrop(width, height, fileMat);
            break;
            case SOUTH:
                //南下中
                mat= southCrop(width, height, fileMat);
            break;
            case SOUTHEAST:
                //东南右下
                mat= southEastCrop(width, height, fileMat);
            break;
            default:
                mat= centerCrop(width, height, fileMat);
                break;
        }
        return mat;
    }

    /**
     *
     * @description: 图片裁剪, 以原图中心位置作为裁剪保留区域
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return long
     */
    public static Mat centerCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width)/2,(fileMat.rows()-height)/2,width,height);
        return new Mat(fileMat,roi);
    }

    /**
     *
     * @description: 西北方向，即左上角，也是OpenCV默认的坐标点
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat northWestCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect(0,0,width,height);
        return new Mat(fileMat,roi);
    }
    
    /**
     *
     * @description: 北方向，即中上方向
     *      <p/>   
     * @param width: 
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat northCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width)/2,0,width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 东北方向，即右上方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat northEastCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width),0,width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 西方向，即左中方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat westCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect(0,(fileMat.rows()-height)/2,width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 东方向，即右中方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat eastCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width),(fileMat.rows()-height)/2,width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 西南方向，即左下方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat southWestCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect(0,(fileMat.rows()-height),width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 南方向，即下中方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat southCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width)/2,(fileMat.rows()-height),width,height);
        return new Mat(fileMat,roi);
    }
    /**
     *
     * @description: 东南方向，即右下方向
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.bytedeco.opencv.opencv_core.Mat
     */
    public static Mat southEastCrop(int width, int height, Mat fileMat) throws Exception {
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((fileMat.cols()-width),(fileMat.rows()-height),width,height);
        return new Mat(fileMat,roi);
    }

    /**
     *
     * @description:
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     * @return org.opencv.core.Mat
     */
    public static Mat resizeLock(int width, int height, Mat fileMat) throws Exception {
        if (fileMat.empty()) {
            log.error("原图读取失败:fileMat");
            throw new Exception("原图读取失败:fileMat");
        }
        float width1 = fileMat.cols();
        float height1 = fileMat.rows();
        float scale = Math.max(width/width1, height/height1);
        Mat dst = fileMat.clone();
        resize(fileMat,dst,new Size(Math.round(width1*scale),Math.round(height1*scale)),0,0, opencv_imgproc.INTER_LINEAR);
        return dst;
    }

    /**
     *
     * @description: 压缩图片（不锁定尺寸比例）
     *      <p/>
     * @param width:
     * @param height:
     * @param fileMat:
     */
    public static Mat resizeUnlock(int width, int height, Mat fileMat) throws Exception {
        if (fileMat.empty()) {
            log.error("原图读取失败:fileMat");
            throw new Exception("原图读取失败:fileMat");
        }
        Mat dst = fileMat.clone();
        resize(fileMat,dst,new Size(width,height));
        return dst;
    }

    /**
     *
     * @description: 添加logo水印
     *      <p/>
     * @param fileMat:
     * @return void
     */
    public static boolean addImgWatermark(int logoNum ,Mat fileMat) throws Exception {
        try {
            if (fileMat.empty()){
                log.error("原图读取失败:fileMat");
                throw new Exception("原图读取失败:fileMat");
            }
            ImageProperties.LogoProperties logoProperties = CommonUtil.getLogos(logoNum);
            int w = logoProperties.getWatermarkWidth();
            int h = logoProperties.getWatermarkHeight();
            Rect rect =new Rect(10+33,10+5,w,h);
            log.info("w,h"+w+":"+h);
            //取出原图需要被logo图覆盖的区域
            Mat logoRoi = new Mat(fileMat,rect);
            log.info(logoRoi.cols()+"=="+logoRoi.rows());
            if (logoRoi.empty()){
                log.error("原图选取区域读取为null:logoRoi");
                throw new Exception("原图选取区域读取为null:logoRoi");
            }
            Mat logoMat;
            //logo图的掩模
            Mat logoMask;
            if (CommonUtil.calAreaBlack(logoRoi)){
                //选取白色字体
                logoMat = logoProperties.getWhiteWatermarkMat();
                logoMask = logoProperties.getWhiteWatermarkMatMask();
            }else {
                logoMat = logoProperties.getBlackWatermarkMat();
                //读取logo的灰度作为掩模
                logoMask = logoProperties.getBlackWatermarkMatMask();
            }
            if (logoMat.empty()){
                log.error("logoMat读取失败");
                throw new Exception("logoMat图读取失败");
            }
            if (logoMask.empty()){
                log.error("logoMatMask读取失败");
                throw new Exception("logoMatMask图读取失败");
            }
            // todo: submit  colRange
            Mat clone = fileMat.apply(new Rect(10,10,logoMat.cols(),logoMat.rows()));
            System.out.println("clone="+clone.cols()+"::"+clone.rows());
//            logoMat.copyTo(clone);
            logoMat.copyTo(clone,logoMask);
        }catch (Exception e){
            log.error("添加logo水印失败:"+e.getMessage());
            throw new Exception("添加logo水印失败:"+e.getMessage());
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
    public static boolean pollCompress(String fileName,long desFileSize) throws Exception {
        File newExistFile = new File(fileName);
        long size = newExistFile.length();
        int n = 0;
        int quality = 95;
        boolean flag = true;
        while (desFileSize>0.0&&desFileSize*1024<size){
            ++n;
            if (n>2){
                if (flag){
                    //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
                    quality = (int) Math.floor((desFileSize * 102400d) /size);
                    quality = Math.min(quality, 50);
                    log.info("质量差距明显，折半新quality="+quality);
                    flag = false;
                }else {
                    quality = quality/2;
                    log.info("质量差距过大，新quality="+quality);
                }
            }
            if (n > 5){
                log.warn("质量差距超过范围，压缩无法实现:fileName"+fileName);
                return false;
            }
            IntPointer rate = new IntPointer(IMWRITE_JPEG_QUALITY,quality);
//            MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
            Mat src2 = imread(fileName);
            imwrite(fileName,src2,rate);
            size = newExistFile.length();
        }
        return true;
    }

    //*************************************直接路径作为参数暂时不用  *******************************
    /**
     *
     * @description: 保持原图尺寸比进行压缩
     *      <p/> （优先选择比例越小的作为scale进行压缩）Scala = 需求图宽:原图宽
     * @param width:
     * @param height:
     * @param fileName:
     * @param newFile:
     * @param matOfInt: 质量保证参数
     */
    public static boolean resizeLock(int width, int height, String fileName, String newFile, IntPointer matOfInt) throws Exception {
        Mat src = imread(fileName, IMREAD_UNCHANGED );
        if (src.empty()) {
            log.error("图片路径不正确:fileName"+fileName);
            throw new Exception("图片路径不正确");
        }
        float width1 = src.cols();
        float height1 = src.rows();
        float scale = Math.max(width/width1, height/height1);
        Mat dst = src.clone();
        resize(src,dst,new Size(Math.round(width1*scale),Math.round(height1*scale)),0,0, INTER_LINEAR);
        return imwrite(newFile, dst,matOfInt);
    }


    /**
     *
     * @description: 图片裁剪, 以原图中心位置作为裁剪保留区域
     *      <p/>
     * @param width:
     * @param height:
     * @param fileName:
     * @param newFile:
     * @param matOfInt:
     * @return long
     */
    public static boolean centerCrop(int width, int height, String fileName, String newFile, IntPointer matOfInt) throws Exception {
        Mat src = imread(fileName, IMREAD_UNCHANGED );
        if (src.empty()) {
            log.error("图片路径不正确:fileName"+fileName);
            throw new Exception("图片路径不正确");
        }
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
        Rect roi = new Rect((src.cols()-width)/2,(src.rows()-height)/2,width,height);
        Mat dst2=new Mat(src,roi);
        return imwrite(newFile, dst2,matOfInt);
    }

    /**
     *
     * @description: 压缩图片（不锁定尺寸比例）
     *      <p/>
     * @param width:
     * @param height:
     * @param fileName:
     * @param newFile:
     * @param matOfInt: 质量保证参数
     */
    public static boolean resizeUnlock(int width, int height, String fileName, String newFile, IntPointer matOfInt) throws Exception {
        Mat src = imread(fileName);
        if (src.empty()) {
            log.error("图片路径不正确:fileName"+fileName);
            throw new Exception("图片路径不正确");
        }
        Mat dst = src.clone();
        resize(src,dst,new Size(width,height));
        return imwrite(newFile, dst,matOfInt);
    }




}