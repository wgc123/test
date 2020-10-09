package com.note.mytest.okhttp;

import java.util.Map;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 15:39
 * @desc :
 */
public class HttpHelper implements IHttpLoader {

    private static IHttpLoader mHttpLoader;

    private HttpHelper() {

    }

    public static void init(IHttpLoader httpLoader) {
        mHttpLoader = httpLoader;
    }

    public static HttpHelper obtain() {
        return HttpHelperHolder.INSTANCE;
    }

    static class HttpHelperHolder {
        private static HttpHelper INSTANCE = new HttpHelper();
    }

    @Override
    public void get(String url, Map<String, Object> params, IHttpCallback callback) {
        mHttpLoader.get(url, params, callback);
    }

    @Override
    public void post(String url, Map<String, Object> params, IHttpCallback callback) {
        mHttpLoader.post(url, params, callback);
    }
}
