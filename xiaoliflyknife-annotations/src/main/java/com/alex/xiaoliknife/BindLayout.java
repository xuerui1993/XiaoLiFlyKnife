package com.alex.xiaoliknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *  @项目名：  APTDemo
 *  @包名：    com.alex.xiaoliknife
 *  @文件名:   BindLayout
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/27 14:17
 *  @描述：    TODO
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface BindLayout {
	int value();
}
