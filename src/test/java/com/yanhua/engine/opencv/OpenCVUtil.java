/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: openCVUtil
 * Author: Emiya
 * Date: 2020/6/8 15:21
 * Description: openCV的工具类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.opencv;

import com.yanhua.engine.magick.MagickUtil;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.yanhua.engine.FontImageUtil.isShenRGB;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;

/**
 * 〈功能简述〉<br>
 * 〈openCV的工具类〉
 * 图像拷贝:
 * cv::Mat img = cv::imread(argv[1]);
 * cv::Mat convas = img.clone();
 * // 或者
 * img.copyTo(convas);
 *
 * cv::Mat a;
 * cv::Mat b = a;             // 浅拷贝, 只拷贝矩阵头, 数据并没有复制
 * cv::Mat c(a);              // 浅拷贝, 只拷贝矩阵头, 数据并没有复制
 * cv::Mat b = a.clone();     // 深拷贝, 矩阵头和数据都拷贝了
 * cv::Mat c;
 * a.copyTo(c);               // 深拷贝, 矩阵头和数据都拷贝了3). cv::Mat 与 CvMat 和 IplImage 的互转
 *  <p>
 * @author Emiya
 * @create 2020/6/8 15:21
 * @version 1.0.0
 */
public class OpenCVUtil {
    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.load(System.getProperty("user.dir")+"");
    }

    public static void main(String[] args) throws Exception {
        String filename = "F:\\jiguang\\20200723160310.jpg";
        String newFile = "F:\\picjg\\s_open2.jpg";
//        Mat src = Mat.zeros(bmp.getheigth(),bmp.getwidth, opencv_core.CV_8UC1);
        //读取原始图片
        org.bytedeco.opencv.opencv_core.Mat image = imread(filename, opencv_imgcodecs.IMREAD_UNCHANGED);
        System.out.println("cahng"+image.cols()+":");
        if (image.empty()) {
            System.err.println("加载图片出错，请检查图片路径！");
            return;
        }
//        ROI_AddImage3();
//        duqu();
        System.out.println("succc");
    }
////        System.out.println("welcome to opencv!"+Core.VERSION);
//        Long start = System.currentTimeMillis();
////        Mat m = new Mat(5,10, CvType.CV_8SC1,new Scalar(0));
////        System.out.println("opencv mat:"+m);
////        System.out.println("-----------------------------------------------------");
////        String filename = "E:\\images\\kkk.jpg";
////        String filename = "F:\\jiguang\\congjiahuan\\h.jpg";
////        String filename = "F:\\jiguang\\chenqingling\\h.jpg";
//        String filename = "F:\\jiguang\\20200723160310.jpg";
////        String filename = "F:\\jiguang\\chenqingling\\s.jpg";
////        String filename = "F:\\jiguang\\liehuangzhe\\s.jpg";
////        String filename = "F:\\jiguang\\liehuangzhe\\h.jpg";
////        String filename = "F:\\jiguang\\kaijiayongshi\\cp_s.jpg";
////        String filename = "F:\\jiguang\\kaijiayongshi\\cp_h.jpg";
////        String filename = "F:\\jiguang\\baituolebingxiang\\h.jpg";
////        String filename = "F:\\jiguang\\congjiahuan\\kkk.jpg";
////        String filename = "E:\\old\\ASUS.jpg";
////        String newFile = "E:\\images\\kkkopencv.jpg";
////        String newFile = "F:\\picjg\\chenqingling\\h_open.jpg";
//        String newFile = "F:\\picjg\\s_open.jpg";
////        String newFile = "F:\\picjg\\chenqingling\\s_open.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\s_open_F.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\h_open_F.jpg";
////        String newFile = "F:\\picjg\\kaijiayongshi\\cp_s_open_F.jpg";
////        String newFile = "F:\\picjg\\kaijiayongshi\\cp_h_open_F.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\h_open.jpg";
////        String newFile = "F:\\picjg\\baituolebingxiang\\h_open.jpg";
////        String newFile1 = "F:\\picjg\\chenqingling\\open_h1.jpg";
////        String newFile1 = "F:\\picjg\\chenqingling\\open_s1.jpg";
//        //matofint用来设置质量 jpg 0-100(最大100), png 0-9（最大是0）
//        MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 100);
//        MatOfInt rate2 = new MatOfInt(Imgcodecs.CV_IMWRITE_PNG_COMPRESSION, 0);
//        MatOfInt rate3 = new MatOfInt(Imgcodecs.IMWRITE_PNG_COMPRESSION, 0);
//        MatOfInt rate4 = new MatOfInt(Imgcodecs.IMWRITE_PNG_COMPRESSION, 0);
//
//
//        //按比例压缩
////        resize2(220,324,filename,newFile,rate);
////        resize2(500,280,filename,newFile,rate);
//        //以中心裁剪
////        resize(220,324,newFile,newFile,rate,100);
////        resize(500,280,newFile,newFile,rate);
//        //非等压缩
////        resize3(272,203,filename,newFile,rate,1000);
//        resize3(960,540,filename,newFile,rate,2048);
////        resize3(220,324,filename,newFile,rate,1000);
////        resize3(500,280,filename,newFile,rate,1000);
//
////        colorReduce_2(filename,newFile,1,rate);
//
////        colorReduce(filename,newFile,64,rate);
//
//        Long end = System.currentTimeMillis();
//        System.out.println("time:"+(end-start)+"毫秒");
//    }

    /**
     *
     * @description: 图片裁剪,以原图中心位置作为裁剪保留区域
     *      <p/>
     * @param :
     * @return void
     * @author: Emiya
     * @create: 2020-06-02 16:50
     * @title: resize
     */
    public static long resize(int width, int height, String fileName, String newFile, MatOfInt matOfInt, long desFileSize){
        long guoqu = System.currentTimeMillis();
        Mat src = Imgcodecs.imread(fileName, Imgcodecs.IMREAD_UNCHANGED );
        if (src.empty()) {
            System.out.println("图片路径不正确");
            return 0;
        }
//        src.cols()/rows()
        //rect这种是以定点位置进行裁剪，以图片的左下角为起始点，x y代表该点需要位移的数值，然后再按照宽高裁剪
//        Rect roi=new Rect(40,40,width,height);
        //假设原图50x50，希望裁剪到40X40,起始点应该为（5,5）,
        Rect roi = new Rect((src.width()-width)/2,(src.height()-height)/2,width,height);
        //指定图像格式大小
        src.size();
        Mat dst2=new Mat(src,roi);
//        MatOfInt rate1 = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 100);
        Imgcodecs.imwrite(newFile, dst2,matOfInt);
        File newExistFile = new File(newFile);
        long size = newExistFile.length();
        int n = 0;
        int quality = 95;
        boolean flag = true;
        while (desFileSize>0&&desFileSize*1024<size){
            ++n;
            if (n>2){
                if (flag){
                    //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
                    quality = (int) Math.floor((desFileSize * 102400d) /size);
                    quality = Math.min(quality, 50);
                    System.out.println("质量差距过大，新quality="+quality);
                    flag = false;
                }else {
                    quality = quality/2;
                    System.out.println("质量差距过大了，新quality="+quality);
                }
            }
            if (n > 5){
                System.out.println("质量差距过大，压缩无法实现");
                break;
            }
            System.out.println("原size="+size+"需要降低质量");
            MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
            Mat src2 = Imgcodecs.imread(newFile);
            Imgcodecs.imwrite(newFile,src2,rate);
            size = newExistFile.length();
            System.out.println("得到新size="+size+"简经历了"+n+"次降质");
        }
        long now = System.currentTimeMillis();
        System.out.println("resize---------------succ,花时"+(now-guoqu));
        return now-guoqu;
    }


    /**
     *
     * @description: 保持原图尺寸比进行压缩
     *      <p/> （优先选择比例越小的作为scale进行压缩）Scala = 需求图宽:原图宽
     * @param width: 
     * @param height:
     * @param fileName:
     * @param newFile:
     * @param matOfInt: 质量保证参数
     * @return long
     * @author: Emiya
     */
    public static long resize2(int width, int height, String fileName, String newFile, MatOfInt matOfInt){
//        width = 1279;
//        width = 914;
//        height = 720;
//        height = 1280;
        long guoqu = System.currentTimeMillis();
        Mat src = Imgcodecs.imread(fileName, Imgcodecs.IMREAD_UNCHANGED );
        if (src.empty()) {
            System.out.println("图片路径不正确");
            return 0;
        }
        float width1 = src.width();
        System.out.println("w="+src.cols());
        float height1 = src.height();
        System.out.println("h="+src.rows());
        float scale = Math.max(width/width1, height/height1);
        Mat dst = src.clone();
        Imgproc.resize(src,dst,new Size(Math.round(width1*scale),Math.round(height1*scale)),0,0, Imgproc.INTER_LINEAR);

//        int width2 = (int) width;
//        int height2 = (int) height;
//        Rect roi = new Rect((dst.width()-width2)/2,(dst.height()-height2)/2,width2,height2);
//        //指定图像格式大小
//        dst.size();
//        Mat dst2=new Mat(dst,roi);

        Imgcodecs.imwrite(newFile, dst,matOfInt);
        long now = System.currentTimeMillis();
        System.out.println("resize2---------------succ,花时"+(now-guoqu));
        return now-guoqu;
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
     * @return long
     * @author: Emiya
     */
    public static long resize3(int width, int height, String fileName, String newFile, MatOfInt matOfInt, long desFileSize) throws InterruptedException {
        long guoqu = System.currentTimeMillis();
//        MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 100);
        Mat src = Imgcodecs.imread(fileName);
        if (src.empty()) {
            System.out.println("图片路径不正确");
            return 0;
        }
        Mat dst = src.clone();
        Imgproc.resize(src,dst,new Size(width,height));
        Imgcodecs.imwrite(newFile, dst,matOfInt);
        File newExistFile = new File(newFile);
        long size = newExistFile.length();
        int n = 0;
        int quality = 95;
        boolean flag = true;
        while (desFileSize>0&&desFileSize*1024<size){
            ++n;
            if (n>2){
                if (flag){
                    //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
                    quality = (int) Math.floor((desFileSize * 102400d) /size);
                    quality = Math.min(quality, 50);
                    System.out.println("质量差距过大，新quality="+quality);
                    flag = false;
                }else {
                    quality = quality/2;
                    System.out.println("质量差距过大了，新quality="+quality);
                }
            }
            if (n > 5){
                System.out.println("质量差距过大，压缩无法实现");
                break;
            }
            MatOfInt rate = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
            Mat src2 = Imgcodecs.imread(newFile);
            Imgcodecs.imwrite(newFile,src2,rate);
            size = newExistFile.length();
        }
//        File srcFileJpg = new File(newFile);
//        long srcFileSizeJpg = srcFileJpg.length();
        //判断大小，如果小于100k,不压缩；反之则压缩
//        if (srcFileSizeJpg>desFileSize * 1024) {
//            int quality = 100;
//            if (srcFileSizeJpg>0){
//                quality = (int) ((desFileSize * 102400) /srcFileSizeJpg);
//                System.out.println("qua="+quality);
//            }
//            MatOfInt rate1 = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
//            Imgcodecs.imwrite(newFile, dst,rate1);
//        }
        long now = System.currentTimeMillis();
        System.out.println("resize3---------------succ,花时"+(now-guoqu));
        return now-guoqu;
    }

    public static void colorReduce(String fileName , String newFile , int div, MatOfInt matOfInt){
        long guoqu = System.currentTimeMillis();
//        long end = Core.getTickCount();
        Mat src = Imgcodecs.imread(fileName, Imgcodecs.IMREAD_UNCHANGED );
        if (src.empty()) {
            System.out.println("图片路径不正确");
            return ;
        }

        Mat dst = new Mat();
        dst.create(src.rows(),src.cols(),src.type());


        dst = src.clone();
        int rowNumber = dst.rows();
        int colNumber = dst.cols();//列数 * 通道数，等于每一行元素的个数

        for(int i=0; i< rowNumber; i++){

            for(int j=0; j<colNumber; j++){

                int channelIndex = 0;
                while (channelIndex < dst.channels()){
                    //颜色空间压缩处理

                    if(j == 0 && i < 10){
                        System.out.printf("Point[%d,%d],Channel[%d] >> %s",i,j,channelIndex+1, dst.get(i,j)[channelIndex]);
                        System.out.printf(" , after >> %s | " , (dst.get(i,j)[channelIndex] / div) * div);

                        if(channelIndex+1 == dst.channels()){
                            System.out.printf("\r\n");
                        }
                    }

                    dst.get(i,j)[channelIndex] = (dst.get(i,j)[channelIndex] / div) * div;
                    channelIndex++;
                }
            }
        }
        Imgcodecs.imwrite(newFile, dst,matOfInt);

    }

    private static void colorReduce_2(String fileName , String newFile , int div, MatOfInt matOfInt){
        Mat src = Imgcodecs.imread(fileName, Imgcodecs.IMREAD_UNCHANGED );
        if (src.empty()) {
            System.out.println("图片路径不正确");
            return ;
        }
        Mat dst = new Mat();
        dst.create(src.rows(),src.cols(),src.type());
        dst = src.clone();
        int rowNumber = dst.rows();
        int colNumber = dst.cols();//列数 * 通道数，等于每一行元素的个数

        for(int i=0; i< rowNumber; i++){

            for(int j=0; j<colNumber; j++){

                int channelIndex = 0;
                while (channelIndex < dst.channels()){
                    //颜色空间压缩处理

                    if(j == 0 && i < 10){
                        System.out.printf("Point[%d,%d],Channel[%d] >> %s",i,j,channelIndex+1, dst.get(i,j)[channelIndex]);
                        System.out.printf(" , after >> %s | " , (dst.get(i,j)[channelIndex]) / div * div);

                        if(channelIndex+1 == dst.channels()){
                            System.out.printf("\r\n");
                        }
                    }

                    dst.get(i,j)[channelIndex] = (dst.get(i,j)[channelIndex]) / div * div;
                    channelIndex++;
                }
            }
        }
        Imgcodecs.imwrite(newFile, dst,matOfInt);
    }

    /*
     * resize 是 OpenCV 里专门用来调整图像大小的函数
     *
     * 原型方法 :
     *      resize(Mat src, Mat dst, Size dsize)
     *      resize(Mat src, Mat dst, Size dsize, double fx, double fy, int interpolation)
     *
     * 参数说明
     *      src: 输入，原图像，即待改变大小的图像
     *      dst: 输出，改变大小之后的图像
     *      dsize: 输出图像的大小 Size(width,height) ，通过 new Size(0,0) 可以设置其值为0，当 dsize 为0时，按照 fx、fy 缩放图片
     *          -- 当 dsize 为 0 时，通过计算 fx、fy 来对图片进行缩放，缩放的公式为：new Size(round(fx*src.cols), round(fy*src.rows))
     *      fx: 宽度的缩放比例，当 dsize 为 0 时才生效。
     *      fy: 高度的缩放比例，当 dsize 为 0 时才生效。
     *      interpolation: int 插值方式，默认 INTER_LINEAR 线性插值
     *          -- INTER_LINEAR 线性插值
     *          -- INTER_NEAREST 最近邻插值
     *          -- INTER_AREA 区域插值（利用像素区域关系的重采样插值）
     *          -- INTER_CUBIC 三次样条插值（超过4x4像素邻域内的双三次插值)
     *          -- INTER_LANCZOS4 Lanczos 插值（超过8x8像素邻域内的Lanczos插值）
     *
     *          *** 缩小一般用 INTER_AREA,放大2种 INTER_CUBIC(效率不高，速度慢)、INTER_LANCZOS4（效率高、速度快）
     *
     * 注意：
     * dsize 与 fx，fy 分别为2种模式，必须启用一种。要么自定义大小（dszie），要么按照比例（fx,fy）; 也就是说，dsize、fx、fy 3者不能同时都为 0。
     *
     */

//    /**
//     * 图像缩小
//     */
////    @Test
//    public void testResize_small(){
//
//        // 读取彩色图
//        Mat sourceImage = Imgcodecs.imread(this.p_test_file_path + "/5cent.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
//
//        // 定义一个比原图小的 mat
//        Mat dstImage = Mat.ones(400,400,CvType.CV_8UC3);
//        // 会根据输出图像 dstImage的大小，自动计算出 fx，fy，进行缩放
//        Imgproc.resize(sourceImage,dstImage,dstImage.size());
//        this.saveImage(this.save_dir + "/image_process_resize_small_1.png",dstImage);
//
//        // 等比缩放
//        dstImage = Mat.ones(sourceImage.rows()/3,sourceImage.cols()/3,CvType.CV_8UC3);
//        Imgproc.resize(sourceImage,dstImage,dstImage.size());
//        this.saveImage(this.save_dir + "/image_process_resize_small_2.png",dstImage);
//
//        // 指定比例的缩放
//        dstImage = new Mat();
//        Imgproc.resize(sourceImage,dstImage,new Size(0,0) , 0.2, 0.4 ,Imgproc.INTER_AREA);
//        this.saveImage(this.save_dir + "/image_process_resize_small_3.png",dstImage);
//
//
//        //比较插值不同，缩小后的效果
//        Mat src1 = new Mat();
//        sourceImage.copyTo(src1);
//
//        Mat src2 = new Mat();
//        sourceImage.copyTo(src2);
//
//        Mat dstImage1 = null,dstImage2 = null;
//        for(int i=0; i<3; i++){
//            dstImage1 = Mat.ones(src1.rows()/2,src1.cols()/2,CvType.CV_8UC3);
//            dstImage2 = Mat.ones(src2.rows()/2,src2.cols()/2,CvType.CV_8UC3);
//            // 线性插值
//            Imgproc.resize(src1,dstImage1,dstImage1.size(),0,0,Imgproc.INTER_LINEAR);
//            // 区域插值
//            Imgproc.resize(src2,dstImage2,dstImage2.size(),0,0,Imgproc.INTER_AREA);
//            dstImage1.copyTo(src1);
//            dstImage2.copyTo(src2);
//        }
//        //输出内容可以放大看看效果
//        this.saveImage(this.save_dir + "/image_process_resize_small_Linear.png",dstImage1);
//        this.saveImage(this.save_dir + "/image_process_resize_small_area.png",dstImage2);
//    }
//
//    /**
//     * 图像放大
//     */
////    @Test
//    public void testResize_big(){
//
//        // 读取彩色图
//        Mat sourceImage = Imgcodecs.imread(this.p_test_file_path + "/5cent.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
//
//        // 定义一个比原图大的 mat
//        Mat dstImage = Mat.ones(1000,1000,CvType.CV_8UC3);
//        // 会根据输出图像 dstImage的大小，自动计算出 fx，fy，进行缩放
//        Imgproc.resize(sourceImage,dstImage,dstImage.size());
//        this.saveImage(this.save_dir + "/image_process_resize_big_1.png",dstImage);
//
//        // 等比放大
//        dstImage = Mat.ones((int)(sourceImage.rows()*1.3),(int)(sourceImage.cols()*1.3),CvType.CV_8UC3);
//        Imgproc.resize(sourceImage,dstImage,dstImage.size());
//        this.saveImage(this.save_dir + "/image_process_resize_big_2.png",dstImage);
//
//        // 指定比例的放大
//        dstImage = new Mat();
//        Imgproc.resize(sourceImage,dstImage,new Size(0,0) , 1.3, 1.8 ,Imgproc.INTER_CUBIC);
//        this.saveImage(this.save_dir + "/image_process_resize_big_3.png",dstImage);
//
//
//        //比较插值不同，放大后的效果
//        Mat src1 = new Mat();
//        sourceImage.copyTo(src1);
//
//        Mat src2 = new Mat();
//        sourceImage.copyTo(src2);
//
//        Mat src3 = new Mat();
//        sourceImage.copyTo(src3);
//
//        Mat dstImage1 = null,dstImage2 = null,dstImage3 = null;
//        for(int i=0; i<6; i++){
//
//            dstImage1 = Mat.ones((int)(src1.rows()*1.3),(int)(src1.cols()*1.3),CvType.CV_8UC3);
//            dstImage2 = Mat.ones((int)(src2.rows()*1.3),(int)(src2.cols()*1.3),CvType.CV_8UC3);
//            dstImage3 = Mat.ones((int)(src3.rows()*1.3),(int)(src3.cols()*1.3),CvType.CV_8UC3);
//
//            // 线性插值
//            Imgproc.resize(src1,dstImage1,dstImage1.size(),0,0,Imgproc.INTER_LINEAR);
//            // 三次条插值
//            Imgproc.resize(src2,dstImage2,dstImage2.size(),0,0,Imgproc.INTER_CUBIC);
//            // Lanczos插值
//            Imgproc.resize(src3,dstImage3,dstImage3.size(),0,0,Imgproc.INTER_LANCZOS4);
//
//            dstImage1.copyTo(src1);
//            dstImage2.copyTo(src2);
//            dstImage3.copyTo(src3);
//        }
//
//        //输出内容可以放大看看效果，看看那种放大要平滑点
//        this.saveImage(this.save_dir + "/image_process_resize_big_linear.png",dstImage1);
//        this.saveImage(this.save_dir + "/image_process_resize_big_cubic.png",dstImage2);
//        this.saveImage(this.save_dir + "/image_process_resize_big_lanczos.png",dstImage3);
//
//    }

    public static void ROI_AddImage2(){
        Mat image = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\0801.png",Imgcodecs.IMREAD_COLOR);
        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\logoo11.png",Imgcodecs.IMREAD_COLOR);
        Mat clone = image.clone();//复制一层，主要是为了获得原来图像的大小。也可以自己创建。
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\2020clone.jpg",clone);
        Rect rect_logo = new Rect(0,0,logo.width(),logo.height());//设置 logo 图片的剪切位置以及大小，即左上角的坐标为(150,150)，宽为220，高为200
        Rect rect_image = new Rect(150,150,logo.width(),logo.height());//设置image图片的剪切位置及大小，这里注意：两个剪切的大小大小要一致

        Mat logoRoi = new Mat(logo,rect_logo);//对图片进行剪切
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\2020logo.jpg",logoRoi);

        Mat imageRoi = new Mat(image,rect_logo);//对图片进行剪切
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\2020imag.jpg",imageRoi);


        Mat dstRoi=new Mat(clone,rect_logo);

//        Mat cloneRoi = new Mat(clone,rect_image);
//        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202001.jpg",cloneRoi);

        Core.add(logoRoi, imageRoi, clone);//将图片混入，如果图片大小不一样会报错


//        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202002.jpg",clone);
//        new ShowImage(clone);//显示图片，ShowImage 是自己写的显示图片的工具类

//        logoRoi.copyTo(clone);//将图片替换
        clone.copyTo(logoRoi);//将图片替换
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202003.jpg",clone);
//        new ShowImage(clone);


    }
    public static void ROI_AddImage3() throws Exception {
//        Mat image = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\0731.jpg",Imgcodecs.IMREAD_UNCHANGED);
        Mat image = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\11.png",Imgcodecs.IMREAD_UNCHANGED);
        Mat image2 = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\11.png",0);
        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\wanquansmall.png");
//        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\black1.bmp");
//        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\logoo1.png",Imgcodecs.IMREAD_UNCHANGED);
//        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\2013.bmp");
//        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\logosec2.bmp");
//        Mat logo = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\logo2.jpg");
//        Mat logomask = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\logosec2.bmp",0);
        //加载掩模，必须是灰度图，所以为0
        Mat logomask = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\wanquansmall.png",0);
//        Mat logomask = Imgcodecs.imread("C:\\Users\\49468\\Desktop\\2013.bmp",0);
        Mat clone = image.clone();//复制一层，主要是为了获得原来图像的大小。也可以自己创建。
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202007.png",logo);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\2020clone.jpg",clone);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\2020mask.jpg",logomask);
//        Mat ff = image.submat(0,29,0,83);//bai
//        Mat ff = image.submat(0,57,0,100);//hei
        Mat ff = image.submat(new Rect(0,0,logo.width(),logo.height()));//hei
//        Mat ff = image.submat(0,203,0,272);
        logo.copyTo(ff,logomask);
//        logo.copyTo(ff);

        //定义文本输入点
//        Point point = new Point(50.0,image.rows()/2);
//        Imgproc.putText(image,"极光TV",point,Core.FONT_HERSHEY_SIMPLEX,0.8,new Scalar(50,60,80),2);
//        Core.addWeighted(ff,1.0,logo,1.0,0.0,ff);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\11copy.jpg",image);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\11copy22.jpg",image2);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202005.png",logo);
        Imgcodecs.imwrite("C:\\Users\\49468\\Desktop\\202006.png",ff);
//        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5));




        //close processing ，连通区域
//        Imgproc.morphologyEx(desImgMat, desImgMat, Imgproc.MORPH_CLOSE, kernel);
//        Images.showImage(image,"");

//        imshow("gggg",image);

//        if (image.empty()){
//            throw new Exception("为空");
//        }
//        HighGui.imshow("正常",image);
//
//        //这行一定要加，不然窗口不会显示
//        HighGui.waitKey();

//        magick2Util.addImgWatermark("C:\\Users\\49468\\Desktop\\11.png","C:\\Users\\49468\\Desktop\\11copy2.png",100);
    }


    public static void duqu() throws IOException {
        BufferedImage bi = ImageIO.read(new File("C:\\Users\\49468\\Desktop\\logoo1.png"));
//        BufferedImage bi = ImageIO.read(new File("C:\\Users\\49468\\Desktop\\tim.jpg"));
        // 当前像素点
        int pixel = bi.getRGB(75,0);
        System.out.println("pixel="+pixel);
        int[] rgb = new int[3];// 定义RGB空间
        float[] hsv = new float[3];// 定义HSV空间
        // 获取RGB各值
        rgb[0] = (pixel & 0xff0000) >> 16;//R
        rgb[1] = (pixel & 0xff00) >> 8;//G
        rgb[2] = (pixel & 0xff);//B
        System.out.println("R:"+rgb[0]+",G:"+rgb[1]+",B:"+rgb[2]);
        // rgb转hsv
        Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv);
        System.out.println("H:"+hsv[0]+",S:"+hsv[1]+",V:"+hsv[2]);
        System.out.println((rgb[0]+rgb[1]+rgb[2])/3>128?"qian":"shen");
        System.out.println(isShenRGB(rgb)?"shen":"qian");

//        System.out.println((0.2126*rgb[0] + 0.7152*rgb[1] + 0.0722*rgb[2])<128?"black":"white");
        System.out.println((0.2126*240 + 0.7152*90 + 0.0722*188));
    }

    /**
     *
     * @description: 原本量张合并的图片减去一张图片
     *      <p/>
     * @param two_path:
     * @param one_path:
     * @param des_path:
     * @return void
     */
    public static void sub(String two_path, String one_path, String des_path) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 读取图像，不改变图像的原始信息
        Mat image1 = Imgcodecs.imread(two_path, Imgcodecs.IMREAD_UNCHANGED);
        Mat image2 = Imgcodecs.imread(one_path, Imgcodecs.IMREAD_UNCHANGED);

        Mat image3 = new Mat(image1.size(), CvType.CV_64F);
        Core.subtract(image2, image1, image3);

        Mat image5 = new Mat(image1.size(), CvType.CV_64F);
        Core.bitwise_not(image3, image5);

        Imgcodecs.imwrite(des_path, image5);
    }
}