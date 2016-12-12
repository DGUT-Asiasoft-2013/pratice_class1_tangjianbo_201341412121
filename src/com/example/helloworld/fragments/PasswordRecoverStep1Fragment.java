package com.example.helloworld.fragments;

import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class PasswordRecoverStep1Fragment extends Fragment {
	SimpleTextInputCellFragment fragEmail;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(view==null){
		view = inflater.inflate(R.layout.fragment_password_recover_step1, null);

		fragEmail =(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_email);

		view.findViewById(R.id.btn_next).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				goNext();
			}
		});
		}
		return view;
	}

	@Override
	public void onResume() {

		super.onResume();

		fragEmail.setLabelText("ע������");
		fragEmail.setHintText("������ע�������ַ");
	}
	
	public static interface OnGoNextListener{
		void onGoNext();
	}
	
	OnGoNextListener onGoNextLisener;
	
	public void setOnGoNextListener(OnGoNextListener onGoNextLisener){
		this.onGoNextLisener= onGoNextLisener;
	}
	void goNext() {
		if(onGoNextLisener!=null){
			onGoNextLisener.onGoNext();
		}
	}

	public String getText() {
		// TODO Auto-generated method stub
		return fragEmail.getText().toString();
	
}
}
