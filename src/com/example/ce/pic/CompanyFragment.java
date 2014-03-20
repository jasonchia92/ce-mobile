package com.example.ce.pic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ce.R;

public class CompanyFragment extends Fragment {
	
	private int value=0;
	private View view;
	
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		//PicTabhost_Activity mainActivity = (PicTabhost_Activity) activity;
		//value = mainActivity.get_data(0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_company, container, false);
		view.setTag("FragmentView2");
		
		return view;
	}

}
