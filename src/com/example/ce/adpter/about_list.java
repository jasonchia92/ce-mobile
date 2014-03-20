package com.example.ce.adpter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.ce.Http_Post;
import com.example.ce.R;

public class about_list extends Activity {
	private ListView listV;
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	String Response;
	JSONObject object;
	String MANAGEMENT_ID[];
	String USER_CONTACT_PHONE[];
	String PROJECT_ID, USER_ID, DEPARTURE, USER_NAME_CHINESE,
			USER_NAME_ENGLISH, USER_ACCOUNT, USER_GID;
	// layout 使用陣列
	String[] from = { "MANAGEMENT_ID", "PROJECT_ID", "USER_ID", "DEPARTURE",
			"USER_GID", "USER_NAME_CHINESE", "USER_NAME_ENGLISH",
			"USER_ACCOUNT", "USER_CONTACT_PHONE" };
	// layout 使用陣列
	int[] to = { R.id.textView1, R.id.TextView08, R.id.TextView09,
			R.id.TextView10, R.id.TextView11, R.id.TextView12, R.id.TextView13,
			R.id.TextView14, R.id.TextView15 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fuck);
		listV = (ListView) findViewById(R.id.listView1);
		main();

	}

	private void main() {
		Bundle bundle = this.getIntent().getExtras();

		String ii = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID
		final String url = "http://apps.csie.stu.edu.tw:3980/api/project/getProjectManagement/project_id/"
				+ ii;
		Log.d("url", url);

		Thread thread = new Thread() {
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 1) {
						SimpleAdapter adapter = new SimpleAdapter(
								about_list.this, list, R.layout.about_adpter,
								from, to);
						listV.setAdapter(adapter);// setListAdapter(adapter);
						adapter.notifyDataSetChanged();
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
					JSONObject jsonResponse;
					jsonResponse = new JSONObject(Response);
					// / 陣列接jsonArray的值
					JSONArray jsonArray = jsonResponse.getJSONArray("data");
					MANAGEMENT_ID = new String[jsonArray.length()];
					USER_CONTACT_PHONE = new String[jsonArray.length()];
					for (int i = 0; i < jsonArray.length(); i++) {
						object = jsonArray.getJSONObject(i);
						// Log 顯示 Json的值

						Log.d("manageent_id", object.getString("MANAGEMENT_ID"));
						Log.d("PROJECT_ID", object.getString("PROJECT_ID"));
						Log.d("USER_ID", object.getString("USER_ID"));
						Log.d("DEPARTURE", object.getString("DEPARTURE"));
						Log.d("USER_GID", object.getString("USER_GID"));
						Log.d("USER_NAME_CHINESE",
								object.getString("USER_NAME_CHINESE"));
						Log.d("USER_NAME_ENGLISH",
								object.getString("USER_NAME_ENGLISH"));
						Log.d("USER_ACCOUNT", object.getString("USER_ACCOUNT"));
						// 陣列字串接值
						MANAGEMENT_ID[i] = object.getString("MANAGEMENT_ID");
						USER_GID = object.getString("USER_GID");
						PROJECT_ID = object.getString("PROJECT_ID");
						USER_ID = object.getString("USER_ID");
						DEPARTURE = object.getString("DEPARTURE");
						USER_NAME_CHINESE = object
								.getString("USER_NAME_CHINESE");
						USER_NAME_ENGLISH = object
								.getString("USER_NAME_ENGLISH");
						USER_ACCOUNT = object.getString("USER_ACCOUNT");
						USER_CONTACT_PHONE[i] = object
								.getString("USER_CONTACT_PHONE");

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("MANAGEMENT_ID", MANAGEMENT_ID[i]);
						map.put("PROJECT_ID", PROJECT_ID);
						map.put("USER_ID", USER_ID);
						map.put("DEPARTURE", DEPARTURE);
						map.put("USER_GID", USER_GID);
						map.put("USER_NAME_CHINESE", USER_NAME_CHINESE);
						map.put("USER_NAME_ENGLISH", USER_NAME_ENGLISH);
						map.put("USER_ACCOUNT", USER_ACCOUNT);
						map.put("USER_CONTACT_PHONE", USER_CONTACT_PHONE[i]);

						list.add(map);
						Log.d("Map", map.toString());
						Message msg = new Message();
						msg.what = 1;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		thread.start();
		// 點擊撥電話
		listV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(about_list.this, "YES", Toast.LENGTH_SHORT)
						.show();
				String phone = USER_CONTACT_PHONE[arg2].toString();
				Log.d("電話", phone);
				Intent intentDial = new Intent("android.intent.action.CALL",
						Uri.parse("tel:" + phone));
				startActivity(intentDial);
			}
		});
	}

}
