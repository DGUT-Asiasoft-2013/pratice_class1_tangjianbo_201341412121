package com.example.helloworld;

import com.example.helloworld.fragments.pages.FeedListFragment;
import com.example.helloworld.fragments.pages.MyProfileFragment;
import com.example.helloworld.fragments.pages.NoteListFragment;
import com.example.helloworld.fragments.pages.SearchPageFragment;
import com.example.helloworld.fragments.widget.MainTabbarFragment;
import com.example.helloworld.fragments.widget.MainTabbarFragment.OnTabSelectedListener;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class HelloWorldActivity extends Activity {
	FeedListFragment contentFeedList =new FeedListFragment();
	NoteListFragment contentNoteList =new NoteListFragment();
	SearchPageFragment contentSearchPage=new SearchPageFragment();
	MyProfileFragment contentMyprofile =new MyProfileFragment();
	
	MainTabbarFragment tabbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_helloworld);
		
		tabbar = (MainTabbarFragment)getFragmentManager().findFragmentById(R.id.frag_tabber);
		tabbar.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabSelected(int index) {
				changeContentFragment(index);
				
			}
		});
		
		findViewById(R.id.btn_new).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goNewMessage();
			}
		});
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		tabbar.setSelectedItem(0);
	}
	
	void changeContentFragment(int index){
		Fragment newFrag=null;
		switch(index){
		case 0: newFrag=contentFeedList;break;
		case 1: newFrag=contentNoteList;break;
		case 2: newFrag=contentSearchPage;break;
		case 3: newFrag=contentMyprofile;break;
		
		default:break;
		}
		
		if(newFrag==null)return;
		
		getFragmentManager()
		.beginTransaction()
		.replace(R.id.content, newFrag)
		.commit();
	}
	
	void goNewMessage(){
		Intent itnt=new Intent(this,NewMessageActivity.class);
		startActivity(itnt);
		
	}

}
