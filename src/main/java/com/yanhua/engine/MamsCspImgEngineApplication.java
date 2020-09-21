package com.yanhua.engine;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import javax.swing.*;


@SpringBootApplication
@ServletComponentScan
public class MamsCspImgEngineApplication {
//public class MamsCspImgEngineApplication    implements CommandLineRunner {
//public class MamsCspImgEngineApplication    extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure (SpringApplicationBuilder springApplicationBuilder){
//        return springApplicationBuilder.sources(MamsCspImgEngineApplication.class);
//    }

//    public static void main(String[] args) {
////        SpringApplicationBuilder builder = new SpringApplicationBuilder(MamsCspImgEngineApplication.class);
////        builder.headless(false).run(args);
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(MamsCspImgEngineApplication.class);
//        builder.headless(false);
//        ConfigurableApplicationContext context = builder.run(args);
////        OpenCVUtil.ROI_AddImage3();
////        SpringApplication.run(MamsCspImgEngineApplication.class, args);
//    }
//    public static void main(String[] args) {
//    ApplicationContext contexto = new SpringApplicationBuilder(MamsCspImgEngineApplication.class)
//            .web(WebApplicationType.NONE)
//            .headless(false)
//            .bannerMode(Banner.Mode.OFF)
//            .run(args);
//}
//
//    @Override
//    public void run(String... args) throws Exception {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame();
//            frame.setVisible(true);
//        });
//    }

    public static void main(String[] args) {
        SpringApplication.run(MamsCspImgEngineApplication.class, args);
    }

}
