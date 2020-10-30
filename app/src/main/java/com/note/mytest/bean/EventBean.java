package com.note.mytest.bean;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/30 8:42
 * @desc : 测试eventbus 异步消息机制
 */
public class EventBean {
    public final String message;

    public static EventBean getInstance(String message) {
        return new EventBean(message);
    }

    private EventBean(String message){
        this.message = message;
    }
}
