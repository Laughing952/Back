package com.laughing.net.manager.base.interceptor;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 将所有的get请求————>post请求，将get后面的Query Params————>Body（基本用不到）
 * 作者：Laughing on 2018/6/5 15:04
 * 邮箱：719240226@qq.com
 */

public class AppendBodyParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //拿到所有Query的key
        Set<String> queryKeyNames = request.url().queryParameterNames();
        StringBuffer bodyString = new StringBuffer();
        //将Query————>Body
        for (String s : queryKeyNames) {
            bodyString.append(s)
                    .append("=")
                    .append(request.url().queryParameterValues(s))//查询url后的key ，value
                    .append(",");

        }
        //构建新Body
        RequestBody newBody = RequestBody.create(MediaType.parse("application/json"),
                bodyString.toString().substring(0, bodyString.length() - 1));
        Request newRequest = request.newBuilder()
                .post(newBody)
                .build();
        return chain.proceed(newRequest);
    }

}
