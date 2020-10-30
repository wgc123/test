package com.note.mytest.okhttp;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

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
        OkHttpClient mClient = new OkHttpClient.Builder()
                .callTimeout(6_000, TimeUnit.MILLISECONDS)
                .connectTimeout(6_000, TimeUnit.MILLISECONDS)
                .readTimeout(20_000, TimeUnit.MILLISECONDS)
                .writeTimeout(20_000, TimeUnit.MILLISECONDS)
                .build();
//        Request request = new Request.Builder()
//                .header("Accept","image/webp")
//                .addHeader("Charset","UTF-8")
//                .url(url)
//                .build();
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
