package com.laughing.android.back.updateApk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

//此类用来获取apk包的信息：版本号等等
public class PackUtils {

	private Context context;
	private PackageManager manager;
	private PackageInfo info;

	public PackUtils(Context context) {
		this.context = context;
		manager = context.getPackageManager();
		initInfo();
	}

	// 初始化数据
	public void initInfo() {
		try {
			info = manager.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取版本号
	public int getVersionCode() {

		return info.versionCode;
	}

	// 获取版本名称
	public String getVersionName() {

		return info.versionName;
	}

	/**
	 * 是否进行版本升级
	 * @param oldVersion 老版本号
	 * @param newVersion 新版本号
	 * @return
	 */
	public boolean isUpgradeVersion(int oldVersion, int newVersion) {
		boolean flag = false;
		flag = (newVersion > oldVersion ? true : false);
		return flag;
	}
}
