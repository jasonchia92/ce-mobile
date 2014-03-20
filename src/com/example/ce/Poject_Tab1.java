package com.example.ce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
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
import android.widget.Toast;

public class Poject_Tab1 extends Fragment {
	private ListView listView;
	String url = "http://apps.csie.stu.edu.tw:3980/api/project/getProjectList/status/0";
	ArrayList<HashMap<String, String>> levelList = new ArrayList<HashMap<String, String>>();
	String Response;
	String name[], address[], status[], id1[];
	JSONObject object;
	Context context;
	String[] from = { "PROJECT_ID", "NAME", "ADDRESS", "STATUS" };
	int[] to = { R.id.textView1, R.id.textView2, R.id.textView3, };
	AlertDialogManager alert = new AlertDialogManager();
	Message msg = new Message();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.f1, container, false);
		listView = (ListView) v.findViewById(R.id.listView1);
		Thread thread = new Thread() {
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 0:
						alert.showAlertDialog(getActivity(), "Warring",
								"�s�u���~ �нT�{�z������");
						break;
					case 1:

						SimpleAdapter adapter = new SimpleAdapter(getActivity()
								.getBaseContext(), levelList, R.layout.newwork,
								from, to);
						adapter.notifyDataSetChanged();
						listView.setAdapter(adapter);
						break;

					default:
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
					// �P�_�O�_��������JSON
					if (Response == null) {
						msg.what = 0;
					} else {
						JSONObject jsonResponse;
						jsonResponse = new JSONObject(Response);
						JSONArray jsonArray = jsonResponse.getJSONArray("data");

						levelList.clear();
						id1 = new String[jsonArray.length()];
						name = new String[jsonArray.length()];
						address = new String[jsonArray.length()];
						status = new String[jsonArray.length()];
						for (int i = 0; i < jsonArray.length(); i++) {
							object = jsonArray.getJSONObject(i);
							Log.d("PROJECT", object.getString("PROJECT_ID"));
							Log.d("NAME", object.getString("NAME"));
							Log.d("ADDRESS", object.getString("ADDRESS"));
							Log.d("STATUS", object.getString("STATUS"));
							// id = object.getString("PROJECT_ID");
							// name = object.getString("NAME");
							// address = object.getString("ADDRESS");
							// status = object.getString("STATUS");
							id1[i] = object.getString("PROJECT_ID");
							name[i] = object.getString("NAME");
							address[i] = object.getString("ADDRESS");
							status[i] = object.getString("STATUS");
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("PROJECT_ID", id1[i]);
							map.put("NAME", name[i]);
							map.put("ADDRESS", address[i]);
							map.put("STATUS", status[i]);

							levelList.add(map);

							msg.what = 1;
						}
						handler.sendMessage(msg);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		thread.start();

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// do things with the clicked item
				Toast.makeText(getActivity(), name[position],
						Toast.LENGTH_SHORT).show();
				Bundle bundle = new Bundle();
				Intent intent = new Intent();
				intent.setClass(getActivity(), CE_Poject_about.class);
				bundle.putString("poject", name[position]);
				bundle.putString("add", address[position]);
				bundle.putString("id", id1[position]);
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
		return v;
	}

}
