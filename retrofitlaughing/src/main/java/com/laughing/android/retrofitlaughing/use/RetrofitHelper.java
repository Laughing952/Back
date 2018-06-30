package com.laughing.android.retrofitlaughing.use;

import com.laughing.android.retrofitlaughing.api.GankApi;
import com.laughing.android.retrofitlaughing.api.GitHubApi;
import com.laughing.net.manager.base.manager.RetrofitManager;

/**
 * 作者：Laughing on 2018/6/6 09:52
 * 邮箱：719240226@qq.com
 */

public class RetrofitHelper {
    public static final String URL = "http://gank.io/";
    public static final String GANK_URL = "http://gank.io/";

    /**
     * 一个URL对应一个retrofit实例
     */
    private static final GitHubApi mGitHubApi = RetrofitManager.getInstance()
            .newRetrofit(URL)
            .create(GitHubApi.class);


    public static GitHubApi getGitHubApi() {

        return mGitHubApi;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    private static final GankApi mGankApi = (GankApi) RetrofitManager.getInstance()
            .newRetrofit(GANK_URL)
            .create(GankApi.class);

    public static GankApi getGankApi() {

        return mGankApi;
    }

}
