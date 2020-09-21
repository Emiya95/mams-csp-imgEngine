/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: PicProcessingServiceImpl
 * Author: Emiya
 * Date: 2020/8/3 1:00
 * Description: 图片处理服务类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.service.impl;

import com.yanhua.engine.config.CommonUtil;
import com.yanhua.engine.open.OpenCvUtil;
import com.yanhua.engine.service.OpenCvProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Service;

import static org.bytedeco.opencv.global.opencv_imgcodecs.IMWRITE_JPEG_QUALITY;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;

/**
 * 〈功能简述〉<br>
 * 〈图片处理服务类〉
 *  <p>
 * @author Emiya
 * @create 2020/8/3 1:00
 * @version 1.0.0
 */
@Service
public class OpenCvProcessingServiceImpl implements OpenCvProcessingService {

    private static final Logger log = LogManager.getLogger(OpenCvProcessingServiceImpl.class);


    @Override
    public boolean compressLock(Integer logoNum ,int position,int width, int height, String sourceFile, String desFile, Integer quality,Long desFileSize) {
        try {
            //MatOfInt用来设置质量 jpg 0-100(最大100), png 0-9（最大是0）
//            MatOfInt rate;
            IntPointer rate;
            if (quality==null||quality<=0){
                rate = new IntPointer(IMWRITE_JPEG_QUALITY, 100);
            }else {
                rate = new IntPointer(IMWRITE_JPEG_QUALITY, quality);
            }
            //读取
            Mat sourceMat = OpenCvUtil.readMat(sourceFile,null);
            //等比压缩
            sourceMat = OpenCvUtil.resizeLock(width,height,sourceMat);
            //裁剪,默认就选择中心坐标
            sourceMat = OpenCvUtil.crop(width,height,sourceMat,position);
            if (logoNum!=null&&logoNum>-1){
                //添加logo水印
                OpenCvUtil.addImgWatermark(logoNum,sourceMat);
            }
            //生成
            boolean ret = imwrite(CommonUtil.createDirectory(desFile),sourceMat,rate);
//            boolean ret = Imgcodecs.imwrite(desFile,sourceMat,rate);
            if (desFileSize!=null&&desFileSize>0){
                //质量压缩
                ret =  OpenCvUtil.pollCompress(desFile, desFileSize);
            }
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean compressUnlock(Integer logoNum,int width, int height, String sourceFile, String desFile, Integer quality,Long desFileSize){
        try{
            //MatOfInt用来设置质量 jpg 0-100(最大100), png 0-9（最大是0）
            IntPointer rate;
            if (quality==null||quality<=0){
                rate = new IntPointer(IMWRITE_JPEG_QUALITY, 100);
            }else {
                rate = new IntPointer(IMWRITE_JPEG_QUALITY, quality);
            }
            //读取
            Mat sourceMat = OpenCvUtil.readMat(sourceFile,null);
            //非等比压缩
            sourceMat = OpenCvUtil.resizeUnlock(width, height, sourceMat);
            if (logoNum!=null&&logoNum>-1){
                //添加logo水印
                OpenCvUtil.addImgWatermark(logoNum,sourceMat);
            }
            //生成
            boolean ret = imwrite(CommonUtil.createDirectory(desFile),sourceMat,rate);
            if (desFileSize!=null&&desFileSize>0){
                //质量压缩
                ret = OpenCvUtil.pollCompress(desFile, desFileSize);
            }
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean reduceQuality(String sourceFile, Long desFileSize) throws Exception {
        return OpenCvUtil.pollCompress(sourceFile, desFileSize);
    }

    @Override
    public boolean crop() {
        return false;
    }
}