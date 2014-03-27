package com.example.ce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ce.adpter.SP_data;
import com.example.ce.pic.PicTabhost_Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Poject_Tab3 extends Fragment {
	private ListView listView;
	SP_data session;
	ArrayList<HashMap<String, String>> levelList = new ArrayList<HashMap<String, String>>();
	String name[], address[], status[], id1[];
	JSONObject object;
	String[] from = { "PROJECT_ID", "NAME", "ADDRESS" };
	int[] to = { R.id.textView1, R.id.textView2, R.id.textView3 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.f2, container, false);
		session = new SP_data(this.getActivity().getApplicationContext()); 
		listView = (ListView) v.findViewById(R.id.listView2);
		
		JSONArray obj;
		HashMap<String, String> user = session.getUserDetails();
		String data2 = user.get(SP_data.KEY_DATA);
		levelList.clear();
		try {
			obj = new JSONArray(data2);
			
			id1 = new String[obj.length()];
			name = new String[obj.length()];
			address = new String[obj.length()];
			status = new String[obj.length()];
			
			for (int i = 0; i < obj.length(); i++) {
				object =  obj.getJSONObject(i);
				status[i] = object.getString("STATUS").toString();
				if(status[i].contains("2")){
					id1[i] = object.getString("PROJECT_ID").toString();
					name[i] = object.getString("NAME").toString();
					address[i] = object.getString("ADDRESS").toString();
					
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("PROJECT_ID", id1[i]);
					map.put("NAME", name[i]);
					map.put("ADDRESS", address[i]);
					levelList.add(map);
				}
				else{
					
				}
			}
			SimpleAdapter adapter = new SimpleAdapter(getActivity()
					.getBaseContext(), levelList, R.layout.newwork2,
					from, to);
			
			adapter.notifyDataSetChanged();
			listView.setAdapter(adapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// ÂIÀ»¨Æ¥ó
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
				bundle.putString("status", "done");
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
		return v;
	}
}
