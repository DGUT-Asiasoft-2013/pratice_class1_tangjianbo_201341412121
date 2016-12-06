package com.example.helloworld;

import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LoginActivity extends Activity {
	SimpleTextInputCellFragment fragAccout,fragPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goRegister();
			}
		});

		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				goLogin();	
			}
		});
		
		findViewById(R.id.btn_forgot_password).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goRecoverPassword();
			}
		});

		fragAccout=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);

	}

	@Override
	protected void onResume() {

		super.onResume();

		fragAccout.setLabelText("账户名");
		fragAccout.setHintText("请输入账户名");
		fragPassword.setLabelText("密码");
		fragPassword.setHintText("请输入密码");
		fragPassword.setIsPassword(true);
	}

	void goLogin() {


	}

	void goRegister(){
		Intent itnt = new Intent(this,RegisterActivity.class);
		startActivity(itnt);
	}
	
	void goRecoverPassword(){
		Intent itnt = new Intent(this,PasswordRecoverActivity.class);
		startActivity(itnt);
	}
}
