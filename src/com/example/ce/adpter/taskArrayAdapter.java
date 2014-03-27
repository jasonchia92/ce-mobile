package com.example.ce.adpter;

import com.example.ce.R;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class taskArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	private final String[] name;
	private final String[] address;

	public taskArrayAdapter(Context context, String[] values, String[] name,String[] address) {
		super(context, R.layout.newwork, values);
		this.context = context;
		this.values = values;
		this.name = name;
		this.address = address;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.newwork, parent, false);
		
		TextView id2 = (TextView) rowView.findViewById(R.id.id);
		TextView name2 = (TextView) rowView.findViewById(R.id.name);
		TextView address2 = (TextView) rowView.findViewById(R.id.address);
		
		id2.setText(values[position]);
		name2.setText(name[position]);
		address2.setText(address[position]);


		return rowView;
	}

} 