package com.example.ce.adpter;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ce.CE;
import com.example.ce.R;

public class schedule_list extends Activity {
	ListView listview;
	String ii;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_list);

		listview = (ListView) findViewById(R.id.listView1);
		file();

	}

	public void file() {
		Bundle bundle = this.getIntent().getExtras();

		ii = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID

		String[] days = { "設計工程", "工程專案", "檔案管理" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, days);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// TODO Auto-generated method stub
				Intent intent = new Intent();
				switch (arg2) {
				case 0:
					intent.setClass(schedule_list.this, CE.class);
					Bundle bundle = new Bundle();
					bundle.putString("id", ii);
					intent.putExtras(bundle);
					startActivity(intent);
					Log.d("me", ii);
					break;
				case 1:
					intent.setClass(schedule_list.this, CE.class);
					Bundle bundle1 = new Bundle();
					bundle1.putString("id", ii);
					intent.putExtras(bundle1);
					startActivity(intent);
					Log.d("you", ii);
					break;
				case 2:
					intent.setClass(schedule_list.this, CE.class);
					Bundle bundle2 = new Bundle();
					bundle2.putString("id", ii);
					intent.putExtras(bundle2);
					startActivity(intent);
					Log.d("oh", ii);
					break;

				default:
					break;
				}

			}
		});
	}

}
