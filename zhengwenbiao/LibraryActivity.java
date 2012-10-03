package com.zhengwenbiao;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class LibraryActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost mTabHost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.main,
				mTabHost.getTabContentView());
		Intent intent1 = new Intent(LibraryActivity.this, TabA.class);
		TabHost.TabSpec mtab1 = mTabHost.newTabSpec("taba").setIndicator(
				"推荐好书", null);
		mtab1.setContent(intent1);
		mTabHost.addTab(mtab1);
		Intent intent2 = new Intent(LibraryActivity.this, TabB.class);
		TabHost.TabSpec mtab2 = mTabHost.newTabSpec("tabb").setIndicator(
				"索引书单", null);
		mtab2.setContent(intent2);
		mTabHost.addTab(mtab2);
	}
}