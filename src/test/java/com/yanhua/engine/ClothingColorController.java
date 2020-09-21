package com.yanhua.engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coobird.thumbnailator.Thumbnails;

public class ClothingColorController {
    // 颜色标准数（固定差值）
    public float colorNum = 51.0f;

    // 颜色标准（倍数关系）
    public static Map<Integer, String> colorStandard = new HashMap<Integer, String>();
    static {
        colorStandard.put(0, "00");
        colorStandard.put(1, "33");
        colorStandard.put(2, "66");
        colorStandard.put(3, "99");
        colorStandard.put(4, "cc");
        colorStandard.put(5, "ff");
    }

    // 十进制转十六进制
    @SuppressWarnings({ "static-access", "deprecation" })
    public String tenToHex(int x) {
        Integer ten = new Integer(x);
        String hex = ten.toHexString(ten);
        return hex;
    }

    // 十六进制转十进制
    public Integer hexToTen(String hex) {
        Integer x = Integer.parseInt(hex, 16);
        return x;
    }

    // 将十进制形式rgb转为符合标准颜色的十六进制字符串形式
    public String changeRgb(int r, int g, int b) {
        // 通过颜色标准数计算颜色标准key
        int rk = Math.round(Float.parseFloat(r + "") / colorNum);
        int gk = Math.round(Float.parseFloat(g + "") / colorNum);
        int bk = Math.round(Float.parseFloat(b + "") / colorNum);
        // 通过颜色标准key取出标准颜色
        String rs = colorStandard.get(rk);
        String gs = colorStandard.get(gk);
        String bs = colorStandard.get(bk);
        return rs + gs + bs;
    }

    // 将BufferImage取出来的rgb int 值转换为三位数的rgb
    public int[] changeToRgb(int pixel) {
        int[] rgb = new int[3];
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);
        return rgb;
    }

    @SuppressWarnings("deprecation")
    public void recognition(String input) {
        String path = input.substring(input.indexOf("=") + 1, input.length());

        try {
            int[] rgb = new int[3];
            BufferedImage bi = Thumbnails.of(path).size(100, 100).asBufferedImage();
            Map<String, Integer> colorCount = new HashMap<String, Integer>();
            // 统计图中颜色标准的数量分布情况
            for (int w = 0; w < bi.getWidth(); w = w + 2) {
                for (int h = 0; h < bi.getHeight(); h = h + 2) {
                    // 取出每一个像素点的rgb值
                    rgb = changeToRgb(bi.getRGB(w, h));
                    // 将rbg转换为符合颜色标准的十六进制形式
                    String hexColor = changeRgb(rgb[0], rgb[1], rgb[2]);
                    // 统计下每一个颜色对应的像素数量
                    Integer count = colorCount.get(hexColor);
                    if (count == null) {
                        count = new Integer(0);
                    }
                    count++;
                    colorCount.put(hexColor, count);
                }
            }
            Map<Integer, String> colorCountRev = new HashMap<Integer, String>();
            List<Integer> countList = new ArrayList<Integer>();
            for (String key : colorCount.keySet()) {
                colorCountRev.put(colorCount.get(key), key);
                countList.add(colorCount.get(key));
            }
            // 排序
            Collections.sort(countList);
            int length = countList.size();
            if (length > 6) {
                length = 6;
            }

            // 取出数量最多的前五种颜色
            String colors = "";
            for (int i = 1; i <= length; i++) {
                colors = String.valueOf(colorCountRev.get(countList.get(countList.size() - i)));
                //输出结果
                System.out.println(colors);
            }
            return;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}