/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: PicProcessingService
 * Author: Emiya
 * Date: 2020/8/3 0:59
 * Description: 图片处理服务类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.service;

/**
 * 〈功能简述〉<br>
 * 〈图片处理服务接口〉
 *  <p>
 * @author Emiya
 * @create 2020/8/3 0:59
 * @version 1.0.0
 */
public interface OpenCvProcessingService {

    /**
     *
     * @description: 等比压缩
     *      <p/>
     * @param logoNum: 水印
     * @param position: 坐标
     * @param width: 宽
     * @param height: 高
     * @param sourceFile: 来源文件
     * @param desFile: 目标文件
     * @param quality: 质量
     * @param desFileSize: 目标文件大小
     * @return boolean : 结果
     * @throws Exception: 压缩失败
     */
    boolean compressLock(Integer logoNum,int position,int width, int height, String sourceFile, String desFile,Integer quality,Long desFileSize);

    /**
     *
     * @description: 非等比压缩
     *      <p/>
     * @param logoNum: 水印
     * @param width: 宽
     * @param height: 高
     * @param sourceFile: 来源文件
     * @param desFile: 目标文件
     * @param quality: 质量
     * @param desFileSize: 目标文件大小
     * @return boolean
     * @throws Exception: 压缩失败
     */
    boolean compressUnlock(Integer logoNum,int width, int height, String sourceFile, String desFile,Integer quality,Long desFileSize);

    /**
     *
     * @description: 降低图片质量
     *      <p/>
     * @param sourceFile:
     * @param desFileSize:
     * @return boolean
     */
    boolean reduceQuality(String sourceFile,Long desFileSize) throws Exception;

    boolean crop();
}