package com.example.helloworld.fragments.pages;

import com.example.helloworld.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedContentFragment extends Fragment {

	View view;
	TextView feedContent;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if(view==null){
			view=inflater.inflate(R.layout.fragment_page_feed_content, null);
		}
		feedContent=(TextView)view.findViewById(R.id.feed_text);
		
		return view;
	}
	
	public void setFeedText(String feedContentText){
		feedContent.setText(feedContentText);
	}

}
