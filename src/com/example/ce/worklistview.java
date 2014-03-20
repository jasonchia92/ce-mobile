//package com.example.ce;
//
//import java.util.ArrayList;
//
//import android.app.ActionBar;
//import android.app.ActionBar.OnNavigationListener;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//public class worklistview extends Activity {
//	private ListView lv;
//	private String listview_array[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE",
//			"SIX", "SEVEN", "EIGHT", "NINE", "TEN" }; // /listview的陣列
//
//	public void onCreate(Bundle icicle)
//
//	{
//		super.onCreate(icicle);
//		setContentView(R.layout.worklistview);
//		final ActionBar actionBar = getActionBar();
//		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//		ArrayList itemList = new ArrayList();
//		itemList.add("TEST");
//		itemList.add("TEST1");
//		itemList.add("TEST2");
//		ArrayAdapter adapter = new ArrayAdapter(this,
//				android.R.layout.simple_list_item_1, android.R.id.text1,
//				itemList);
//		actionBar.setListNavigationCallbacks(adapter, new actionfunction());
//
//		lv = (ListView) findViewById(R.id.listview);
//		lv.setAdapter(new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, listview_array));
//		lv.setTextFilterEnabled(true);
//		lv.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> arg0, View v, int position,
//					long id) {
//				AlertDialog.Builder adb = new AlertDialog.Builder(
//						worklistview.this);
//				adb.setTitle("公告");
//				adb.setMessage("Selected Item is = "
//						+ lv.getItemAtPosition(position));
//				adb.setPositiveButton("Ok", null);
//				adb.show();
//			}
//		});
//	}
//
//	class actionfunction implements OnNavigationListener {
//
//		@Override
//		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
//			// TODO Auto-generated method stub
//			if (itemPosition == 0) {
//
//			} else if (itemPosition == 1) {
//				Intent intent = new Intent();
//
//				intent.setClass(worklistview.this, newwork.class);
//
//				startActivity(intent);
//
//				finish();
//			} else {
//				Toast toast = Toast.makeText(worklistview.this, "選擇 TEST2",
//						Toast.LENGTH_LONG);
//				toast.show();
//			}
//			return true;
//		}
//	}
//
//}
