package com.yanhua.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * ==================================================
 * <p/>
 * 版权：　软件工程.net12-1 原明卓
 * <p/>
 * 项目：　基于用户兴趣标签的新闻推荐系统
 * <p/>
 * 作者：　原明卓
 * <p/>
 * 版本：　1.0
 * <p/>
 * 创建日期：　16-4-14 上午11:36
 * <p/>
 * 功能描述： 根据用户昵称生成用户头像
 * <p>
 * <p/>
 * 功能更新历史：
 * <p>
 * ==================================================
 */
public class FontImageUtil {



    /**
     * 根据文字和图片名称创建图片
     * 默认的字体　宋体加粗
     * @param str　文字
     * @param imgName　文件明
     * @throws Exception
     */
    public static void createImage(String str, String imgName) throws Exception{
//        createImage(str,new Font("宋体",Font.BOLD,40),new File(FONT_IMAGE_BASE_PATH+imgName));
    }

    /**
     * 根据str,输出目录创建图片
     * 文字样式默认为　宋体加粗
     * @param str　文字
     * @param outFile　输出目录
     * @throws Exception
     */
    public static void createImage(String str, File outFile) throws Exception{
        createImage(str,new Font("宋体",Font.BOLD,40),outFile);
    }

    /**
     * 根据str,font的样式以及输出文件目录
     * @param str 文字
     * @param font　字体样式
     * @param outFile　输出的文件及其输出的位置
     * @throws Exception
     * createImage("中华人民共和国",new Font("宋体",Font.BOLD,18),new File("e:/a.png"));
     */
    public static void createImage(String str, Font font, File outFile) throws Exception{

        ArrayList<int[]> rgbs = getRGB();
//        //获取font的样式应用在str上的整个矩形
//        Rectangle2D r=font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1),false,false));
//        int unitHeight=(int)Math.floor(r.getHeight());//获取单个字符的高度
//        //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width=120;
        int height=120;
        //创建图片
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g=image.getGraphics();
        g.setColor(new Color(rgbs.get(0)[0],rgbs.get(0)[1],rgbs.get(0)[2]));//深色
        g.fillRect(0, 0, width, height);//先用深色填充整张图片,也就是背景
        g.setColor(new Color(rgbs.get(1)[0],rgbs.get(1)[1],rgbs.get(1)[2]));//在换成浅色
        g.setFont(font);//设置画笔字体
        g.drawString(str,60,60);//画出字符串
        g.dispose();

        ImageIO.write(image, "png", outFile);//输出png图片
    }


    /**
     *  一个算法　－　判断是深颜色还是浅颜色的算法
     *
     *  $grayLevel = $R * 0.299 + $G * 0.587 + $B * 0.114;
     *    if ($grayLevel >= 192) {
     *      // add shadow
     *    }
     */
    private static ArrayList<int[]> getRGB(){
       //1.　随机生成　rgb
       //2.　判断深颜色还是浅颜色
       //3.　继续随机生成　rgb
       //4.　判读是深颜色还是浅颜色　：和第一次相反，返回两个rgb值
       //5.  颜色深的作为背景，颜色浅的作为文字；
       //6.　第一个存放深颜色，第二个存放浅颜色
        ArrayList<int[]> colorList=new ArrayList<int[]>();
        int[] rgb = getRanRGB();
        while (true){
            if(isShenRGB(rgb)){
                colorList.add(rgb);
                break;
            }else {
                rgb=getRanRGB();
            }
        }
        int[] rgbQ=getRanRGB();
        while (true){
            if(isShenRGB(rgbQ)){
                rgbQ=getRanRGB();
            }else {
                colorList.add(rgbQ);
                break;
            }
        }
        return colorList;
    }

    /**
     * 获得随机颜色
     * @return
     */
    private static int[] getRanRGB(){
        int [] colors=new int[3];
        for(int i=0;i<colors.length;i++){
            colors[i]=(int)(Math.random()*256);
        }
        return colors;
    }

    /**
     * 判断是不是深颜色
     * @return
     */
    public static boolean isShenRGB(int[] colors){
//        //使用hsv判断该像素点是否可以判定为绿色像素点
//        if (hsv[2] >= 0.075 && hsv[1] >= 0.15 && hsv[0] > 0.1389 &&
//                hsv[0] <= 0.4444) {
//            greenNumber++;
//        }
        int grayLevel = (int) (colors[0] * 0.299 + colors[1] * 0.587 + colors[2] * 0.114);
        if(grayLevel>=192){
            return true;
        }
        return false;
    }



}