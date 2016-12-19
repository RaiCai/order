package com.example.chongdong;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SinginActivity extends Activity{

	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login);
	        
	        
	    }
	 public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			switch (item.getItemId()) {
			case R.id.loginitem:
				Intent intent = new Intent();
				intent.setClass(SinginActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.enrollitem:
				Intent intent1 = new Intent();
				intent1.setClass(SinginActivity.this, SinginActivity.class);
				startActivity(intent1);
				finish();
				break;

			default:
				break;
			}

			return super.onOptionsItemSelected(item);
		}

		private void setIconVisible(Menu menu) {

			try {

				Class<?> clazz = Class
						.forName("com.android.internal.view.menu.MenuBuilder");
				// 未知的类
				Method m = clazz.getDeclaredMethod("setOptionalIconsVisible",

				boolean.class);

				m.setAccessible(true);

				m.invoke(menu, true);
				// MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}


}
