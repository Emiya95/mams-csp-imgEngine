/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: ImageInfo
 * Author: Emiya
 * Date: 2020/8/3 1:35
 * Description: 图片对象类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.pojo;

import javax.validation.constraints.*;

/**
 * 〈功能简述〉<br>
 * 〈图片对象类〉
 * <p>
 *
 * @author Emiya
 * @version 1.0.0
 * @create 2020 /8/3 1:35
 */
public class ImageInfo {

    /**
     * 源文件路径
     */
    @NotEmpty(message = "源文件路径不能为空")
    private String sourceFile;
    /**
     * 目标文件路径
     */
    @NotEmpty(message = "目标文件路径不能为空")
    private String desFile;
    /**
     * 宽
     */
    @Min(value = 1,message = "目标宽度必须是数字且大于0")
    private int width;
    /**
     * 高
     */
    @Min(value = 1,message = "目标高度必须是数字且大于0")
    private int height;
    /**
     * 质量
     */
    private Integer quality;
    /**
     * 目标文件大小
     */
    private Long desFileSize;
    /**
     * 是否添加logo,具体指定哪个
     */
    private Integer logoNum;
    /**
     * Logo文件路径
     */
    private String logoFile;
    /**
     * 选择压缩方式
     */
//    @Size(max = 1,min = 0,message = "压缩方式不能为空")
    @Min(value = 0,message = "压缩方式不能为空")
    @Max(value = 1,message = "压缩方式不能为空")
    @NotNull(message = "压缩方式不能为空")
    private Integer choice;

    /**
     * 坐标定位
     */
    private Integer position;

    private Integer posX;

    private Integer posY;

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    /**
     * Gets logo num *
     *
     * @return the logo num
     */
    public Integer getLogoNum() {
        return logoNum;
    }

    /**
     * Sets logo num *
     *
     * @param logoNum logo num
     */
    public void setLogoNum(Integer logoNum) {
        this.logoNum = logoNum;
    }

    /**
     * Gets logo file *
     *
     * @return the logo file
     */
    public String getLogoFile() {
        return logoFile;
    }

    /**
     * Sets logo file *
     *
     * @param logoFile logo file
     */
    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    /**
     * Gets source file *
     *
     * @return the source file
     */
    public String getSourceFile() {
        return sourceFile;
    }

    /**
     * Sets source file *
     *
     * @param sourceFile source file
     */
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * Gets des file *
     *
     * @return the des file
     */
    public String getDesFile() {
        return desFile;
    }

    /**
     * Sets des file *
     *
     * @param desFile des file
     */
    public void setDesFile(String desFile) {
        this.desFile = desFile;
    }

    /**
     * Gets width *
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width *
     *
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height *
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height *
     *
     * @param height height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets quality *
     *
     * @return the quality
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * Sets quality *
     *
     * @param quality quality
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Long getDesFileSize() {
        return desFileSize;
    }

    public void setDesFileSize(Long desFileSize) {
        this.desFileSize = desFileSize;
    }
}