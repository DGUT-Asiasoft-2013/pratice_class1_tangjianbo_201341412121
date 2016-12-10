package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.fragments.inputcells.PictureInputCellFragment;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RegisterActivity extends Activity {
	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellName;
	SimpleTextInputCellFragment fragInputCellEmailAddress;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	PictureInputCellFragment fragInputAvatar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);

		fragInputCellAccount = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellName = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_name);
		fragInputCellEmailAddress=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_email);
		fragInputCellPassword = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);
		fragInputAvatar=(PictureInputCellFragment)getFragmentManager().findFragmentById(R.id.input_picture);


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		fragInputCellAccount.setLabelText("’Àªß√˚");
		fragInputCellAccount.setHintText("«Î ‰»Î’Àªß√˚");

		fragInputCellName.setLabelText("Í«≥∆");
		fragInputCellName.setHintText("«Î ‰»ÎÍ«≥∆");

		fragInputCellPassword.setLabelText("√‹¬Î");
		fragInputCellPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragInputCellPassword.setIsPassword(true);

		fragInputCellPasswordRepeat.setLabelText("÷ÿ∏¥√‹¬Î");
		fragInputCellPasswordRepeat.setHintText("«Î÷ÿ∏¥ ‰»Î√‹¬Î");
		fragInputCellPasswordRepeat.setIsPassword(true);

		fragInputCellEmailAddress.setLabelText("µÁ◊”” º˛");
		fragInputCellEmailAddress.setHintText("«Î ‰»ÎµÁ◊”” œ‰");
		
		findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				submit();
				
			}
		});


	}

	void submit(){
		String password=fragInputCellPassword.getText();
		String passwordRepeat =fragInputCellPasswordRepeat.getText();
		
		if(!password.equals(passwordRepeat)){
			new AlertDialog.Builder(RegisterActivity.this)
			.setMessage("÷ÿ∏¥√‹¬Î≤ª“ª÷¬")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setNegativeButton("∫√", null)
			.show();
			
			return;
		}
		
		password=MD5.getMD5(password);

		String account =fragInputCellAccount.getText();
		String name =fragInputCellName.getText();
		String email= fragInputCellEmailAddress.getText();
		
		OkHttpClient client=new OkHttpClient();
		MultipartBody.Builder requestBodyBuilder =new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("account", account)
				.addFormDataPart("name", name)
				.addFormDataPart("email", email)
				.addFormDataPart("passwordHash", password);
		
		if(fragInputAvatar.getPngData()!=null){
			requestBodyBuilder.addFormDataPart(
					"avatar", 
					"avatar",
					RequestBody.create(MediaType.parse("image/png"),
					fragInputAvatar.getPngData()));
		}
		
		Request request=new Request.Builder()
				.url("http://172.27.0.26:8080/membercenter/api/register")
				.method("post", null)
				.post(requestBodyBuilder.build())
				.build();
		
		final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
		progressDialog.setMessage("«Î…‘∫Ú");
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						progressDialog.dismiss();
						
						try{
							RegisterActivity.this.onResponse(arg0,arg1.body().string());
						}catch(Exception e){
							e.printStackTrace();
							RegisterActivity.this.onFailure(arg0,e);
						}
					}
				});
				
			}
			
			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						progressDialog.dismiss();
						onFailure(arg0, arg1);
						
					}
				});
				
			}
		});
	}
	
	void onResponse(Call arg0, String responseBody){
		new AlertDialog.Builder(this)
		.setTitle("µ«¬º≥…π¶")
		.setMessage(responseBody)
		.setPositiveButton("∫√", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				
			}
		})
		.show();
		
	}
	
	public void onFailure(Call arg0, Exception arg1) {
		new AlertDialog.Builder(this)
		.setTitle("µ«¬º ß∞‹")
		.setMessage(arg1.getLocalizedMessage())
		.setNegativeButton("∫√", null)
		.show();
	}

}
