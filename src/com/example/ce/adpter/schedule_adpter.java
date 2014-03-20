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
	

	ImageView img1; // �Ϥ�
	ImageView img2; // �Ϥ�
	ImageView img3; // �Ϥ�
	TextView context0;// �]�p�u�{
	TextView context1; // �M�פu�{
	TextView context2;// �ɮ׺޲z

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
			// ���olistItem�e��view
			convertView = layoutInflater
					.inflate(R.layout.shcedule_adpter, null);
			// �غcListItem���e
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

		ImageView img1; // �Ϥ�

		TextView context1;// �]�p�u�{

		public ViewTag(TextView context1, ImageView img1) {
			this.context1 = context1;

			this.img1 = img1;

		}
	}
}
