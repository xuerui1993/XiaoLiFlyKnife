package com.alex.xiaoliflyknife;

import android.content.Intent;
import android.widget.Button;

import com.alex.xiaoliknife.BindLayout;

@BindLayout(R.layout.activity_main)
public class MainActivity extends ClassBaseActivity {

	@Override
	protected void initContent() {
		Button button = findViewById(R.id.btn);
		button.setOnClickListener(v -> {
			startActivity(new Intent(MainActivity.this,SecondActivity.class));
		});
	}
}
