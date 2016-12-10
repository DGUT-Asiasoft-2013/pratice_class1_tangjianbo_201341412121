package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.entity.User;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {
	SimpleTextInputCellFragment fragAccount,fragPassword;

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

		fragAccount=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);

	}

	@Override
	protected void onResume() {

		super.onResume();

		fragAccount.setLabelText("’Àªß√˚");
		fragAccount.setHintText("«Î ‰»Î’Àªß√˚");
		fragPassword.setLabelText("√‹¬Î");
		fragPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragPassword.setIsPassword(true);
	}

	void goLogin() {
		//		Intent itnt = new Intent(this,HelloWorldActivity.class);
		//		startActivity(itnt);

		String account = fragAccount.getText();
		String password = fragPassword.getText();



		password = MD5.getMD5(password);



		OkHttpClient client=new OkHttpClient();

		MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("account", account)

				.addFormDataPart("passwordHash", password);




		Request request=new Request.Builder()
				.url("http://172.27.0.26:8080/membercenter/api/login")
				.method("post", null)
				.post(requestBodyBuilder.build())
				.build();

		final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setMessage("«Î…‘∫Ú");
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);

		client.newCall(request).enqueue(new Callback() {


			@Override
			public void onResponse(final Call arg0,final Response arg1) throws IOException {

				final String responseString = arg1.body().string();
				ObjectMapper mapper = new ObjectMapper();
				final User user = mapper.readValue(responseString, User.class);

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						progressDialog.dismiss();

						new AlertDialog.Builder(LoginActivity.this)
						.setTitle("µ«¬º≥…π¶")
						.setMessage("Hello,"+user.getName())
						.setPositiveButton("∫√", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent itnt = new Intent(LoginActivity.this,HelloWorldActivity.class);
								startActivity(itnt);

							}
						})
						.show();


					}
				});

			}

			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						progressDialog.dismiss();

						LoginActivity.this.onFailure(arg0, arg1);

					}
				});

			}
		});




	}	

	void goRegister(){
		Intent itnt = new Intent(this,RegisterActivity.class);
		startActivity(itnt);
	}

	void goRecoverPassword(){
		Intent itnt = new Intent(this,PasswordRecoverActivity.class);
		startActivity(itnt);
	}
	

	void onFailure(Call arg0, Exception arg1) {
		new AlertDialog.Builder(this)
		.setTitle("µ«¬º ß∞‹")
		.setMessage(arg1.getLocalizedMessage())
		.setNegativeButton("∫√", null)
		.show();

	}

}
