package com.example.helloworld;

import com.example.helloworld.fragments.pages.FeedContentFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedContentActivity extends Activity {
	FeedContentFragment feedContentText;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		String text=getIntent().getStringExtra("text");
		
		
		setContentView(R.layout.activity_feed_content);
		feedContentText=(FeedContentFragment)getFragmentManager().findFragmentById(R.id.feed_text);
		
		
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

}
