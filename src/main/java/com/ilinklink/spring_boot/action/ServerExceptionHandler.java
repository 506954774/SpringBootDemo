/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.action;



import com.ilinklink.spring_boot.exception.AdminErrorCode;
import com.ilinklink.spring_boot.exception.AdminException;
import com.ilinklink.spring_boot.web.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import static com.ilinklink.spring_boot.ServerConstants.LOGGER_PREFIX;

@Slf4j
@ControllerAdvice
public class ServerExceptionHandler extends BaseAction {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity exception(Exception exception, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);

        if (exception instanceof AdminException) {
            return new ResponseEntity(AdminErrorCode.PERMISSION_REQUIRED, false, getMessage(AdminErrorCode.PERMISSION_REQUIRED));
        }

        log.error(LOGGER_PREFIX+"异常!"+ exception.getMessage());


        return new ResponseEntity(AdminErrorCode.REQUEST_EXCEPTION, false, getMessage(AdminErrorCode.REQUEST_EXCEPTION));
    }
}