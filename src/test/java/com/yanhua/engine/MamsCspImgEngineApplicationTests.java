package com.yanhua.engine;

import com.yanhua.engine.config.CommonUtil;
import com.yanhua.engine.config.ImageProperties;
import com.yanhua.engine.magick.MagickUtil;
import com.yanhua.engine.open.JavaCVUtil;
import com.yanhua.engine.open.OpenCvUtil;
import com.yanhua.engine.opencv.OpenCVUtil;
import com.yanhua.engine.service.MagickProcessingService;
import com.yanhua.engine.service.OpenCvProcessingService;
import org.bytedeco.javacpp.IntPointer;
//import org.opencv.core.Mat;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.CvType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MamsCspImgEngineApplicationTests {
//    @BeforeClass
//    public static void setupHeadlessMode() {
//        System.setProperty("java.awt.headless", "false");
//    }

    @Autowired
    private OpenCvProcessingService openCvProcessingService;
    @Autowired
    private MagickProcessingService magickProcessingService;
    @Test
    public void contextLoads() throws Exception {
        String filename = "F:\\jiguang\\20200723160310.jpg";
        String newFile = "F:\\picjg\\s_open2.jpg";
//        Mat src = Mat.zeros(bmp.getheigth(),bmp.getwidth, opencv_core.CV_8UC1);
        //读取原始图片
        Mat image = imread(filename, opencv_imgcodecs.IMREAD_UNCHANGED);
        System.out.println("cahng"+image.cols()+":");
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
        ImageProperties.LogoProperties imageProperties = CommonUtil.getLogos(0);
//        imwrite(newFile,image,new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY,100));
        //显示图片
//        JavaCVUtil.imShow(image,"跟随打工是");
//        imshow("33333ffff", image);

        //无限等待按键按下
//        waitKey(0);

//        JavaCVUtil.morphology_Dilation("C:\\Users\\49468\\Desktop\\11copy.jpg",2);



//        MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 100);
//        OpenCVUtil.resize3(960,540,filename,newFile,rate,2048);

//        boolean ret = openCvProcessingService.compressLock(640,360,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret4.jpg",100, 100L,true);
//        System.out.println("ret="+ret);
//        boolean ret1 =openCvProcessingService.compressUnlock(640,360,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret5.jpg",100, 100L,true);
//        System.out.println("ret1="+ret1);
//        boolean ret2 = magickProcessingService.compressLock(640,360,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret6.jpg",100, 120L,true);
//        System.out.println("ret2="+ret2);
//        boolean ret3 = magickProcessingService.compressUnlock(640,360,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret6.jpg",100, 100L,true);
//        System.out.println("ret3="+ret3);

//        System.out.println("bb"+MagickUtil.resizeLock2(640,360,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret6.jpg",100));
        //        magick2Util.gg();
//        CommonUtil commonUtil = new CommonUtil();

//        CommonUtil.getImage("C:\\Users\\49468\\Desktop\\logoo2.png");

//        SpringApplicationBuilder builder = new SpringApplicationBuilder(MamsCspImgEngineApplication.class);
//        builder.headless(false);
//        OpenCVUtil.ROI_AddImage3();
//        OpenCVUtil.ROI_AddImage2();
        System.out.println("succ");
    }

}
