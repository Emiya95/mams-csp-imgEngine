///**
// * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
// * FileName: BootServiceListener
// * Author: Emiya
// * Date: 2020/7/30 14:36
// * Description:
// * History:
// * <author> <time> <version> <desc>
// * 作者姓名 修改时间 版本号 描述
// */
//package com.yanhua.engine.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
///**
// * 〈功能简述〉<br>
// * 〈〉
// *  <p>
// * @author Emiya
// * @create 2020/7/30 14:36
// * @version 1.0.0
// */
//@WebListener
//public class BootServiceListener implements ServletContextListener {
//    private Logger logger =  LoggerFactory.getLogger(this.getClass());
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//
////        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
////        if (!SpringContextHolder.hasContext())
////            SpringContextHolder.setSpringContext(wac);
//        System.out.println("webpath="+SystemVariables.loadOpencvSystemFile());
//        System.load(SystemVariables.loadOpencvSystemFile());
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//        // TODO Auto-generated method stub
//        logger.info("liting: contextDestroyed");
//    }
//}