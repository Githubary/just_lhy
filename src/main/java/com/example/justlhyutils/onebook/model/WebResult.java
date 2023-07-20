package com.example.justlhyutils.onebook.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/5/31 17:38
 */
public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private Date timestamp;

    private T data;

    public WebResult() {

    }

    public WebResult(T data){
        this.code=0;
        this.message = "";
        this.data = data;
        this.timestamp = new Date();
    }

    public WebResult(int code, String msg, T data){
        this.code=code;
        this.message = msg;
        this.data = data;
        this.timestamp = new Date();
    }

    public static <T extends Serializable> WebResult<T> success(T data) {
        return new WebResult<>(0, "success", data);
    }

    public static <T extends Collection> WebResult<T> success(T data) {
        return new WebResult<>(0, "success", data);
    }

    public static <T extends Map> WebResult<T> success(T data) {
        return new WebResult<>(0, "success", data);
    }

    public static <T extends Object> WebResult<T> success(T data) {
        return new WebResult<>(0, "success", data);
    }


    public static <T extends Serializable> WebResult<T> failure(String msg){
        WebResult<T> webResult = new WebResult<>();
        webResult.setCode(50000);
        webResult.setMessage("failure");
        if(StringUtils.isNotBlank(msg)){
            webResult.setMessage(msg);
        }
        webResult.setTimestamp(new Date());
        return webResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
