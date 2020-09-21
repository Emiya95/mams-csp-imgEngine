/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: MagickProcessingServiceImpl
 * Author: Emiya
 * Date: 2020/8/5 11:57
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.service.impl;

import com.yanhua.engine.config.CommonUtil;
import com.yanhua.engine.magick.MagickUtil;
import com.yanhua.engine.service.MagickProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 〈功能简述〉<br>
 * 〈〉
 *  <p>
 * @author Emiya
 * @create 2020/8/5 11:57
 * @version 1.0.0
 */
@Service
public class MagickProcessingServiceImpl implements MagickProcessingService {

    private static final Logger log = LogManager.getLogger(MagickProcessingServiceImpl.class);


    @Override
    public boolean compressLock(Integer logoNum,int position,int width, int height, String sourceFile, String desFile, Integer quality, Long desFileSize) {
        boolean ret =false;
        try{
            if (quality==null||quality<=0){
                quality = 100;
            }
            ret =MagickUtil.resizeLock(width,height,sourceFile,desFile,quality.doubleValue(),position);
            if (ret){
                if (logoNum!=null&&logoNum>-1){
                    //加水印
                    //透明度默认给100
                    //todo:根据选择的具体logo作为水印
                    MagickUtil.addImgWatermark(logoNum,desFile,desFile,100);
                }
                if (desFileSize!=null&&desFileSize>0){
                    //质量压缩
                    ret =  MagickUtil.pollCompress(desFile, desFileSize);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    @Override
    public boolean compressUnlock(Integer logoNum,int width, int height, String sourceFile, String desFile, Integer quality, Long desFileSize) {
        boolean ret =false;
        try{
            if (quality==null||quality<=0){
                quality = 100;
            }
            ret = MagickUtil.resizeUnlock(width,height,sourceFile,desFile,1,quality.toString());
            if (ret){
                if (logoNum!=null&&logoNum>-1){
                    //加水印
                    //透明度默认给100
                    MagickUtil.addImgWatermark(logoNum,desFile,desFile,100);
                }
                if (desFileSize!=null&&desFileSize>0){
                    //质量压缩
                    ret =  MagickUtil.pollCompress(desFile, desFileSize);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    @Override
    public boolean reduceQuality(String sourceFile, Long desFileSize) throws Exception {
        return MagickUtil.pollCompress(sourceFile,desFileSize);
    }

    @Override
    public boolean crop() {
        return false;
    }
}