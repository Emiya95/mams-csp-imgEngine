/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: MagickProcessController
 * Author: Emiya
 * Date: 2020/8/6 16:50
 * Description: magick的控制类
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.controller;

import com.yanhua.engine.common.dto.ResponseResult;
import com.yanhua.engine.common.Result;
import com.yanhua.engine.common.ResultFactory;
import com.yanhua.engine.pojo.ImageInfo;
import com.yanhua.engine.service.MagickProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 〈功能简述〉<br>
 * 〈magick的控制类〉
 *  <p>
 * @author Emiya
 * @create 2020/8/6 16:50
 * @version 1.0.0
 */
@RestController
@RequestMapping("/magick")
@ResponseResult
public class MagickProcessController {
    @Autowired
    private MagickProcessingService magickProcessingService;

    private static final Logger log = LogManager.getLogger(MagickProcessController.class);

    @RequestMapping(value = "/resizeLock",method = RequestMethod.POST)
    @ResponseResult
    public Result getImage(@RequestBody @Valid ImageInfo imageInfo )  throws Exception{
        if (magickProcessingService.compressLock(imageInfo.getLogoNum(),imageInfo.getPosition(),imageInfo.getWidth(),imageInfo.getHeight(),imageInfo.getSourceFile()
                ,imageInfo.getDesFile(),imageInfo.getQuality(),imageInfo.getDesFileSize())){
            return ResultFactory.toResult("0000","success");
        }
        return ResultFactory.toResult("2001","fail");
    }

    @RequestMapping(value = "/resizeUnlock",method = RequestMethod.POST)
    @ResponseResult
    public Result getImage2(@RequestBody @Valid ImageInfo imageInfo ) throws Exception {
        if (magickProcessingService.compressUnlock(imageInfo.getLogoNum(),imageInfo.getWidth(),imageInfo.getHeight(),imageInfo.getSourceFile()
                ,imageInfo.getDesFile(),imageInfo.getQuality(),imageInfo.getDesFileSize())){
//            int kk = 2/0;
//            return true;
            return ResultFactory.toResult("0000","success");
        }
        return ResultFactory.toResult("2001","fail");
//        return false;
    }

    //-----------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseResult
    @ResponseBody
    public String test() throws Exception {

        boolean ret2 = magickProcessingService.compressLock(1,1,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret12.jpg",100, 100L);
//        boolean ret2 = magickProcessingService.compressLock(0,0,640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret1.jpg",100, 100L);

        System.out.println("ret2="+ret2);
        return "hello emiya!"+ret2;
    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2() {

//        boolean ret2 = magickProcessingService.compressUnlock(1,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret11.jpg",100, 100L);
        boolean ret2 = magickProcessingService.compressUnlock(0,640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret2.jpg",100, 100L);
        System.out.println("ret2="+ret2);
        return "hello emiya2!"+ret2;
    }

    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public String test3() {
        boolean ret = magickProcessingService.compressLock(0,1,640,200,"C:\\Users\\49468\\Desktop\\202004.png","C:\\Users\\49468\\Desktop\\202004copy.jpg",100, null);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test4",method = RequestMethod.GET)
    public String test4() {
        boolean ret = magickProcessingService.compressLock(0,3,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret26.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya4!"+ret;
    }
    @RequestMapping(value = "/test5",method = RequestMethod.GET)
    public String test5() {
        boolean ret = magickProcessingService.compressLock(1,4,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret27.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test6",method = RequestMethod.GET)
    public String test6() {
        boolean ret = magickProcessingService.compressLock(1,5,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret28.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test7",method = RequestMethod.GET)
    public String test7() {
        boolean ret = magickProcessingService.compressLock(1,6,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret29.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test8",method = RequestMethod.GET)
    public String test8() {
        boolean ret = magickProcessingService.compressLock(1,7,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret30.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test9",method = RequestMethod.GET)
    public String test9() {
        boolean ret = magickProcessingService.compressLock(1,8,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret31.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }


}