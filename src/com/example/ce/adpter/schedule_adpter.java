package com.example.ce.adpter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ce.R;

public class schedule_adpter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	CharSequence[] list = null;
	

	ImageView img1; // 圖片
	ImageView img2; // 圖片
	ImageView img3; // 圖片
	TextView context0;// 設計工程
	TextView context1; // 專案工程
	TextView context2;// 檔案管理

	public schedule_adpter(Context context, CharSequence[] list) {
		layoutInflater = LayoutInflater.from(context);
		this.list = list;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return list.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewTag viewTag;

		if (convertView == null) {
			// 取得listItem容器view
			convertView = layoutInflater
					.inflate(R.layout.shcedule_adpter, null);
			// 建構ListItem內容
			viewTag = new ViewTag(
					(TextView) convertView.findViewById(R.id.context1),

					(ImageView) convertView.findViewById(R.id.imag1));

			convertView.setTag(viewTag);

		} else {
			viewTag = (ViewTag) convertView.getTag();

		}
		viewTag.context1.setText(list[position]);

		return convertView;
	}

	class ViewTag {

		ImageView img1; // 圖片

		TextView context1;// 設計工程

		public ViewTag(TextView context1, ImageView img1) {
			this.context1 = context1;

			this.img1 = img1;

		}
	}
}
