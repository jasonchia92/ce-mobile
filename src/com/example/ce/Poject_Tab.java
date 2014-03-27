package com.example.ce;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ce.adpter.daily_list;
import com.example.ce.index.Home;

public class Poject_Tab extends FragmentActivity implements TabListener,
		OnPageChangeListener {

	private ViewPager mViewPager;
	public static final int MAX_TAB_SIZE = 3;
	public static final String ARGUMENTS_NAME = "args";
	private TabFragmentPagerAdapter mAdapter;
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ce);

		findViewById();
		initView();
	}

	private void findViewById() {
		mViewPager = (ViewPager) this.findViewById(R.id.view);
	}

	private void initView() {

		final ActionBar mActionBar = getActionBar();

		mActionBar.setDisplayHomeAsUpEnabled(false);

		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				mActionBar.setSelectedNavigationItem(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		mActionBar.addTab(mActionBar.newTab().setText(R.string.Construction)
				.setTabListener(this));

		mActionBar.addTab(mActionBar.newTab().setText(R.string.Preparation)
				.setTabListener(this));
		mActionBar.addTab(mActionBar.newTab().setText(R.string.Closed)
				.setTabListener(this));
		mActionBar.setSelectedNavigationItem(0);

	}

	public static class TabFragmentPagerAdapter extends FragmentPagerAdapter {

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment ft = null;
			switch (arg0) {
			case 0:
				ft = new Poject_Tab1();
				break;
			case 1:
				ft = new Poject_Tab2();
				break;

			default:
				ft = new Poject_Tab3();

				break;
			}
			return ft;
		}

		@Override
		public int getCount() {

			return MAX_TAB_SIZE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "TAB " + (position + 1);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ce, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case R.id.home:
			intent.setClass(this, Home.class);
			startActivity(intent);
			break;
		case R.id.logout:
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("½T©wµn¥X")
					.setTitle("´£¿ô")
					.setPositiveButton("¨ú®ø",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();

								}
							})
					.setNegativeButton("½T»{",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();

								}
							}).show();
			break;

		default:
			break;
		}

		return true;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		this.invalidateOptionsMenu();
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	// Âêªð¦^Áä
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "¦A«ö¤@¦¸°h¥Xµ{§Ç",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}

}
