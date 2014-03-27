package com.example.ce.adpter;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public class CE_Tab<T extends Fragment> implements TabListener {

	private Fragment fragment;
	private Activity activity;
	private String myTag;
	private Class<T> myClass;
	private String data2[];

	public CE_Tab(Activity activity, String myTag, Class<T> myClass,String[] data) {
		this.activity = activity;
		this.myTag = myTag;
		this.myClass = myClass;
		this.data2 = data;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (fragment == null) {
			Bundle args = new Bundle();
	        args.putStringArray("data", data2);
//	        fragment.setArguments(args);
			fragment = Fragment.instantiate(activity, myClass.getName(),args);
			ft.add(android.R.id.content, fragment, myTag);
		} else {
			ft.attach(fragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (fragment != null) {
			ft.detach(fragment);
		}
	}

}
