package com.zhengwenbiao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TabB extends Activity {
	@SuppressWarnings("unused")
	private ListView list2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabb);
		list2 = (ListView) findViewById(R.id.list2);
	}
}
