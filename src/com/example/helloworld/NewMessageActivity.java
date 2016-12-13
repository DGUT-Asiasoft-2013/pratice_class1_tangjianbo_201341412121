package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.api.Server;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewMessageActivity extends Activity {
	SimpleTextInputCellFragment fragTitle;
	EditText fragText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_new_message);
		
		fragTitle=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_title);
		fragText=(EditText) findViewById(R.id.input_text);
		findViewById(R.id.btn_new_message_submit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				onSubmit();
				overridePendingTransition(R.anim.none,R.anim.slide_out_bottom);
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		fragTitle.setLabelText("标题");
		fragTitle.setHintText("请输入标题");
		
	}
	
	void onSubmit(){
		String title=fragTitle.getText();
		String text=fragText.getText().toString();
		OkHttpClient client=Server.getSharedClient();
		MultipartBody Body = new MultipartBody.Builder()
				.addFormDataPart("title", title)
				.addFormDataPart("text", text)
				.build();

		Request request=Server.requestBuilderWithApi("article")
				.post(Body)
				.build();
		
		Server.getSharedClient().newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				final String responseBody=arg1.body().string();
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						NewMessageActivity.this.onSucceed(responseBody);
						
					}
				});
				
			}
			
			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						NewMessageActivity.this.onFailure(arg1);
						
					}
				});
				
			}
		});
		
	}

	protected void onFailure(IOException arg1) {
		new AlertDialog.Builder(this)
		.setTitle("上传失败")
		.setMessage(arg1.getLocalizedMessage())
		.setNegativeButton("好", null)
		.show();
		
	}

	protected void onSucceed(String text) {
		new AlertDialog.Builder(this)
		.setTitle("上传成功")
		.setMessage(text)
		.setPositiveButton("好", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		})
		.show();
		
	}

}
