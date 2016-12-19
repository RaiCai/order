package com.example.chongdong;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class LoginActivity extends FragmentActivity{
	private ViewPager mviewpager;
	private FragmentPagerAdapter madapter;
	private List<Fragment> mDatas;
	private TextView usertext, phonetext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alltable);
		findview();
	}
	private void findview() {
		// TODO Auto-generated method stub
		mviewpager = (ViewPager) findViewById(R.id.id_viewpager);
		mDatas = new ArrayList<Fragment>();
		usertext = (TextView) findViewById(R.id.usertextn);
		phonetext = (TextView) findViewById(R.id.phonebtext);
		username_page login_Page = new username_page();
		phoneNumber_page phone_login = new phoneNumber_page();

		mDatas.add(login_Page);// �����˻������¼��username_page_login_Page��xml�ļ�����
		mDatas.add(phone_login);// �����ֻ���֤��¼��phoneNumber_page_activity_main��xml�ļ�����
		madapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDatas.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mDatas.get(arg0);
			}
		};
		// ����������
		mviewpager.setAdapter(madapter);
		mviewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				retsetTextView();

				switch (position) {
				case 0:
					usertext.setTextColor(Color.GREEN);
					break;

				case 1:
					phonetext.setTextColor(Color.GREEN);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected void retsetTextView() {
		// TODO Auto-generated method stub
		usertext.setTextColor(Color.BLACK);
		phonetext.setTextColor(Color.BLACK);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.loginitem:
		
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.enrollitem:
			Intent intent1 = new Intent();
			intent1.setClass(LoginActivity.this, SinginActivity.class);
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
			// δ֪����
			Method m = clazz.getDeclaredMethod("setOptionalIconsVisible",

			boolean.class);

			m.setAccessible(true);

			m.invoke(menu, true);
			// MenuBuilderʵ��Menu�ӿڣ������˵�ʱ����������menu��ʵ����MenuBuilder����(java�Ķ�̬����)
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

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}
