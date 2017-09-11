package com.pingxun.library.guomeilibrary.bridge;

/**
 * Created by hbl on 2017/5/11.
 */

public class HandlerHolder {
    private String handlerName;
    private String handlerClass;

    public HandlerHolder(String handlerName, String handlerClass) {
        this.handlerName = handlerName;
        this.handlerClass = handlerClass;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(String handlerClass) {
        this.handlerClass = handlerClass;
    }
}
