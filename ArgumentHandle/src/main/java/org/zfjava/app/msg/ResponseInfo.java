package org.zfjava.app.msg;

/**
 * @auther zhangfen
 * @date 2022/1/4 16:27
 */

/*
    统一的 JSON 结构中属性包括数据、状态码、提示信息，其他项可以自己根据需要添加。
    一般来说，应该有默认的返回结构，也应该有用户指定的返回结构。
    由于返回数据类型无法确定，需要使用泛型。
 */
public class ResponseInfo <T>{

    protected int code; //状态码
    protected String msg; //响应信息

    private T data; //响应数据

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public ResponseInfo() {
        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public ResponseInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public ResponseInfo(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data=data;
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
     * @param data
     */
    public ResponseInfo(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     * @param data
     * @param msg
     */
    public ResponseInfo(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
