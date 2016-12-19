package com.example.chongdong;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private ListView list;

 
	private Button geren;
	/**
	 * ViewPager
	 */
	private ViewPager viewPager;

	/**
	 * װ����ImageView����
	 */
	private ImageView[] tips;

	/**
	 * װImageView����
	 */
	private ImageView[][] mImageViews;

	/**
	 * ͼƬ��Դid
	 */
	private int[] imgIdArray;

	private static final int MSG_CHANGE_PHOTO = 1;

	/** ͼƬ�Զ��л�ʱ�� */
	private static final int PHOTO_CHANGE_TIME = 5000;
	private Handler mHandler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case MSG_CHANGE_PHOTO:
				int index = viewPager.getCurrentItem();
				viewPager.setCurrentItem(index + 1);
				mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO,
						PHOTO_CHANGE_TIME);
				break;
			}
			super.dispatchMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//����
		list=(ListView)findViewById(R.id.listView1);
		
	        SimpleAdapter adapter=new SimpleAdapter(MainActivity.this, getData(), R.layout.listview, new String[]{"title","info","img"}, new int[]{R.id.title,R.id.info,R.id.img});
	        list.setAdapter(adapter);
	        
	        
	        
		//����
		
		geren=(Button)findViewById(R.id.button3);
		geren.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this,LoginActivity.class);
				startActivity(intent);
				
			}
		});
		
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		// ����ͼƬ��ԴID
		imgIdArray = new int[] { R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6,R.drawable.item7,R.drawable.item8};

		// �������뵽ViewGroup��
		tips = new ImageView[imgIdArray.length];

		if (imgIdArray.length <= 1)
			group.setVisibility(View.GONE);
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}

			group.addView(imageView);
		}

		mImageViews = new ImageView[2][];
		// ��ͼƬװ�ص�������,����һ�����ƻ��壬��ֹͼƬ��ʱ���ֺ�ɫͼƬ������ʾ������
		mImageViews[0] = new ImageView[imgIdArray.length];
		mImageViews[1] = new ImageView[imgIdArray.length];

		for (int i = 0; i < mImageViews.length; i++) {
			for (int j = 0; j < mImageViews[i].length; j++) {
				ImageView imageView = new ImageView(this);
				imageView.setBackgroundResource(imgIdArray[j]);
				mImageViews[i][j] = imageView;
				Log.i("TwoActivity_WY", i + "," + j + "\t");
			}
		}

		// ����Adapter
		viewPager.setAdapter(new MyAdapter());
		// ���ü�������Ҫ�����õ��ı���
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageSelected(int arg0) {
				setImageBackground(arg0 % imgIdArray.length);
			}
		});
		
		viewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(imgIdArray.length==0||imgIdArray.length==1)
					return true;
				else 
					return false;
			}
		});

		// ����ViewPager��Ĭ����, ����Ϊ���ȵ�50���������ӿ�ʼ�������󻬶�
		viewPager.setCurrentItem((imgIdArray.length) * 50);
		if(imgIdArray.length>1){
			mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);
		}
		
	}
	 private List<Map<String, Object>> getData() {
			// TODO Auto-generated method stub
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    	  Map<String, Object> map = new HashMap<String, Object>();
	    	  map.put("title", "过桥米线黄焖鸡");
	          map.put("info", "活动1");
	          map.put("img", R.drawable.bing);
	          list.add(map);
	   
	          map = new HashMap<String, Object>();
	          map.put("title", "骨头饭");
	          map.put("info", "买一送一");
	          map.put("img", R.drawable.doufu);
	          list.add(map);
	   
	          map = new HashMap<String, Object>();
	          map.put("title", "杭州风味");
	          map.put("info", "活动3");
	          map.put("img", R.drawable.fantyan);
	          list.add(map);
	          map = new HashMap<String, Object>();
	          map.put("title", "公元前");
	          map.put("info", "满10减3");
	          map.put("img", R.drawable.kaorou);
	          list.add(map);
	          map = new HashMap<String, Object>();
	          map.put("title", "兰州拉面");
	          map.put("info", "满15减2");
	          map.put("img", R.drawable.yu);
	          list.add(map);
	          return list;
		}
	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
 
		@Override
		public void destroyItem(View container, int position, Object object) {
			if (imgIdArray.length == 1)
				((ViewPager) container).removeView(mImageViews[position
						/ imgIdArray.length % 2][0]);
			else
				((ViewPager) container).removeView(mImageViews[position
						/ imgIdArray.length % 2][position % imgIdArray.length]);
		}

		/**
		 * ����ͼƬ��ȥ���õ�ǰ��position ���� ͼƬ���鳤��ȡ�����ǹؼ�
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			if (imgIdArray.length == 1){
				((ViewPager) container).addView(mImageViews[position / imgIdArray.length % 2][0]);
				return mImageViews[position / imgIdArray.length % 2][0];
			}
			else
				((ViewPager) container).addView(mImageViews[position
						/ imgIdArray.length % 2][position % imgIdArray.length],
						0);
			return mImageViews[position / imgIdArray.length % 2][position
					% imgIdArray.length];
		}

	}



	/**
	 * ����ѡ�е�tip�ı���
	 * 
	 * @param selectItemsIndex
	 */
	private void setImageBackground(int selectItemsIndex) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItemsIndex) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
