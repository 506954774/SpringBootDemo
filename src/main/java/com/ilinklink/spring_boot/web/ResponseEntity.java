package com.ilinklink.spring_boot.web;

import java.io.Serializable;

/**
 * Created by Jason on 2016/2/1.
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 1、true success
     * 2、false fail
     */
    private boolean success;
    /**
     * error code
     */
    private String errorCode;
    /**
     * description
     */
    private String message;
    /**
     * result
     * example：get user information
     * result = UserInfo
     */
    private T result;

    public ResponseEntity(boolean success) {
        this(null, success, null);
    }

    public ResponseEntity(String code, boolean success, String message) {
        this.errorCode = code;
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" + "success=" + success + ", errorCode=" + errorCode + ", message=" + message + ", result=" + result + '}';
    }
}