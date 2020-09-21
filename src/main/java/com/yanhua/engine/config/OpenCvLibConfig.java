///**
// * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
// * FileName: OpenCvLibConfig
// * Author: Emiya
// * Date: 2020/7/30 17:51
// * Description: OpenCV指定预加载链接库
// * History:
// * <author> <time> <version> <desc>
// * 作者姓名 修改时间 版本号 描述
// */
//package com.yanhua.engine.config;
//
//import org.opencv.core.Core;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 〈功能简述〉<br>
// * 〈OpenCV指定预加载链接库〉
// *  <p>
// * @author Emiya
// * @create 2020/7/30 17:51
// * @version 1.0.0
// */
//@Configuration
//@EnableConfigurationProperties(OpenCvProperties.class)
//public class OpenCvLibConfig {
//    @Autowired
//    private OpenCvProperties openCvProperties;
//    @Bean
//    public void init() {
////        System.load(path);
//        System.out.println("gggg=path="+openCvProperties.getPath());
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//    }
//
//
//}