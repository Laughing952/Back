package com.laughing.net.manager.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络util
 * 作者：Laughing on 2018/6/5 16:10
 * 邮箱：719240226@qq.com
 */

public class NetUtil {
    private static Context sContext;

    public static void init(Context c) {
        sContext = c.getApplicationContext();
    }

    public static boolean isNetwork() {
        if (null != sContext) {
            ConnectivityManager cm = (ConnectivityManager) sContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == cm || (null != cm && null == cm.getActiveNetworkInfo())) {
                return false;
            }
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return false;

            }
            return info.isAvailable();

        }
        return false;
    }
}
