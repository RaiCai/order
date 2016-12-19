package com.example.chongdong;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class phoneNumber_page extends Fragment {
	private Button bt_login,bt_get_verification_code;
    private EditText et_phonenum,et_verification_code;
    private View view;
    private String APPKEY="194d1cf5018c4";
    private String APPSECRET="e169bfcfd9e0d9d2f04a07a9ae05e30c";
    private EventHandler eventhand;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		SMSSDK.initSDK(getActivity(), APPKEY, APPSECRET);
		Bmob.initialize(getActivity(),"afcae4b640de26aa1ae9ef2cb91fe1cf");
		view=inflater.inflate(R.layout.phonenumber_sigin, container,false);
		bindfindid();
		eventhand=new EventHandler(){
			@Override
			public void afterEvent(int event, int result, Object data) {

			   if (result == SMSSDK.RESULT_COMPLETE) {
				//回调完成
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
					//提交验证码成功,跳转页面
					final String Mobilenum=et_phonenum.getText().toString();
					final String password=et_verification_code.getText().toString();
					BmobQuery<userjavabean> query=new BmobQuery<userjavabean>();
					query.addWhereEqualTo("mobilenum", Mobilenum);
					query.findObjects(new FindListener<userjavabean>() {
						
						@Override
						public void done(List<userjavabean> arg0, BmobException e) {
							// TODO Auto-generated method stub
							if(e==null)
							{
								Intent intent=new Intent(getActivity(),MainActivity.class);
								startActivity(intent);
							}
							else {
								userjavabean adddata=new userjavabean();
								adddata.setTableName("usertable");
								adddata.setMobilenum(Mobilenum);
								adddata.setPassword(password);
								adddata.save(new SaveListener<String>() {
									
									@Override
									public void done(String arg0, BmobException arg1) {
										// TODO Auto-generated method stub
										return;
									}
								});
								Intent intent=new Intent(getActivity(),MainActivity.class);
								startActivity(intent);
							}
						}
					});
					
                
				}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
			    //获取验证码成功
				}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                //返回支持发送验证码的国家列表
                } 
              }else{                                                                 
                 ((Throwable)data).printStackTrace(); 
          }
      } 
   }; 
SMSSDK.registerEventHandler(eventhand); //注册短信回调
		//为“获取验证码”按钮设置点击监听
	    bt_get_verification_code.setOnClickListener(new android.view.View.OnClickListener() {
						
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//判断手机号码是否为空
				if(et_phonenum.getText().toString().equals("")==false){
                   //判断手机号码是否正确
					if(et_phonenum.getText().length()==11)
					{
						SMSSDK.getVerificationCode("86", et_phonenum.getText().toString());
					}
					else {
						Toast.makeText(getActivity(), "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
					}
				}else {
					Toast.makeText(getActivity(), "手机号码不能为空！", Toast.LENGTH_LONG).show();
				}
			}
			 
		});
	    et_verification_code.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
						if(et_verification_code.getText().toString().equals(""))
						{
							bt_login.setEnabled(false);
						}
						else {
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
				SMSSDK.submitVerificationCode("86", et_phonenum.getText().toString(), et_verification_code.getText().toString());
			}
		});
		return view;
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		SMSSDK.unregisterEventHandler(eventhand);
		super.onDestroy();
	}
	private void bindfindid()
	{
		bt_get_verification_code=(Button)view.findViewById(R.id.get_verification_code);
		bt_login=(Button)view.findViewById(R.id.login);
		et_phonenum=(EditText)view.findViewById(R.id.phonenum);
		et_verification_code=(EditText)view.findViewById(R.id.input_verification_code);
	}			

}
