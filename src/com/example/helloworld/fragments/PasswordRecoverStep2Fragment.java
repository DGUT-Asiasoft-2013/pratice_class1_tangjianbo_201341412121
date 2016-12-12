package com.example.helloworld.fragments;

import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverStep2Fragment extends Fragment {
	View view;
	SimpleTextInputCellFragment fragPassword;
	SimpleTextInputCellFragment fragPasswordRepeat;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		if(view==null){
			view = inflater.inflate(R.layout.fragment_password_recover_step2,null);
			fragPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
			fragPasswordRepeat=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password_repeat);
		}
		
		view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSubmitClicked();
			}
		});
		
		return view;
		
		
	}
	
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	

		fragPassword.setLabelText("√‹¬Î");{
			fragPassword.setHintText("«Î ‰»Î√‹¬Î");
			fragPassword.setIsPassword(true);
		}

		fragPasswordRepeat.setLabelText("»∑»œ√‹¬Î");{
			fragPasswordRepeat.setHintText("«Î÷ÿ–¬ ‰»Î√‹¬Î");
			fragPasswordRepeat.setIsPassword(true);
		}

	}
	
	public String getText(){
		return fragPassword.getText().toString();
	}
	
	public static interface OnSubmitClickedListener{
		void onSubmitClicked();
	}
	
	OnSubmitClickedListener onSubmitClickedListener;
	
	public void setOnSubmitClickedListener(OnSubmitClickedListener onSubmitClickedListener) {
		this.onSubmitClickedListener = onSubmitClickedListener;
	}
	
	void onSubmitClicked(){
		if(fragPassword.getText().equals(fragPasswordRepeat.getText())){
			if(onSubmitClickedListener!=null){
				onSubmitClickedListener.onSubmitClicked();
			}
		}else{
			new AlertDialog.Builder(getActivity())
			.setMessage("√‹¬Î≤ª“ª÷¬")
			.show();
		}
	}
}