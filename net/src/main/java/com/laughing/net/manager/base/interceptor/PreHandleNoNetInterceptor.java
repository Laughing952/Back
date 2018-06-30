package com.laughing.net.manager.base.interceptor;

import com.laughing.net.manager.base.NetUtil;
import com.laughing.net.manager.base.NetWorkConfig;
import com.laughing.net.manager.base.ResultException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 无网络异常处理 拦截器
 * 作者：Laughing on 2018/6/5 16:00
 * 邮箱：719240226@qq.com
 */

public class PreHandleNoNetInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (NetUtil.isNetwork()) {

            return chain.proceed(chain.request());
        }else{
            throw new ResultException(NetWorkConfig.ERROR_CODE_NO_NET , "当前网络不通，请确认网络后重试");

        }
    }
}
