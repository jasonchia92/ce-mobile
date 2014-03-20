package com.example.ce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ce.pic.PicTabhost_Activity;
import com.google.gson.JsonObject;

public class F1 extends Fragment {

	private ListView listView;
	ArrayList<HashMap<String, String>> levelList = new ArrayList<HashMap<String, String>>();
	String Response;
	int[] to = { R.id.textView1, R.id.textView2, R.id.textView3,
			R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7,
			R.id.textView8 };
	String[] from = { "FILE_ID", "FILE_NAME", "FILE_TYPE", "FILE_SIZE",
			"UPLOAD_TIME", "REAL_NAME", "PARENT", "PROJECT_ID" };
	String value;
	String[] FILE_ID;
	String FILE_NAME;
	String FILE_TYPE;
	String FILE_SIZE;
	String UPLOAD_TIME;
	String REAL_NAME;
	String PARENT;
	String PROJECT_ID;
	JSONObject object;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		CE mainActivity = (CE) activity;
		value = mainActivity.data();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.f1, container, false);
		listView = (ListView) v.findViewById(R.id.listView1);

		Thread thread = new Thread() {

			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 1) {
						SimpleAdapter adapter = new SimpleAdapter(getActivity()
								.getBaseContext(), levelList, R.layout.design,
								from, to);
						adapter.notifyDataSetChanged();
						listView.setAdapter(adapter);

					}
				}

			};

			public void run() {
				try {
					String url = "http://apps.csie.stu.edu.tw:3980/api/file/getFileList/type/design/project_id/"
							+ value;// 檔案管理
					Log.d("F1_url", url);
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					Http_Post response = new Http_Post();
					Response = response.Response(url, nameValuePairs);
					JSONObject jsonResponse;
					jsonResponse = new JSONObject(Response);
					JSONArray jsonArray = jsonResponse.getJSONArray("data");
					levelList.clear();
					FILE_ID = new String[jsonArray.length()];

					for (int i = 0; i < jsonArray.length(); i++) {
						object = jsonArray.getJSONObject(i);
						Log.d("FILE_ID", object.getString("FILE_ID"));
						Log.d("REAL_NAME", object.getString("REAL_NAME"));
						Log.d("PARENT", object.getString("PARENT"));
						Log.d("PROJECT_ID", object.getString("PROJECT_ID"));
						Log.d("UPLOAD_TIME", object.getString("UPLOAD_TIME"));
						Log.d("FILE_SIZE", object.getString("FILE_SIZE"));
						Log.d("FILE_TYPE", object.getString("FILE_TYPE"));
						Log.d("FILE_NAME", object.getString("FILE_NAME"));
						// // Log 顯示 Date
						FILE_ID[i] = object.getString("FILE_ID");
						REAL_NAME = object.getString("REAL_NAME");
						PARENT = object.getString("PARENT");
						PROJECT_ID = object.getString("PROJECT_ID");
						UPLOAD_TIME = object.getString("UPLOAD_TIME");
						FILE_SIZE = object.getString("FILE_SIZE");
						FILE_TYPE = object.getString("FILE_TYPE");
						FILE_NAME = object.getString("FILE_NAME");
						// 陣列 字串 接值 Json
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("FILE_ID", FILE_ID[i]);
						map.put("FILE_TYPE", FILE_TYPE);
						map.put("REAL_NAME", REAL_NAME);
						map.put("PARENT", PARENT);
						map.put("PROJECT_ID", PROJECT_ID);
						map.put("UPLOAD_TIME", UPLOAD_TIME);
						map.put("FILE_SIZE", FILE_SIZE);
						map.put("FILE_NAME", FILE_NAME);
						levelList.add(map);
						// add levelist
						Message msg = new Message();
						msg.what = 1;
						handler.sendMessage(msg);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		thread.start();
		// 點擊事件
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// do things with the clicked item

				Intent intent = new Intent();
				intent.setClass(getActivity(), PicTabhost_Activity.class);
				startActivity(intent);
			}
		});
		return v;
	}

}
