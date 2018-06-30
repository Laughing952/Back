package com.laughing.net.manager.base.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * url 统一追加url参数
 * 作者：Laughing on 2018/6/5 11:26
 * 邮箱：719240226@qq.com
 */

public class AppendUrlParamInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //拿到拥有以前的request 里的 url 的 那些信息的 builder
        HttpUrl.Builder builder = request.url().newBuilder();
        //得到新的Url 已经统一追加好了参数
        HttpUrl newUrl = builder.addQueryParameter("deviceId", "123456")
                .addQueryParameter("token", "tokenStr")
                .addQueryParameter("appVersion", "1.0.0")
                .build();
        //利用新的url 构建新的Request，并发送给服务器
        Request newRequest = request.newBuilder()
                .url(newUrl)
                .build();
        return chain.proceed(newRequest);
    }
}
