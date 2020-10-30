package com.note.mytest.okhttp;

import java.util.Map;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 15:29
 * @desc : okhttp 请求
 */
public interface IHttpLoader {
    /**
     * GET方式请求
     * @param url
     * @param params
     * @param callback
     */
    void get(String url, Map<String, Object> params, IHttpCallback callback);

    /**
     * POST方式请求
     * @param url
     * @param params
     * @param callback
     */
    void post(String url, Map<String, Object> params, IHttpCallback callback);
}
