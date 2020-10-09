package com.note.mytest.okhttp;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 15:32
 * @desc : 返回状态
 */
public interface IHttpCallback {


    /**
     * 成功时的回调
     *
     * @param result
     */
    void onSuccess(String result);

    /**
     * 失败时的回调
     *
     * @param msg
     */
    void onFailed(String msg);
}
