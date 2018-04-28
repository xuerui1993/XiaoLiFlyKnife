package com.alex.xiaoliflyknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/*
 *  @项目名：  APTDemo
 *  @包名：    com.alex.aptdemo
 *  @文件名:   RuntimeBaseActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/28 18:03
 *  @描述：    运行时BaseActivity
 */
public abstract class RuntimeBaseActivity extends AppCompatActivity{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ContentView contentView = AnnotationManager.getInstance().getContentViewAnnotation(getClass());
		setContentView(contentView.value());
		initContent();
	}

	protected void initContent(){

	}
}
