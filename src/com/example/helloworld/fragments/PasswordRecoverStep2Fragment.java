package com.example.helloworld.fragments;


import com.example.helloworld.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverStep2Fragment extends Fragment {
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null);{
			view=inflater.inflate(R.layout.fragment_password_recover_step2, null);
		}
		return view;
	}

}
