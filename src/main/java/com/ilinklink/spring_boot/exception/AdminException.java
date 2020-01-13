/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.exception;

/**
 *
 * @author shawn
 * @copyright Halo
 * @datetime 2016-10-25 11:44:52
 * @version 1.0
 */
public class AdminException extends Exception {

    private String errorCode;

    public AdminException(String errorCode) {
        this.errorCode = errorCode;
    }

    public AdminException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AdminException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AdminException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}