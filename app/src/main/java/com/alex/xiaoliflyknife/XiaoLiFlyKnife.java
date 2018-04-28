package com.alex.xiaoliflyknife;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  APTDemo
 *  @包名：    com.alex.aptdemo
 *  @文件名:   XiaoLiFlyKnife
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/28 14:08
 *  @描述：    TODO
 */

public class XiaoLiFlyKnife {
	private static final Map<String, Constructor> FINDER_MAP = new HashMap<>();

	public static void bind(Activity activity) {
		String className = activity.getClass().getName();
		Constructor constructor = FINDER_MAP.get(className);
		if (constructor == null) {
			//				Class<?> finderClass = Class.forName(className + "$$Injector");
			//noinspection unchecked
			try {
				Class<?> bindingClass = activity.getClass().getClassLoader().loadClass(className + "$$Injector");
				constructor = bindingClass.getConstructor(activity.getClass());
				FINDER_MAP.put(className, constructor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			constructor.newInstance(activity);
		} catch (Exception e) {
			throw new RuntimeException("Unable to inject for " + className, e);
		}
	}
}
