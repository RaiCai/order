package com.example.chongdong;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class username_page extends Fragment {
	private View view;
	private EditText et_mobile,et_password;
	private Button bt_login;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view= inflater.inflate(R.layout.username_sigin, container,false);
		Log.i("text", "555141411");
		Bmob.initialize(getActivity(), "afcae4b640de26aa1ae9ef2cb91fe1cf");
		Log.i("text", "5551414bhf11");
		findviewid();//綁定控件的ID
		setcontrollistener();//设置控件的监听事件
		Log.i("text", "55514sdsxd1411");
		return view;
	}
	private void setcontrollistener() {
		et_mobile.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				 if (et_mobile.getText().toString().equals("")) {
					
					et_password.setEnabled(false);
				} else {
					
					et_password.setEnabled(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		et_password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				 if (et_password.getText().toString().equals("")) {
						bt_login.setEnabled(false);
					} else {
						bt_login.setEnabled(true);
					}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			} 
		});
		 
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String Mobilenum=et_mobile.getText().toString();
				final String Password=et_password.getText().toString();	
				BmobQuery<userjavabean> query=new BmobQuery<userjavabean>();
				query.addWhereEqualTo("mobilenum", Mobilenum);
				query.addWhereEqualTo("password", Password);
				query.findObjects(new FindListener<userjavabean>() {
					
					@Override
					public void done(List<userjavabean> arg0, BmobException e) {
						// TODO Auto-generated method stub
						if (e==null) {							
						  Intent intent=new Intent(getActivity(),MainActivity.class);
							  startActivity(intent);
						}
						else {
							Toast.makeText(getActivity(), "账号或密码错误！", Toast.LENGTH_LONG).show();
							  Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
						}
					}
				});
			}
		});  
	}
	private void findviewid() {
		et_mobile=(EditText)view.findViewById(R.id.login_phonenum);
		et_password=(EditText)view.findViewById(R.id.login_password);
		bt_login=(Button)view.findViewById(R.id.login_btn);
		
	}
}
