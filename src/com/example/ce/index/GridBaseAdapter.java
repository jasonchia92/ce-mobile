package com.example.ce.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ce.R;

public class GridBaseAdapter extends BaseAdapter{
	private Context context;
	private String[] items1;
	private String[] items2;
	LayoutInflater inflater;
	public GridBaseAdapter(Context context, String[] items,String[] item) {
        this.context = context;
        this.items1 = items;
        this.items2 = item;
        inflater = LayoutInflater.from(context);
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items2.length;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items2[position];
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		uu a=null;
		if (convertView == null) {
			a=new uu();
            convertView = inflater.inflate(R.layout.griditem, null);
            a.title=(TextView)convertView.findViewById(R.id.textV4);
            a.title.setText(items1[position]);
            a.info=(TextView)convertView.findViewById(R.id.textV5);
            a.info.setText(items2[position]);
            a.img=(ImageView)convertView.findViewById(R.id.imageView1);
            if(items2[position].equals("已完成")){
            	a.img.setImageResource(R.drawable.project_done);
            }else if(items2[position].equals("施工中")){
            	a.img.setImageResource(R.drawable.project_ready);
            }else{
            	a.img.setImageResource(R.drawable.project_constructing);
            }
            convertView.setTag(a);   
        }
		else {                   
            a = (uu)convertView.getTag();
        }         
        return convertView;
	}
	public class uu {
         public TextView title;
         public TextView info;
         public ImageView img;
	}
}
