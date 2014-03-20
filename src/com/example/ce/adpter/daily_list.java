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
	String DAIRY_ID[];
	String PROJECT_ID;
	String MANAGEMENT_ID;
	String PROJECT_NUMBER;
	String NAME;
	String FILLING_DATE;
	String FILLING_NAME;
	String START_DATE;
	String FINISH_DATE;
	String WORK_PLAN[];
	String SUPERVISION_STATUS;
	String FAILURE_IMPROVE[];
	String MANAGEMENT_USER_ID;
	String MANAGEMENT_NAME_CHINESE;
	String MANAGEMENT_NAME_ENGLISH;
	String MANAGEMENT_USER_ACCOUNT;
	String ii;
	String Poject_id;
	// layout use arry Textview 使用
	String[] from = { "DAIRY_ID", "PROJECT_ID", "MANAGEMENT_ID",
			"PROJECT_NUMBER", "NAME", "FILLING_DATE", "FILLING_NAME",
			"START_DATE", "FINISH_DATE", "WORK_PLAN", "SUPERVISION_STATUS",
			"FAILURE_IMPROVE", "MANAGEMENT_USER_ID", "MANAGEMENT_NAME_CHINESE",
			"MANAGEMENT_NAME_ENGLISH", "MANAGEMENT_USER_ACCOUNT" };
	// layout 使用陣列
	int[] to = { R.id.textView1, R.id.textView2, R.id.textView3,
			R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7,
			R.id.textView8, R.id.textView9, R.id.textView10, R.id.textView11,
			R.id.textView12, R.id.textView13, R.id.textView14, R.id.textView15,
			R.id.textView16 };
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
		Log.d("123", bundle.toString());
		ii = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID

		final String url = "http://apps.csie.stu.edu.tw:3980/api/project/getDairy/project_id/"
				+ ii;
		Log.d("daily_url", url);
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
						DAIRY_ID = new String[jsonArray.length()];
						WORK_PLAN = new String[jsonArray.length()];
						FAILURE_IMPROVE = new String[jsonArray.length()];
						for (int i = 0; i < jsonArray.length(); i++) {
							object = jsonArray.getJSONObject(i);
							// Log 顯示 Json的值

							Log.d("DAIRY_ID", object.getString("DAIRY_ID"));
							Log.d("PROJECT_ID", object.getString("PROJECT_ID"));
							Log.d("MANAGEMENT_ID",
									object.getString("MANAGEMENT_ID"));
							Log.d("PROJECT_NUMBER",
									object.getString("PROJECT_NUMBER"));
							Log.d("NAME", object.getString("NAME"));
							Log.d("FILLING_DATE",
									object.getString("FILLING_DATE"));
							Log.d("FILLING_NAME",
									object.getString("FILLING_NAME"));
							Log.d("START_DATE", object.getString("START_DATE"));
							Log.d("FINISH_DATE",
									object.getString("FINISH_DATE"));
							Log.d("WORK_PLAN", object.getString("WORK_PLAN"));
							Log.d("SUPERVISION_STATUS",
									object.getString("SUPERVISION_STATUS"));
							Log.d("FAILURE_IMPROVE",
									object.getString("FAILURE_IMPROVE"));
							Log.d("MANAGEMENT_USER_ID",
									object.getString("MANAGEMENT_USER_ID"));
							Log.d("MANAGEMENT_NAME_CHINESE",
									object.getString("MANAGEMENT_NAME_CHINESE"));
							Log.d("MANAGEMENT_NAME_ENGLISH",
									object.getString("MANAGEMENT_NAME_ENGLISH"));
							Log.d("MANAGEMENT_USER_ACCOUNT",
									object.getString("MANAGEMENT_USER_ACCOUNT"));
							// 陣列字串接值
							DAIRY_ID[i] = object.getString("DAIRY_ID");
							PROJECT_ID = object.getString("PROJECT_ID");
							MANAGEMENT_ID = object.getString("MANAGEMENT_ID");
							PROJECT_NUMBER = object.getString("PROJECT_NUMBER");
							NAME = object.getString("NAME");
							FILLING_DATE = object.getString("FILLING_DATE");
							FILLING_NAME = object.getString("FILLING_NAME");
							START_DATE = object.getString("START_DATE");
							FINISH_DATE = object.getString("FINISH_DATE");
							FINISH_DATE = object.getString("FINISH_DATE");
							WORK_PLAN[i] = object.getString("WORK_PLAN");
							SUPERVISION_STATUS = object
									.getString("SUPERVISION_STATUS");
							FAILURE_IMPROVE[i] = object
									.getString("FAILURE_IMPROVE");
							MANAGEMENT_USER_ID = object
									.getString("MANAGEMENT_USER_ID");
							MANAGEMENT_NAME_CHINESE = object
									.getString("MANAGEMENT_NAME_CHINESE");
							MANAGEMENT_NAME_ENGLISH = object
									.getString("MANAGEMENT_NAME_ENGLISH");
							MANAGEMENT_USER_ACCOUNT = object
									.getString("MANAGEMENT_USER_ACCOUNT");
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("DAIRY_ID", DAIRY_ID[i]);
							map.put("PROJECT_ID", PROJECT_ID);
							map.put("MANAGEMENT_ID", MANAGEMENT_ID);
							map.put("PROJECT_NUMBER", PROJECT_NUMBER);
							map.put("NAME", NAME);
							map.put("FILLING_DATE", FILLING_DATE);
							map.put("FILLING_NAME", FILLING_NAME);
							map.put("START_DATE", START_DATE);
							map.put("FINISH_DATE", FINISH_DATE);
							map.put("WORK_PLAN", WORK_PLAN[i]);
							map.put("SUPERVISION_STATUS", SUPERVISION_STATUS);
							map.put("FAILURE_IMPROVE", FAILURE_IMPROVE[i]);
							map.put("MANAGEMENT_USER_ID", MANAGEMENT_USER_ID);
							map.put("MANAGEMENT_NAME_CHINESE",
									MANAGEMENT_NAME_CHINESE);
							map.put("MANAGEMENT_NAME_ENGLISH",
									MANAGEMENT_NAME_ENGLISH);
							map.put("MANAGEMENT_USER_ACCOUNT",
									MANAGEMENT_USER_ACCOUNT);

							list.add(map);
							Log.d("Map", map.toString());

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
				Toast.makeText(daily_list.this, "YES", Toast.LENGTH_SHORT)
						.show();
				AlertDialog.Builder builder = new Builder(daily_list.this);
				builder.setMessage(
						WORK_PLAN[arg2] + "\n" + FAILURE_IMPROVE[arg2])
						.setTitle(DAIRY_ID[arg2])
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
						| DateUtils.FORMAT_12HOUR));
		Bundle bundle = this.getIntent().getExtras();
		Log.d("123", bundle.toString());
		ii = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID
		but1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();

				intent.setClass(daily_list.this, CE_daily.class);

				Bundle test2 = new Bundle();
				test2.putString("id", ii);

				intent.putExtras(test2);
				startActivity(intent);

			}
		});
	}

}
