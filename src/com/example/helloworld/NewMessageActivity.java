package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class NewMessageActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_new_message);
		
		findViewById(R.id.btn_new_message_submit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
				overridePendingTransition(R.anim.none,R.anim.slide_out_bottom);
			}
		});
	}

}
