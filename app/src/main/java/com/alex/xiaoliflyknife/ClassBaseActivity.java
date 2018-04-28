package com.alex.xiaoliflyknife;

/*
 *  @项目名：  APTDemo 
 *  @包名：    com.alex.aptdemo
 *  @文件名:   ClassBaseActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/3/26 14:57
 *  @描述：    编译时BaseActivity
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class ClassBaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		XiaoLiFlyKnife.bind(this);
		initContent();
	}

	protected void initContent() {

	}
}
