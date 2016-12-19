package com.example.chongdong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class geren extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startpage);
		 Runnable runnable=new Runnable() {
	    		
	    		@Override
	    		public void run() {
	    			// TODO Auto-generated method stub
	    			Intent intent=new Intent();
	    			intent.setClass(geren.this, LoginActivity.class);
	    			startActivity(intent);
	    			finish();
	    		}
	    	};
	    	Handler handler=new Handler();
	    	handler.postDelayed(runnable, 1500);//µÈ´ý1.5s
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
