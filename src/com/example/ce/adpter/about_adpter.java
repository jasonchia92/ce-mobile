package com.example.ce.adpter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ce.R;

public class about_adpter extends BaseAdapter {
	private Context context;
	private ArrayList<String> list2;
	LayoutInflater inflater;

	public about_adpter(Context context, ArrayList<String> list2) {
		this.context = context;
		this.list2 = list2;
		inflater = LayoutInflater.from(context);
		// inflater = (LayoutInflater)
		// this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//þý°î«²þý”XMLï¿½ï‚¥iew
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list2.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		uu a = null;
		if (convertView == null) {
			a = new uu();
			convertView = inflater.inflate(R.layout.about_adpter, null);
			a.title = (TextView) convertView.findViewById(R.id.textView1);
		//	a.phone=(TextView)convertView.findViewById(R.id.textView2);
			a.title.setText(list2.get(position));
		//	a.phone.setText(list2.get(position));
			convertView.setTag(a);
		} else {
			a = (uu) convertView.getTag();
		}
		return convertView;
	}

	public class uu {
		public TextView title;
		//public TextView phone;

	}

}