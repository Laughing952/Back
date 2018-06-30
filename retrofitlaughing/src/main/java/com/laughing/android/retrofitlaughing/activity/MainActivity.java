package com.laughing.android.retrofitlaughing.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.laughing.android.retrofitlaughing.Contributor;
import com.laughing.android.retrofitlaughing.R;
import com.laughing.android.retrofitlaughing.api.GitHubApi;
import com.laughing.android.retrofitlaughing.resp.GetRep;
import com.laughing.android.retrofitlaughing.use.RetrofitHelper;
import com.laughing.net.manager.base.NetUtil;
import com.laughing.net.manager.base.NetWorkConfig;
import com.laughing.net.manager.base.gson.BaseBean;
import com.laughing.net.manager.base.gson.BlogBean;
import com.laughing.net.manager.base.gson.CustomGsonConverterFactory;
import com.laughing.net.manager.base.manager.RetrofitManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTv_content;
    private Button mBt_retrofit_getButton;
    private Retrofit mRetrofit;
    private Button mBt_retrofit_get_cancle;
    private Call<ResponseBody> mCall;
    private Button mBt_get;
    private TextView mTv_result;
    private Context mContext = this;
    private Button mBt_post;
    private GitHubApi mGitHubApi;
    private Button mBt_url;
    private Button mBt_rxjava_get;
    private Button mBt_rxjava_post;
    private Button mBt_rxjava_url;
    private GitHubApi mGitHubApi2;
    private OkHttpClient.Builder mBuilder;
    private Button mBt_rxjava_remove_wrapper;
    private Button mBt_rxjava_no_wrapper;
    private Cache mCache;
    private Interceptor mCacheInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        System.out.print("xdpi------>" + xdpi + ",ydpi------>" + ydpi);
        initView();
        initOkHttp();
        initRetrofit();
        initListener();
        initRetrofit2();
        initGetListener();
        initPostListener();
        initUrlListener();
        // rxjava
        initRxjava();

    }

    private void initOkHttp() {
        mBuilder = new OkHttpClient.Builder();
        //无网络-判断网络状态需要context对象。
        NetUtil.init(this);
        //使用net框架前先初始化context
        RetrofitManager.init(this);

        //按照顺序执行拦截器
//        mBuilder.addInterceptor(new PreHandleNoNetInterceptor());//无网络，放到最前面

        //自动追加url参数
//        mBuilder.addInterceptor(new AppendUrlParamInterceptor());
        //自动追加header参数
//        mBuilder.addInterceptor(new AppendHeaderParamInterceptor());
        //将Url 参数-->Body
//        mBuilder.addInterceptor(new AppendBodyParamsInterceptor());//无奈之举

        //缓存设置
        File cacheFile = new File(getExternalCacheDir(), "laughing");
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
    }


    private void initRetrofit2() {
        //1.拿到retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                //引入Gson解析库，就可以直接以实体的形式拿到返回值
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.拿到api实例
        mGitHubApi = retrofit.create(GitHubApi.class);
    }


    //在这里baseUrl是在创建retrofit实力的时候定义的，我们也可以在API接口中定义完整的url。
    // 在这里建议在创建baseUrl中以”/”结尾，在API中不以”/”开头和结尾。
    private void initRetrofit() {
        //创建retrofit实例
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

    }

    private void initListener() {
        mBt_retrofit_getButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "执行网络访问", Toast.LENGTH_SHORT).show();

                //3.调用API接口     在调用API接口请求后，获得一个json字符串，通过Gson进行解析，获得login以及contributions。
                GitHubApi repo = mRetrofit.create(GitHubApi.class);

                mCall = repo.contributorsBySimpleCall("square", "retrofit");
                mCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Gson gson = new Gson();
                            ArrayList<Contributor> contributorsList = gson.fromJson(response.body().string(), new TypeToken<List<Contributor>>() {
                            }.getType());
                            for (Contributor contributor : contributorsList) {
                                Log.d("login", contributor.getLogin());
                                Log.d("contributions", contributor.getContributions() + "");
                                mTv_content.setText(contributor.getLogin() + "---->" + contributor.getContributions());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "网络访问失败", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        mBt_retrofit_get_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCall.cancel();//取消网络访问   我们可以终止一个请求。终止操作是对底层的httpclient执行cancel操作。即使是正在执行的请求，也能够立即终止。
                Toast.makeText(getApplicationContext(), "取消网络访问", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mTv_content = (TextView) findViewById(R.id.tv_content);
        mBt_retrofit_getButton = (Button) findViewById(R.id.bt_retrofit_get);
        mBt_retrofit_get_cancle = (Button) findViewById(R.id.bt_retrofit_get_cancle);
        ///////////////////////////////////////////////////////////
        mBt_get = (Button) findViewById(R.id.bt_get);
        mBt_post = (Button) findViewById(R.id.bt_post);
        mBt_url = (Button) findViewById(R.id.bt_url);
        mTv_result = (TextView) findViewById(R.id.tv_result);
        //////////////////////////rxjava//////////////////////////////////
        mBt_rxjava_get = (Button) findViewById(R.id.bt_rxjava_get);
        mBt_rxjava_post = (Button) findViewById(R.id.bt_rxjava_post);
        mBt_rxjava_url = (Button) findViewById(R.id.bt_rxjava_url);
        mBt_rxjava_remove_wrapper = (Button) findViewById(R.id.bt_rxjava_remove_wrapper);
        mBt_rxjava_no_wrapper = (Button) findViewById(R.id.bt_rxjava_no_wrapper);
        mBt_rxjava_url.setText("Completable测试");

    }


    private void initRxjava() {
        //1.拿到retrofit实例
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                //引入Gson解析库，就可以直接以实体的形式拿到返回值
//                .addConverterFactory(GsonConverterFactory.create())
                //加入自定义的gson解析库，就可以更友好的处理错误
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加adapter转换器
                .client(mBuilder.build())//将我们客制化的OKHttp实例传入
                .build();

        //2.拿到api实例
        mGitHubApi2 = retrofit2.create(GitHubApi.class);
        method1();//get请求
        method2();//post请求
        method3();//completable
        method4();//统一未剥离BaseBean
        method5();//统一剥离BaseBean
    }


    private void method1() {
        mBt_rxjava_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Observable<GetRep> observable = mGitHubApi2.getDataByRx("Android", "10", "1");
                Observable<GetRep> observable = RetrofitHelper.getGitHubApi().getDataByRx("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<GetRep>() {
                            @Override
                            public void accept(GetRep getRep) throws Exception {
                                mTv_result.setText("Rxjava---->" + getRep.getResults().get(1).getDesc());

                            }
                        });
                // 测试get请求转变为post请求
//                        .subscribe(new Observer<GetRep>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(GetRep getRep) {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Toast.makeText(mContext, "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });

            }
        });
    }

    //mGitHubApi2来请求网络
    private void method2() {
        mBt_rxjava_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Object> observable = mGitHubApi2.postDataByRx("http://square.github.io/retrofit/", "测试数据",
                        "未来Android大佬", "Android", "true");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<Object>() {
//                            @Override
//                            public void accept(Object o) throws Exception {
//                                mTv_result.setText("Rxjava--post-->" + o);
//                            }
//                        });
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object o) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });
    }

    private void method3() {
        mBt_rxjava_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Completable completable = RetrofitHelper.getGitHubApi().postDataByRxNoReturn("http://square.github.io/retrofit/", "测试数据",
                        "未来Android大佬", "Android", "true");
                completable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(mContext, "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    /**
     * 统一未剥离BaseBean
     */
    private void method4() {
        mBt_rxjava_remove_wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<BaseBean<List<BlogBean>>> observable = RetrofitHelper.getGitHubApi().getDataByWrapper("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseBean<List<BlogBean>>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseBean<List<BlogBean>> listBaseBean) {
                                List<BlogBean> result = listBaseBean.getResults();

                                BlogBean blogBean = result.get(2);
                                mTv_result.setText(blogBean.getDesc());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }


    /**
     * 统一剥离BaseBean
     */
    private void method5() {
        mBt_rxjava_no_wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<List<BlogBean>> observable = RetrofitHelper.getGitHubApi().getDataNoWrapper("Android", "10", "1");
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<BlogBean>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<BlogBean> blogBeans) {
                                mTv_result.setText("一共多少个数据" + blogBeans.size());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

    private void initGetListener() {
        mBt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //3. 通过Api拿到call
                Call<GetRep> android = mGitHubApi.getData("Android", "10", "1");
                //4.请求网络
                android.enqueue(new Callback<GetRep>() {
                    @Override
                    public void onResponse(Call<GetRep> call, Response<GetRep> response) {
                        GetRep getRep = response.body();
                        Toast.makeText(mContext, "请求成功" +
                                getRep.getResults().get(0).getDesc(), Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "onResponse: " + getRep.getResults().get(0).getDesc());
                        mTv_result.setText(getRep.getResults().get(0).getDesc());

                    }

                    @Override
                    public void onFailure(Call<GetRep> call, Throwable t) {
                        Toast.makeText(mContext, "请求失败" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    /**
     * post请求传递表单数据
     */
    private void initPostListener() {
        mBt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * https://gank.io/api/add2gank 方式: POST
                 * 提交表单格式如下:
                 * 字段	描述	备注
                 * url	想要提交的网页地址
                 * desc	对干货内容的描述	单独的文字描述
                 * who	提交者 ID	干货提交者的网络 ID
                 * type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
                 * debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
                 */
                //3. 通过Api拿到call
                Call<ResponseBody> responseBodyCall = mGitHubApi.postData(
                        "http://square.github.io/retrofit/", "测试数据",
                        "未来Android大佬", "Android", "true");
                //4.请求网络
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "请求失败" + t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void initUrlListener() {
        mBt_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<GetRep> android = mGitHubApi.getDataWithUrl("http://gank.io/api/data/Android/10/1");
                android.enqueue(new Callback<GetRep>() {
                    @Override
                    public void onResponse(Call<GetRep> call, Response<GetRep> response) {
                        Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                        GetRep getRep = response.body();
                        mTv_result.setText(getRep.getResults().get(0).getDesc());
                    }

                    @Override
                    public void onFailure(Call<GetRep> call, Throwable t) {
                        Toast.makeText(mContext, "请求失败" + t, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
