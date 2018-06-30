package com.laughing.android.retrofitlaughing.api;


import com.laughing.android.retrofitlaughing.resp.GetRep;
import com.laughing.net.manager.base.gson.BaseBean;
import com.laughing.net.manager.base.gson.BlogBean;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * 作者：Laughing on 2018/3/14 08:37
 * 邮箱：719240226@qq.com
 * 定以接口 用retrofit框架进行网络访问
 */

//2.创建API接口     在retrofit中通过一个Java接口作为http请求的api接口。
public interface GitHubApi {
    /**
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     *
     * @return
     */
    @GET("api/data/{category}/{size}/{page}")
    Call<GetRep> getData(@Path("category") String category,
                         @Path("size") String size,
                         @Path("page") String page);


    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleCall(@Path("owner") String owner, @Path("repo") String repo);

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
    @FormUrlEncoded
    @POST("api/add2gank")
    Call<ResponseBody> postData(@Field("url") String url,
                                @Field("desc") String desc,
                                @Field("who") String who,
                                @Field("type") String type,
                                @Field("debug") String debug);


    @GET
    Call<GetRep> getDataWithUrl(@Url String url);


    /////////////////////////////////////////RxJava+Retrofit////////////////////////////////////////

    /**
     * RxJava 的形式返回
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     *
     * @return
     */
    @GET("api/data/{category}/{size}/{page}")
    Observable<GetRep> getDataByRx(@Path("category") String category,
                                   @Path("size") String size,
                                   @Path("page") String page);


    @FormUrlEncoded
    @POST("api/add2gank")
    Observable<Object> postDataByRx(@Field("url") String url,
                                    @Field("desc") String desc,
                                    @Field("who") String who,
                                    @Field("type") String type,
                                    @Field("debug") String debug);

    /**
     * 不care 返回值的
     *
     * @param url
     * @param desc
     * @param who
     * @param type
     * @param debug
     * @return
     */
    @FormUrlEncoded
    @POST("api/add2gank")
    Completable postDataByRxNoReturn(@Field("url") String url,
                                     @Field("desc") String desc,
                                     @Field("who") String who,
                                     @Field("type") String type,
                                     @Field("debug") String debug);

    /////////////////////////////////////统一剥离BaseBean///////////////////////////////////////
    @GET("api/data/{category}/{size}/{page}")
    Observable<BaseBean<List<BlogBean>>> getDataByWrapper(@Path("category") String category,
                                                          @Path("size") String size,
                                                          @Path("page") String page);

    @GET("api/data/{category}/{size}/{page}")
    Observable<List<BlogBean>> getDataNoWrapper(@Path("category") String category,
                                                @Path("size") String size,
                                                @Path("page") String page);
}
