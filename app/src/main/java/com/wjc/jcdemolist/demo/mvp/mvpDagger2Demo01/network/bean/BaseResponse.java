package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.bean;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.bean
 * Description:
 * JcChen on 2019/7/13 17:17
 */
public class BaseResponse<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
