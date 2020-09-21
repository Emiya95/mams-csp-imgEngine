package com.yanhua.engine.open;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
 
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplConvKernel;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;

/**功能说明：JavaCV1.1 mat读取/显示工具类
 * @author:linghushaoxia
 * @time:2016年3月31日上午8:49:25
 * @version:1.0
 * 为中国羸弱的技术,
 * 撑起一片自立自强的天空!
 * 
 */
public class JavaCVUtil {
   
  /**
   * 
   * 功能说明:显示图像
   * @param mat
   * 要显示的mat类型图像
   * @param title
   * 窗口标题
   * @time:2016年3月31日下午1:28:01
   * @author:linghushaoxia
   * @exception:
   *
   */
  public static void imShow(Mat mat, String title) {
    ToMat converter = new OpenCVFrameConverter.ToMat();
    CanvasFrame canvas = new CanvasFrame(title, 1);
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    canvas.showImage(converter.convert(mat));
    
  }
  /***
   * 
   * 功能说明:显示图像
   * @param filePath
   * 图像路径(可以包含中文)
   * @param title 
   * 窗口标题(可以包含中文)
   * @time:2016年3月31日下午1:26:05
   * @author:linghushaoxia
   * @exception:
   *
   */
  public static void show(String filePath,String title) {
      Mat mat= imRead(filePath);
      imShow(mat, title);
   }
 
  
  /**
   * 
   * 功能说明:读取图像
   * @param filePath
   * 文件路径,可以包含中文
   * @return Mat
   * @time:2016年3月31日下午1:26:51
   * @author:linghushaoxia
   * @exception:
   *
   */
  public static Mat imRead(String filePath){
      Mat mat = null;
      try {
	  //使用java2D读取图像
	  Image image = ImageIO.read(new File(filePath));
	  /**
	   * 转为mat
	   * 1、由Java2D的image转为javacv自定义的Frame
	   * 2、由Frame转为Mat
	   */
	  Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
	  Frame frame = java2dFrameConverter.convert((BufferedImage) image);
	  ToMat converter = new OpenCVFrameConverter.ToMat();
	  mat = converter.convert(frame);
	  
    } catch (Exception e) {
	System.out.println("读取图像出现异常：filePath="+filePath);
	e.printStackTrace();
    }
      return mat;
  }
  /**
   * 
   * 功能说明:保存mat到指定路径
   * @param mat
   * 要保存的Mat
   * @param filePath 
   * 保存路径
   * @time:2016年3月31日下午8:39:50
   * @author:linghushaoxia
   * @exception:
   *
   */
  public static boolean imWrite(Mat mat,String filePath){
     try {
	 /**
	  * 将mat转为java的BufferedImage
	  */
	  ToMat convert= new ToMat();
	  Frame frame= convert.convert(mat);
	  Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
	  BufferedImage bufferedImage= java2dFrameConverter.convert(frame);
	  ImageIO.write(bufferedImage, "PNG", new File(filePath));
	  
	  return true;
    } catch (Exception e) {
	System.out.println("保存文件出现异常:"+filePath);
	e.printStackTrace();
    }
  return false;
  }

    //扩张，将目标的边缘的“毛刺”踢除掉
    public static void morphology_Dilation(String filename,int dilation_elem)
    {
        IplImage src;
        IplImage dilation_dst;
        src = cvLoadImage(filename);
        dilation_dst=src;
        int dilation_type=CV_SHAPE_RECT;
        if( dilation_elem == 0 ){ dilation_type = CV_SHAPE_RECT; }
        else if( dilation_elem == 1 ){ dilation_type = CV_SHAPE_CROSS; }
        else if( dilation_elem == 2) { dilation_type = CV_SHAPE_ELLIPSE; }
//        CvMat element = cvGetStructuringElement( dilation_type,
//        cvSize( 2*1 + 1, 2*1+1 ),
//        cvPoint( 2, 2 ) );
        /// Apply the dilation operation
        IplConvKernel kernel=//cvCreateStructuringElementEx(3,3,1,1,dilation_type,null);
                cvCreateStructuringElementEx(3,3,1,1,dilation_type);
        cvDilate( src, dilation_dst, kernel,1);
        cvReleaseStructuringElement( kernel );
        cvSaveImage("E:/pics/ba_morphology_Dilation_"+dilation_type+".JPG", dilation_dst);
    }
}
