package com.laughing.net.manager.base.interceptor;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 统一追加header参数
 * 作者：Laughing on 2018/6/5 11:43
 * 邮箱：719240226@qq.com
 */

public class AppendHeaderParamInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers.Builder builder = request.headers().newBuilder();
        Headers newHeader = builder.add("token1", "i am token1")
                .add("token2", "i am token2")
                .add("uuid", "1234567890").build();
        Request newRequest = request.newBuilder().headers(newHeader).build();
        return chain.proceed(newRequest);
    }
}
