package com.example.ce.index;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ce.R;
import com.example.ce.adpter.Announcement_Gson;
import com.google.gson.Gson;

public class Announcement extends Activity {
	private ListView listV;
	private ArrayList<String> list = new ArrayList<String>();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_announcement);
		view();
		setAdapter();
	}

	private void view() {
		listV = (ListView) findViewById(R.id.listView1);
	}

	private void setAdapter() {
		String JsonStr = "{\"test1\":[{\"title\":\"將在2月15日舉辦尾牙\",\"content\":\"content1\"},{\"title\":\"即將有新的標案\",\"content\":\"content2\"},{\"title\":\"董事長女兒在12月10號要出嫁\",\"content\":\"content3\"},{\"title\":\"發薪日\",\"content\":\"content4\"}]}";
		Gson gsondata = new Gson();
		test AG = gsondata.fromJson(JsonStr, test.class);// fromJson{"Json值","Gson解析的檔案"}
		for (int i = 0; i < AG.getALsize(); i++) {
			System.out.println("Title:" + AG.gettest1().get(i).getTitle());
			System.out.println("Content:" + AG.gettest1().get(i).getContent());
			list.add(AG.gettest1().get(i).getTitle());
		}
		Announcement_BaseAdapter base = new Announcement_BaseAdapter(
				Announcement.this, list);
		listV.setAdapter(base);

	}

}
