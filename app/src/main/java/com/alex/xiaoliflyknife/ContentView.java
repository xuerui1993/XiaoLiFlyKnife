package com.alex.xiaoliflyknife;

/*
 *  @项目名：  APTDemo 
 *  @包名：    com.alex.aptdemo
 *  @文件名:   ContentView
 *  @创建者:   xuerui
 *  @创建时间:  2018/3/26 14:54
 *  @描述：    ContentView注解
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ContentView {
	int value();
}
