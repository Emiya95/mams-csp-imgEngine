package com.yanhua.engine.common;


/**
 * @ClassName: ResultBean
 * @Description: 结果封装类基类
 * @Author: IkARos
 * @Date: 2019/5/22 17:12
 * @Version: 1.0
 */
public class ResultBean {
    /**
     * success或者fail
     */
    private String code;
    /**
     * 字符串信息
     */
    private String message;
    /**
     * 结果对象
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
