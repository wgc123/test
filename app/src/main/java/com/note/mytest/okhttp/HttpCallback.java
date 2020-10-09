package com.note.mytest.okhttp;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 15:35
 * @desc :
 */
public abstract class HttpCallback<Result> implements IHttpCallback {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> clazz = analysisClazzInfo(this);
        Result resultObj = (Result) gson.fromJson(result, clazz);
        onSuccess(resultObj);
    }

    /**
     * 成功时的回调
     * @param result
     */
    public abstract void onSuccess(Result result);

    private Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
