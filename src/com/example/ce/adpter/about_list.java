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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ce.Http_Post;
import com.example.ce.R;

public class about_list extends Activity {
	private ListView listV;
	private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	String Response;
	JSONObject object;
	String USER_CONTACT_PHONE;
	String  USER_NAME_CHINESE,
			USER_NAME_ENGLISH, USER_ACCOUNT, NAME_COVER;
	// layout 使用陣列
	String[] from = { "USER_NAME_CHINESE", "USER_NAME_ENGLISH",
			"USER_ACCOUNT", "USER_CONTACT_PHONE","NAME_COVER" };
	// layout 使用陣列
	int[] to = { 
			 R.id.TextView12, 
			R.id.TextView13, R.id.TextView14,R.id.TextView15,R.id.name };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		listV = (ListView) findViewById(R.id.listView1);
		main();

	}

	private void main() {
		Bundle bundle = this.getIntent().getExtras();

		String id = bundle.getString("id"); // ii == 從 Poject_Tab1取得的專案ID
		final String url = "http://apps.csie.stu.edu.tw:3980/api/project/getProjectManagement/project_id/"
				+ id;

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
					for (int i = 0; i < jsonArray.length(); i++) {
						object = jsonArray.getJSONObject(i);
						// 陣列字串接值
						USER_NAME_CHINESE = object
								.getString("USER_NAME_CHINESE");
						USER_NAME_ENGLISH = object
								.getString("USER_NAME_ENGLISH");
						USER_ACCOUNT = object.getString("USER_ACCOUNT");
						USER_CONTACT_PHONE = object
								.getString("USER_CONTACT_PHONE");

						HashMap<String, String> map = new HashMap<String, String>();
						map.put("USER_NAME_CHINESE", USER_NAME_CHINESE);
						map.put("USER_NAME_ENGLISH", USER_NAME_ENGLISH);
						map.put("USER_ACCOUNT", USER_ACCOUNT);
						map.put("USER_CONTACT_PHONE", USER_CONTACT_PHONE);
						map.put("NAME_COVER", ""+USER_NAME_CHINESE.charAt(0));
						list.add(map);
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
				String phone = USER_CONTACT_PHONE.toString();
				Intent intentDial = new Intent("android.intent.action.CALL",
						Uri.parse("tel:" + phone));
				startActivity(intentDial);
			}
		});
	}

}
