package com.example.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Button bt_login,bt_singin,bt_get_verification_code;
    private EditText et_phonenum,et_verification_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        bindfindid();
        //为“获取验证码”按钮设置动作监听
        bt_get_verification_code.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//判断手机号码是否为空
				if(et_phonenum.getText().toString().equals("")==false){
                   //判断手机号码是否正确
					if(et_phonenum.getText().length()==11)
					{
						
					}
					else {
						Toast.makeText(LoginActivity.this, "请输入正确的手机号码！", Toast.LENGTH_LONG).show();
					}
				}else {
					Toast.makeText(LoginActivity.this, "手机号码不能为空！", Toast.LENGTH_LONG).show();
				}
			}
		});
        //Ϊ为注册按钮设置监听
        bt_singin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,singin_page.class);
				startActivity(intent);
			}
		});
    }
    //关联控件
private void bindfindid()
{
	bt_get_verification_code=(Button)findViewById(R.id.get_verification_code);
	bt_login=(Button)findViewById(R.id.login);
	bt_singin=(Button)findViewById(R.id.singin);
	et_phonenum=(EditText)findViewById(R.id.phonenum);
	et_verification_code=(EditText)findViewById(R.id.input_verification_code);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
