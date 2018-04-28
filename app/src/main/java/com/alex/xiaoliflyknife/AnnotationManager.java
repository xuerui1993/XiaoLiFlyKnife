package com.alex.xiaoliflyknife;

import java.util.HashMap;

/*
 *  @项目名：  APTDemo
 *  @包名：    com.alex.aptdemo
 *  @文件名:   AnnotationManager
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/28 17:51
 *  @描述：    运行时注解缓存工具
 */
public class AnnotationManager {
	private static AnnotationManager sInstance;
	private HashMap<Class<?>,ContentView> mMap = new HashMap<>();

	private AnnotationManager() {
	}

	/**
	 * 获取FragmentFactory的实例
	 * @return FragmentFactory的实例
	 */
	public static AnnotationManager getInstance() {
		if (sInstance == null) {
			synchronized (AnnotationManager.class) {
				if (sInstance == null) {
					sInstance = new AnnotationManager();
				}
			}
		}
		return sInstance;
	}

	public ContentView getContentViewAnnotation(Class<?> clazz){
		if (mMap.get(clazz)!=null){
			return mMap.get(clazz);
		}
		ContentView annotation = clazz.getAnnotation(ContentView.class);
		if(annotation == null) throw new RuntimeException("Please bind activity layout");
		mMap.put(clazz,annotation);
		return annotation;
	}
}
