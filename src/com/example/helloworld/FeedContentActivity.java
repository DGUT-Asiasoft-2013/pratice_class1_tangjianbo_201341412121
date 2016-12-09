package com.example.helloworld;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedContentActivity extends Activity {
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		String text=getIntent().getStringExtra("text");
		
		
		setContentView(R.layout.activity_feed_content);
		
		TextView textView = (TextView) findViewById(R.id.text);
		
		textView.setText(text);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

}
