///**
// * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
// * FileName: magickUtil
// * Author: Emiya
// * Date: 2020/6/5 14:08
// * Description: ImageMagick和GraphicsMagick的工具类
// * History:
// * <author> <time> <version> <desc>
// * 作者姓名 修改时间 版本号 描述
// */
//package com.yanhua.engine.magick;
//
////import com.yanhua.consumer.utils.PropertyUtil;
//import org.im4java.core.ConvertCmd;
//import org.im4java.core.GMOperation;
//import org.im4java.process.StandardStream;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//
///**
// * 〈功能简述〉<br>
// * 〈ImageMagick和GraphicsMagick的工具类〉
// *  <p>
// * @author Emiya
// * @create 2020/6/5 14:08
// * @version 1.0.0
// */
//public class magickUtil {
//
//    /**
//     * ImageMagick的路径
//     */
//    public static String imageMagickPath = null;
//    public static String graphicsMagickPath = null;
//    //获取路径
//    static{
//        String osName = System.getProperty("os.name").toLowerCase();
//        //linux下不要设置此值，不然会报错
//        if (osName.contains("win")) {
////            imageMagickPath = PropertyUtil.getProperty("imageMagickPath");
////            graphicsMagickPath  = PropertyUtil.getProperty("GraphicsMagickPath");
//        }
//        //Properties prop = new PropertiesFile().getPropertiesFile();
//        //imageMagickPath = prop.getProperty("imageMagickPath");
//    }
//
//
//
//    /**
//     * 根据尺寸缩放图片 (-sample模式)
//     * 裁剪后保证等比缩图 （缺点：裁剪了图片的一部分）
//     * gm convert input.jpg -thumbnail "100x100^" -gravity center -extent 100x100 output_3.jpg
//     * 生成的图片大小是：100x100，还保证了比例。不过图片经过了裁剪，剪了图片左右两边才达到1:1
//     * 原则是缩放后的尺寸最少有一个是符合宽或高，且另外一个不能大于指定的参数中对应的宽或高
//     * @param width 缩放后的图片宽度
//     * @param height 缩放后的图片高度
//     * @param srcPath 源图片路径
//     * @param newPath 缩放后图片的路径
//     * @param type 1为比例处理，2为大小处理，如（比例：1024x1024,大小：50%x50%）
//     */
//    public static String zoomImageByGM(int width, int height, String srcPath, String newPath,int type, double quality) throws Exception {
//        GMOperation op = new GMOperation();
////        IMOperation op = new IMOperation();
//        ConvertCmd cmd = new ConvertCmd(true);
////        ConvertCmd cmd = new ConvertCmd(false);
//        op.addImage();
//        BufferedImage bufferedImage = ImageIO.read(new File(srcPath));
//        float width1 = bufferedImage.getWidth();
//        float height1 = bufferedImage.getHeight();
//        float scale = Math.max(width/width1, height/height1);
//        String raw = "";
//        if(type == 1){
//            //按像素
//            raw = Math.round(width1*scale)+"x"+Math.round(height1*scale)+"^";
//        }else{
//            //按像素百分比
//            raw = width+"%x"+height+"%";
//        }
//        String raws = width+"x"+height;
//        op.addRawArgs("-scale" ,  raw );
////        op.addRawArgs("-gravity","center");
////        op.addRawArgs("-extent",raws);
////        if((quality !=null && quality.equals(""))){
//////            op.addRawArgs("-quality" ,  quality );
////            op.quality(Double.valueOf(quality) );
////        }
//
//        op.quality(quality );
//        op.addRawArgs("+profile","*");
//        op.addImage();
//        cmd.setSearchPath(graphicsMagickPath);
////        ByteArrayOutputStream baos = new ByteArrayOutputStream(16*1024);
////        Pipe pipeInOut = new Pipe(new ByteArrayInputStream(pdfbytes), baos);
////        cmd.setInputProvider(pipeInOut);
////        cmd.setOutputConsumer(pipeInOut);
////        cmd.setSearchPath(imageMagickPath);
//        //判断大小，如果小于100k,不压缩；反之则压缩
//        try{
//            cmd.run(op, srcPath, newPath);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return newPath;
//    }
//    public static String jdImageByGM(String srcPath, String newPath,long desFileSize) throws Exception {
//
//        File srcFileJpg = new File(srcPath);
//        long srcFileSizeJpg = srcFileJpg.length();
//        //判断大小，如果小于100k,不压缩；反之则压缩
//        if (srcFileSizeJpg>desFileSize * 1024) {
//            double quality = 100d;
//            if (srcFileSizeJpg>0){
//                quality = Math.floor((desFileSize * 102400d) /srcFileSizeJpg);
//                System.out.println("qua="+quality);
//            }
//            GMOperation op = new GMOperation();
//            ConvertCmd cmd = new ConvertCmd(true);
//            op.addImage();
//            op.quality(98d);
//            op.addRawArgs("+profile","*");
//            op.addImage();
//
//            cmd.setSearchPath(graphicsMagickPath);
//            try{
//                cmd.run(op, srcPath, newPath);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        return newPath;
//    }
//    /**
//     * 根据尺寸缩放图片 (-sample模式)(与resize的区别在于-sample只进行了采样，没有进行插值，使用一个简单的算法生成缩略图，速度快，画质较低，适合生成100x100以下的图片所以用来生成缩略图最合适)
//     * 非等比缩图，按给定的参数缩图（缺点：长宽比会变化）
//     * gm convert input.jpg -thumbnail "100x100!" output_2.jpg  生成的图片大小是：100x100
//     * gm convert -resize 640×480 image.gif image.gif
//     * resize 和 sample模式的区别：生成的图片大小差距不大，可能原图质量大差距会打也有可能。并且再加上去掉信息+profile "*"，得到的也是一样差不多。
//     *                           都可以用！进行不等比压缩。
//     * thumbnail；跟他们的区别应该是这个只能缩图，不能扩大。
//     * 三者按照默认的质量-quality 95生成的差距不大，但100其实是跟图片大小有关的。resize的画质比两者好。
//     * 给定参数也有3种，100x100!,50%x100%（不需要加!非等比）,50%（只能等比）
//     * openmp加速：openMP 是只用于c、c++语言的可以开启多处理器并行多线程的编程语言， gm新版本是默认开启openmp,的。
//     * convert -version：Features的值为空或DPC，说明是单线程；如果Features的值是openMP，说明是多线程。
//     * @param width 缩放后的图片宽度
//     * @param height 缩放后的图片高度
//     * @param srcPath 源图片路径
//     * @param newPath 缩放后图片的路径
//     * @param type 1为比例处理，2为大小处理，如（比例：1024x1024,大小：50%x50%）
//     */
//    public static long zoomImageByGM2(int width, int height, String srcPath, String newPath,int type,
//                                        String quality,long maxSize) throws Exception {
//        long guoqu = System.currentTimeMillis();
//        GMOperation op = new GMOperation();
////        IMOperation op = new IMOperation();
//        ConvertCmd cmd = new ConvertCmd(true);
////        ConvertCmd cmd = new ConvertCmd(false);
//        op.addImage();
//        String raw = "";
//        if(type == 1){
//            //按像素
//            raw = width+"x"+height+"!";
//        }else{
//            //按像素百分比
//            raw = width+"%x"+height+"%!";
//        }
//        op.addRawArgs("-resize" ,  raw );
//        if((quality !=null && !quality.equals(""))){
//            op.addRawArgs("-quality" ,  quality);
//        }
//        //去掉多余信息可以进一步减少图片占用大小,是可以减少,但也就2、3kb吧
//        op.addRawArgs("+profile","*");
//        op.addImage();
//        cmd.setSearchPath(graphicsMagickPath);
//        //不知道干嘛用
//        cmd.setErrorConsumer(StandardStream.STDERR);
////        cmd.setSearchPath(imageMagickPath);
//        try{
//            cmd.run(op, srcPath, newPath);
//            File newExistFile = new File(newPath);
//            long size = newExistFile.length();
//            GMOperation op1;
//            int n = 0;
//            double quality1 = 95d;
//            boolean flag = true;
//            while(maxSize>0&&size>maxSize*1024){
//                ++n;
//                if (n>2){
//                    if (flag){
//                        //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
//                        quality1 = Math.floor((maxSize * 102400d) /size);
//                        quality1 = Math.min(quality1, 50d);
//                        System.out.println("质量差距过大，新quality="+quality1);
//                        flag = false;
//                    }else {
//                        quality1 = quality1/2;
//                        System.out.println("质量差距过大了，新quality="+quality1);
//                    }
//                }
//                if (n > 5){
//                    System.out.println("质量差距过大，压缩无法实现");
//                    break;
//                }
//                System.out.println("原size="+size+"需要降低质量");
//                op1 = new GMOperation();
//                op1.addImage();
//                op1.quality(quality1);
//                op1.addRawArgs("+profile","*");
//                op1.addImage();
//                cmd.run(op1,newPath,newPath);
//                size = newExistFile.length();
//                System.out.println("得到新size="+size+"简经历了"+n+"次降质");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        long now = System.currentTimeMillis();
//        System.out.println("zoomImageByGM2---------------succ,花时"+(now-guoqu));
//        return now-guoqu;
//    }
//    //获取宽度高度
//    public static long zoomImageByGM3(int width, int height, String srcPath, String newPath,
//                                        double quality,long maxSize) throws Exception {
//        width = 1279;
////        width = 914;
////        height = 720;
//        height = 1280;
//        long guoqu = System.currentTimeMillis();
//        GMOperation op = new GMOperation();
////        IMOperation op = new IMOperation();
//        ConvertCmd cmd = new ConvertCmd(true);
////        ConvertCmd cmd = new ConvertCmd(false);
//        op.addImage();
////        op.strip();//清除信息
//        BufferedImage bufferedImage = ImageIO.read(new File(srcPath));
//        float width1 = bufferedImage.getWidth();
//        float height1 = bufferedImage.getHeight();
//        float scale = Math.max(width/width1, height/height1);
////        op.scale(Math.round(width1*scale), Math.round(height1*scale),'^');
//        op.resize(Math.round(width1*scale), Math.round(height1*scale));//scale
//        op.gravity("center");
////        op.region(width,height);
//        String raws = width+"x"+height;
//        op.addRawArgs("-extent",raws);
////        op.crop(width,height);
////        op.size(100);
////        MagickConstants.GENERAL_QUALITY
//
//        //        op.addRawArgs("-gravity","center");
////        op.addRawArgs("-extent",raws);
////        op.font("Arial").fill("red").draw("text 100,100 www.taobao.com");
//        op.quality(quality);
////        op.p_profile("*");
//        op.addRawArgs("+profile","*");
//        //去掉多余信息可以进一步减少图片占用大小,是可以减少,但也就2、3kb吧
//        op.addImage();
//        cmd.setSearchPath(graphicsMagickPath);
//        //不知道干嘛用
//        cmd.setErrorConsumer(StandardStream.STDERR);
////        cmd.setSearchPath(imageMagickPath);
//        try{
//            cmd.run(op, srcPath, newPath);
//            File newExistFile = new File(newPath);
//            long size = newExistFile.length();
//            GMOperation op1;
//            int n = 0;
//            quality = 95d;
//            boolean flag = true;
//            while(size>maxSize*1024){
//                ++n;
//                if (n>2){
//                    if (flag){
//                        //则说明大概率得到的初始图质量与期望数值差距过大，而分辨率相对没有压缩很多,导致质量一直降不下来。
//                        quality = Math.floor((maxSize * 102400d) /size);
//                        quality = Math.min(quality, 50d);
//                        System.out.println("质量差距过大，新quality="+quality);
//                        flag = false;
//                    }else {
//                        quality = quality/2;
//                        System.out.println("质量差距过大了，新quality="+quality);
//                    }
//                }
//                if (n > 5){
//                    System.out.println("质量差距过大，压缩无法实现");
//                    break;
//                }
//                System.out.println("原size="+size+"需要降低质量");
//                op1 = new GMOperation();
//                op1.addImage();
//                op1.quality(quality);
//                op1.addRawArgs("+profile","*");
//                op1.addImage();
//                cmd.run(op1,newPath,newPath);
//                size = newExistFile.length();
//                System.out.println("得到新size="+size+"简经历了"+n+"次降质");
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
////        jdImageByGM(newPath,newPath,100);
//        long now = System.currentTimeMillis();
////        System.out.println("chazhi="+(now-guoqu)+"毫秒");
//        System.out.println("zoomImageByGM3---------------succ,花时"+(now-guoqu));
//        return now-guoqu;
//    }
//
//
//    /** * 根据坐标裁剪图片
//     * @param srcPath 要裁剪图片的路径
//     * @param newPath 裁剪图片后的路径
//     * @param x 起始横坐标
//     * @param y 起始挫坐标
//     * @param x1 结束横坐标
//     * @param y1 结束挫坐标
//     */
//    public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1)
//            throws Exception {
//        int width = x1 - x; int height = y1 - y;
////        IMOperation op = new IMOperation();
//        GMOperation op = new GMOperation();
//        op.addImage(srcPath);
//        /**
//         * width：裁剪的宽度
//         * height：裁剪的高度
//         * x：裁剪的横坐标
//         * y：裁剪的挫坐标
//         */
//        op.crop(width, height, x, y);
//        op.addImage(newPath);
//        ConvertCmd convert = new ConvertCmd(true);
//        //linux下不要设置此值，不然会报错
//        convert.setSearchPath(graphicsMagickPath);
////        convert.setSearchPath(imageMagickPath);
//        try{
//            convert.run(op, srcPath, newPath);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 给图片加水印
//     * @param srcPath 源图片路径
//     */
//    public static void addImgText(String srcPath) throws Exception {
////        IMOperation op = new IMOperation();
//        GMOperation op = new GMOperation();
//        op.font("宋体").gravity("southeast").pointsize(18).fill("#BCBFC8").draw("text 100,100 co188.com");
//        op.addImage();
//        op.addImage();
////        String osName = System.getProperty("os.name").toLowerCase();
//        ConvertCmd cmd = new ConvertCmd(true);
//        cmd.setSearchPath(graphicsMagickPath);
////        cmd.setSearchPath(imageMagickPath);
////        if(osName.contains("win")) {
//            //linux下不要设置此值，不然会报错
////            cmd.setSearchPath("C://Program Files//GraphicsMagick-1.3.14-Q16");
////        }
//        try{
//            cmd.run(op, srcPath, srcPath);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws Exception{
//        //cutImage("D:\\apple870.jpg", "D:\\apple870eee.jpg",98, 48, 370, 320);
//        Long start = System.currentTimeMillis();
////        String filename = "F:\\jiguang\\chenqingling\\h.jpg";
////        String newFile = "F:\\picjg\\chenqingling\\h_magick.jpg";
////        String filename = "F:\\jiguang\\chenqingling\\s.jpg";
//        String filename = "F:\\jiguang\\33.jpg";
////        String filename = "F:\\jiguang\\liehuangzhe\\s.jpg";
////        String filename = "F:\\jiguang\\liehuangzhe\\h.jpg";
////        String filename = "F:\\jiguang\\tucaodahui\\h.jpg";
////        String filename = "F:\\jiguang\\baituolebingxiang\\h.jpg";
////        String filename = "F:\\jiguang\\kaijiayongshi\\cp_s.jpg";
////        String filename = "F:\\jiguang\\kaijiayongshi\\cp_h.jpg";
////        String newFile = "F:\\picjg\\chenqingling\\s_magick.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\s_magick.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\xx.jpg";
////        String newFile = "F:\\picjg\\liehuangzhe\\h_magick.jpg";
////        String newFile = "F:\\picjg\\kaijiayongshi\\cp_s_magick.jpg";
////        String newFile1 = "F:\\picjg\\chenqingling\\s_magick_F.jpg";
////        String newFile1 = "F:\\picjg\\liehuangzhe\\s_magick_F.jpg";
////        String newFile1 = "F:\\picjg\\liehuangzhe\\h_magick_F.jpg";
////        String newFile1 = "F:\\picjg\\kaijiayongshi\\cp_h_magick_F.jpg";
//        String newFile2 = "F:\\picjg\\h_thumb.jpg";
////        String newFile2 = "F:\\picjg\\chenqingling\\h_thumb.jpg";
////        String newFile2 = "F:\\picjg\\s_thumb.jpg";
////        String newFile2 = "F:\\picjg\\liehuangzhe\\s_thumb.jpg";
////        String newFile2 = "F:\\picjg\\liehuangzhe\\h_thumb.jpg";
////        String newFile2 = "F:\\picjg\\liehuangzhe\\s_thumb_F.jpg";
////        String newFile2 = "F:\\picjg\\tucaodahui\\h_thumb_F.jpg";
////        String newFile2 = "F:\\picjg\\kaijiayongshi\\cp_s_thumb.jpg";
////        String newFile2 = "F:\\picjg\\kaijiayongshi\\cp_s_thumb_F.jpg";
////        String newFile2 = "F:\\picjg\\kaijiayongshi\\cp_h_thumb_F.jpg";
////        zoomImageByGM2(900,450, "E:\\ASUS.jpg","E:\\zzz2.jpg",1,"100");
////        zoomImageByGM3(900,450,"E:\\zzz.jpg","E:\\zzz2.jpg",100d);
////        addImgText("e:\\37AF7D10F2D8448A9A5_bak2.jpg");
////        zoomImageByGM2(220,324,filename,newFile1,1,"100");
//        zoomImageByGM2(272,203,filename,newFile2,1,"100",100);
////        FileUtil.compressPicForQuality(filename,newFile,1024,1920,1080);
////        zoomImageByGM3(500,280,filename,newFile,100d,100);
////        zoomImageByGM3(500,280,filename,newFile,100d);
////        zoomImageByGM3(220,324,filename,newFile,100d,100);
////        picTest.thumbnailsTest(220,324,filename,newFile2);
////        picTest.thumbnailsTest(500,280,filename,newFile2);
////        picTest.thumbnailsTest2(220,324,filename,newFile2);
////        picTest.thumbnailsTest2(500,280,filename,newFile2);
////        picTest.thumbnailsTest2(272,203,filename,newFile2);
//        Long end = System.currentTimeMillis();
//        System.out.println("time:"+(end-start)+"毫秒");
//    }
//
//}