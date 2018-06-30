package com.laughing.net.manager.base.manager;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.laughing.net.manager.base.NetUtil;
import com.laughing.net.manager.base.NetWorkConfig;
import com.laughing.net.manager.base.gson.CustomGsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * 通过这个类获取retrofit的实例
 * 使用者 通过这个retrofit实例，创建Api Service
 * 作者：Laughing on 2018/6/6 09:08
 * 邮箱：719240226@qq.com
 */

public class RetrofitManager {
    private OkHttpClient mOkHttpClient;
    private static Context mContext;
    private Cache mCache;
    private Interceptor mCacheInterceptor;

    private static class InnerHolder {
        public static RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {

        return InnerHolder.INSTANCE;
    }

    /**
     * 网络请求初始化时调用一次
     * @param ctx
     */
    public static void init(Context ctx) {
        //防止内存泄漏
        mContext = ctx.getApplicationContext();
    }

    private RetrofitManager() {
        initOkHttp();
    }

    private void initOkHttp() {
        //在构造方法中最终是为了得到一个OKHttpClient实例

        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        //无网络-判断网络状态需要context对象。
        NetUtil.init(mContext);
        //按照顺序执行拦截器
//        mBuilder.addInterceptor(new PreHandleNoNetInterceptor());//无网络，放到最前面

        //自动追加url参数
//        mBuilder.addInterceptor(new AppendUrlParamInterceptor());
        //自动追加header参数
//        mBuilder.addInterceptor(new AppendHeaderParamInterceptor());
        //将Url 参数-->Body
//        mBuilder.addInterceptor(new AppendBodyParamsInterceptor());//无奈之举

        //缓存设置
        File cacheFile = new File(mContext.getExternalCacheDir(), "laughing");
        mCache = new Cache(cacheFile, 1024 * 1024 * 100);
        //取缓存(没有网络)
        //有网络 存缓存
        //2句以下2句为套路代码（"http cache"）
        mCacheInterceptor = new Interceptor() {

            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                okhttp3.Response response = chain.proceed(request);
//                if (!NetUtil.isNetwork()) {
//                    //取缓存(没有网络)
//                    Request newRequest = request.newBuilder()
//                            .cacheControl(CacheControl.FORCE_CACHE)
//                            .build();
//                    return chain.proceed(newRequest);
//
//                } else {
//
//                    //有网络 存缓存
//                    int maxTime = 60 * 60 * 24;
//                    okhttp3.Response newResponse = response.newBuilder()
//                            //2句以下2句为套路代码（"http cache"）
//                            .header("Cache-Control", "public, only-if-cached,max-stale=" + maxTime)
//                            .removeHeader("Pragma")
//                            .build();
//                    return newResponse;
//                }
//            }
//        };
//        mBuilder.cache(mCache)
//                .addInterceptor(mCacheInterceptor);

                CacheControl.Builder cacheBuilder = new CacheControl.Builder();

                cacheBuilder.maxAge(0, TimeUnit.SECONDS);

                cacheBuilder.maxStale(365, TimeUnit.DAYS);

                CacheControl cacheControl = cacheBuilder.build();

                Request request = chain.request();

                if (!NetUtil.isNetwork()) {

                    request = request.newBuilder()

                            .cacheControl(cacheControl)

                            .build();

                }

                okhttp3.Response originalResponse = chain.proceed(request);

                if (NetUtil.isNetwork()) {

                    int maxAge = 0; // read from cache

                    return originalResponse.newBuilder()

                            .removeHeader("Pragma")

                            .header("Cache-Control", "public ,max-age=" + maxAge)

                            .build();

                } else {

                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale

                    return originalResponse.newBuilder()

                            .removeHeader("Pragma")

                            .header("Cache-Control", "public, only-if-cached,max-stale=" + maxStale)

                            .build();

                }
            }
        };
        mBuilder.cache(mCache)
                .addInterceptor(mCacheInterceptor);


        //OkHttp设置超时
        mBuilder.connectTimeout(30, TimeUnit.SECONDS);
        mBuilder.readTimeout(30, TimeUnit.SECONDS);
        mBuilder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        mBuilder.retryOnConnectionFailure(true);


        if (NetWorkConfig.DEBUG) {
            //OKHttp的Log信息拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            HttpLoggingInterceptor httpLoggingInterceptor1 = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mBuilder.addInterceptor(httpLoggingInterceptor1);

        }
        mOkHttpClient = mBuilder.build();
    }


    public Retrofit newRetrofit(String url) {
        //1.拿到retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //引入Gson解析库，就可以直接以实体的形式拿到返回值
//                .addConverterFactory(GsonConverterFactory.create())
                //加入自定义的gson解析库，就可以更友好的处理错误
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加adapter转换器
                .client(mOkHttpClient)//将我们客制化的OKHttp实例传入
                .build();
        return retrofit;
        //2.拿到api实例
//        mGitHubApi = retrofit.create(GitHubApi.class);
    }
}
