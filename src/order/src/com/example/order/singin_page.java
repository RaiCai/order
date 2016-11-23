package com.example.order;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class singin_page extends Activity{
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.singin);
	        //获取ActionBar
	        ActionBar actionBar=getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	//为actionbar设置监听
	 @Override
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
	 	// TODO Auto-generated method stub
	 	switch (item.getItemId()) {
	 	case android.R.id.home:
	 		Intent intent=new Intent(singin_page.this,LoginActivity.class);
	 		startActivity(intent);
	 		break;

	 	default:
	 		break;
	 	}
	 	return super.onMenuItemSelected(featureId, item);

	 }
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     // Inflate the menu; this adds items to the action bar if it is present.
	     getMenuInflater().inflate(R.menu.main, menu);
	     return true;
	 }
}
