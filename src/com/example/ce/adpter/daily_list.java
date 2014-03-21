package com.example.ce.adpter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ce.CE_daily;
import com.example.ce.Http_Post;
import com.example.ce.R;

public class daily_list extends Activity {
	private ImageButton but1;
	private ListView listV;
	String Response;
	JSONObject object;
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	String FILLING_DATE;
	String FILLING_NAME;
	String START_DATE;
	String FINISH_DATE;
	String WORK_PLAN[];
	String SUPERVISION_STATUS[];
	String FAILURE_IMPROVE[];
	String id;
	String Poject_id;
	// layout use arry Textview 使用
	String[] from = { "FILLING_DATE", "FILLING_NAME", "WORK_PLAN" };
	// layout 使用陣列
	int[] to = { 
			R.id.textView1, R.id.textView2,
			R.id.textView3, };
	Message msg = new Message();
	SimpleAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daily_list);

		listV = (ListView) findViewById(R.id.listView1);
		click();
		view();
	}

	private void view() {

		Bundle bundle = this.getIntent().getExtras();
		id = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID

		final String url = "http://apps.csie.stu.edu.tw:3980/api/project/getDairy/project_id/"
				+ id;
		Thread thread = new Thread() {
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 0:
						break;
					case 1:
						adapter = new SimpleAdapter(daily_list.this, list,
								R.layout.daily_adpter, from, to);
						adapter.notifyDataSetChanged();
						listV.setAdapter(adapter);// setListAdapter(adapter);
						break;
					}
				}
			};

			public void run() {
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					Http_Post response = new Http_Post();
					Response = response.Response(url, nameValuePairs);
					if (Response == null) {
						msg.what = 0;
					} else {
						JSONObject jsonResponse;
						jsonResponse = new JSONObject(Response);
						// / 陣列接jsonArray的值
						JSONArray jsonArray = jsonResponse.getJSONArray("data");
						WORK_PLAN = new String[jsonArray.length()];
						FAILURE_IMPROVE = new String[jsonArray.length()];
						SUPERVISION_STATUS = new String[jsonArray.length()];
						for (int i = 0; i < jsonArray.length(); i++) {
							object = jsonArray.getJSONObject(i);
							// Log 顯示 Json的值
							// 陣列字串接值
							FILLING_DATE = object.getString("FILLING_DATE");
							FILLING_NAME = object.getString("FILLING_NAME");
							START_DATE = object.getString("START_DATE");
							FINISH_DATE = object.getString("FINISH_DATE");
							FINISH_DATE = object.getString("FINISH_DATE");
							WORK_PLAN[i] = object.getString("WORK_PLAN");
							FAILURE_IMPROVE[i] = object.getString("FAILURE_IMPROVE");
							SUPERVISION_STATUS[i] = object
									.getString("SUPERVISION_STATUS");
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("FILLING_DATE", FILLING_DATE);
							map.put("FILLING_NAME", FILLING_NAME);
							map.put("WORK_PLAN", WORK_PLAN[i]);

							list.add(map);
							msg.what = 1;

						}
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		thread.start();
		listV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new Builder(daily_list.this);
				builder.setMessage(
						WORK_PLAN[arg2] + "\n" + FAILURE_IMPROVE[arg2])
						.setTitle(FILLING_DATE)
						.setPositiveButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();

									}
								})
						.setNegativeButton("確認",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();

									}
								}).show();
			}
		});
	}

	public void click() {
		but1 = (ImageButton) findViewById(R.id.but1);
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(DateUtils.formatDateTime(getBaseContext(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_NUMERIC_DATE
						| DateUtils.FORMAT_24HOUR));
		Bundle bundle = this.getIntent().getExtras();
		id = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID
		but1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				intent.setClass(daily_list.this, CE_daily.class);

				Bundle test2 = new Bundle();
				test2.putString("id", id);

				intent.putExtras(test2);
				startActivity(intent);

			}
		});
	}

}
