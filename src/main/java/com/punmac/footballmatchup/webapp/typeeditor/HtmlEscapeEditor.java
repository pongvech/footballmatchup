package com.punmac.footballmatchup.webapp.typeeditor;

import org.springframework.web.util.HtmlUtils;

import java.beans.PropertyEditorSupport;

/**
 * Escape and Unescape html in spring form.
 */
public class HtmlEscapeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            setValue(HtmlUtils.htmlEscape(text));
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return (value != null ? HtmlUtils.htmlUnescape(value.toString()) : "");
    }
}
