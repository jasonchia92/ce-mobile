package com.example.ce;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	// ©wúå¤TŸÄFragmentªº¯Á¤Þ
	public static final int F1 = 0;
	public static final int F2 = 1;
	public static final int F3 = 2;

	public ViewPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int Index) {
		Fragment mFragemnt = null;
		switch (Index) {
		case F1:
			mFragemnt = new F1();
			break;
		case F2:
			mFragemnt = new F2();
			break;
		case F3:
			mFragemnt = new F3();
			break;
		}
		return mFragemnt;
	}

	@Override
	public int getCount() {
		return 3;
	}

}