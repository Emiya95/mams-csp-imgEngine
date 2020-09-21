package com.yanhua.engine.common;


import java.util.ArrayList;
import java.util.List;

/**
 * 封装结果对象工厂
 *
 * @author Chester
 * 2018-08-06 13:42
 **/
public class ResultFactory {
    public static Result toResult(String code, String msg, Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMessage(msg);
        res.setData(data);
        return res;
    }

    public static Result toResult(String code, String msg){
        Result res = new Result();
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }




    public static ResultBean toResultBean(String code, String msg, Object data) {
        ResultBean res = new ResultBean();
        res.setCode(code);
        res.setMessage(msg);
        res.setData(data);
        return res;
    }

    public static ResultBean toResultBean(String code, String msg){
        ResultBean res = new ResultBean();
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }

    public static ResultBean toResultBean(ResultBean res, String code, String msg) {
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }


    public static List<ResultBean> toResultBeans(String code, String msg) {
        List<ResultBean> resultBeanList = new ArrayList<>();
        resultBeanList.add(toResultBean(code, msg));
        return resultBeanList;
    }
}
