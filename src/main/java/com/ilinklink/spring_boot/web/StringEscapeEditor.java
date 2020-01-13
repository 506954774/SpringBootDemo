/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilinklink.spring_boot.web;

import org.apache.commons.lang.StringEscapeUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author shawn
 * @version 1.0.0
 * @datetime 2017-8-29 14:23:49
 * @copyright Shenzhen LingLing Technology Co., Ltd.
 */
public class StringEscapeEditor extends PropertyEditorSupport {

    private boolean escapeHTML;
    private boolean escapeJavaScript;
    private boolean escapeSQL;

    public StringEscapeEditor() {
        super();
    }

    public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript, boolean escapeSQL) {
        super();
        this.escapeHTML = escapeHTML;
        this.escapeJavaScript = escapeJavaScript;
        this.escapeSQL = escapeSQL;
    }

    @Override
    public void setAsText(String text) {

        if (text == null) {

            setValue(null);

        } else {
            String value = text;
            if (escapeHTML) {
                value = StringEscapeUtils.escapeHtml(value);
            }
            if (escapeJavaScript) {
                value = StringEscapeUtils.escapeJavaScript(value);
            }
            if (escapeSQL) {
                value = StringEscapeUtils.escapeSql(value);
            }
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }
}