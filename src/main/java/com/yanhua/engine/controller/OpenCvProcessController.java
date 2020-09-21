/**
 * Copyright (C), 2020-2020, 浙江岩华文化科技有限公司
 * FileName: OpenCv2ProcessController
 * Author: Emiya
 * Date: 2020/8/3 1:30
 * Description: OpenCV处理
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.yanhua.engine.controller;

import com.yanhua.engine.common.Result;
import com.yanhua.engine.common.ResultFactory;
import com.yanhua.engine.pojo.ImageInfo;
import com.yanhua.engine.service.OpenCvProcessingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 〈功能简述〉<br>
 * 〈OpenCV处理〉
 *  <p>
 * @author Emiya
 * @create 2020/8/3 1:30
 * @version 1.0.0
 */
@RestController
@RequestMapping("/openCv")
public class OpenCvProcessController {


    private static final Logger log = LogManager.getLogger(OpenCvProcessController.class);

    @Autowired
    private OpenCvProcessingService openCvProcessingService;


    @RequestMapping(value = "/resizeLock",method = RequestMethod.POST)
    @ResponseBody
    public Result getImage(@RequestBody @Valid ImageInfo imageInfo, BindingResult results)  {
        if (results.hasErrors()){
            List<FieldError> fieldErrors = results.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                log.error("----------------------->请求参数error field is : {} ,message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResultFactory.toResult("1000", Objects.requireNonNull(results.getFieldError()).getDefaultMessage());
        }
        if (openCvProcessingService.compressLock(imageInfo.getLogoNum(),imageInfo.getPosition(),imageInfo.getWidth(),imageInfo.getHeight(),imageInfo.getSourceFile()
                ,imageInfo.getDesFile(),imageInfo.getQuality(),imageInfo.getDesFileSize())){
            return ResultFactory.toResult("0000","success");
        }
        return ResultFactory.toResult("2001","fail");
    }

    @RequestMapping(value = "/resizeUnlock",method = RequestMethod.POST)
    @ResponseBody
    public Result getImage2(@RequestBody @Valid ImageInfo imageInfo, BindingResult results) {
        if (results.hasErrors()){
            List<FieldError> fieldErrors = results.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                log.error("----------------------->请求参数error field is : {} ,message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResultFactory.toResult("1000", Objects.requireNonNull(results.getFieldError()).getDefaultMessage());
        }
        if (openCvProcessingService.compressUnlock(imageInfo.getLogoNum(),imageInfo.getWidth(),imageInfo.getHeight(),imageInfo.getSourceFile()
                ,imageInfo.getDesFile(),imageInfo.getQuality(),imageInfo.getDesFileSize())){
            return ResultFactory.toResult("0000","success");
        }
        return ResultFactory.toResult("2001","fail");
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
//        boolean ret = openCvProcessingService.compressUnlock(1,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret13.jpg",100, 100L);
        boolean ret = openCvProcessingService.compressUnlock(null,640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret8.jpg",100, 100L);
        ;
        return "hello emiya!"+ret;
    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2() {
//        boolean ret = openCvProcessingService.compressLock(1,1,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret14.jpg",100, 100L);
        boolean ret = openCvProcessingService.compressLock(0,0,640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L);
        return "hello emiya2!"+ret;
    }
    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public String test3() {
        boolean ret = openCvProcessingService.compressLock(1,2,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret15.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test4",method = RequestMethod.GET)
    public String test4() {
        boolean ret = openCvProcessingService.compressLock(1,3,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret16.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya4!"+ret;
    }
    @RequestMapping(value = "/test5",method = RequestMethod.GET)
    public String test5() {
        boolean ret = openCvProcessingService.compressLock(1,4,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret17.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test6",method = RequestMethod.GET)
    public String test6() {
        boolean ret = openCvProcessingService.compressLock(1,5,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret18.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test7",method = RequestMethod.GET)
    public String test7() {
        boolean ret = openCvProcessingService.compressLock(1,6,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret19.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test8",method = RequestMethod.GET)
    public String test8() {
        boolean ret = openCvProcessingService.compressLock(1,7,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret20.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }
    @RequestMapping(value = "/test9",method = RequestMethod.GET)
    public String test9() {
        boolean ret = openCvProcessingService.compressLock(1,8,640,200,"F:\\jiguang\\chenqingling\\h.jpg","F:\\picjg\\ret21.jpg",100, 100L);
//        boolean ret = openCvProcessingService.compressLock(640,360,"/opt/yanhua-erp-consumer-engine/pic/h.jpg","/opt/yanhua-erp-consumer-engine/picjg/ret9.jpg",100, 100L,true);
        return "hello emiya3!"+ret;
    }

}