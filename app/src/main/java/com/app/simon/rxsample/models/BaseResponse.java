package com.app.simon.rxsample.models;

/**
 * desc: BaseResponse
 * date: 2017/7/28
 *
 * @author xw
 */
public class BaseResponse<T> {
    public static final int MAX_SUCCESS_CODE = 100;
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        if (code >= 0 && code <= MAX_SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }
}
