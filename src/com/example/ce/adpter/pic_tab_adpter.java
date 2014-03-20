package com.example.ce.adpter;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ce.R;
import com.example.ce.pic.test;
import com.example.ce.savedate.Pic_date;

public class pic_tab_adpter extends BaseAdapter {
	private GridView gridView;
	private final static String url = "http://uploadingit.com/file/lltpirkd9pk3jbuw/raccoon.png";
	private test loadpic;
	private Context mContext;
	private Activity activity1;
	private LayoutInflater layoutInflater;
	public boolean upload_checkbox = false;
	public boolean remove_checkbox = false;
	private Handler mHandler;

	private String[] myImageURL = new String[url.length()];

	// public ArrayList<date> ADT; 宣告列表date
	// ImageView.setVisibility(View.VISIBLE); layout 中顯示
	// ImageView.setVisibility(View.GONE); layout 中隱藏

	public pic_tab_adpter(Context c, Activity activity, GridView gridView1) {
		super();
		this.mContext = c;
		this.activity1 = activity;
		this.gridView = gridView1;
		layoutInflater = LayoutInflater.from(mContext);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myImageURL.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myImageURL[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("HandlerLeak")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// // TODO Auto-generated method stub
		final Pic_date PIC;
		try {
			if (convertView == null) {

				convertView = layoutInflater.inflate(
						R.layout.gridview_pictabhost, null);
				PIC = new Pic_date();
				PIC.imageButton1 = (ImageButton) convertView
						.findViewById(R.id.imageButton1);
				PIC.imageView1 = (ImageView) convertView
						.findViewById(R.id.imageView1);
				PIC.checkBox1 = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				PIC.progressBar1 = (ProgressBar) convertView
						.findViewById(R.id.progressBar1);
				loadpic = new test();

				mHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						switch (msg.what) {
						case 1:
							// String Path = Environment
							// .getExternalStorageDirectory()
							// + "/DCIM/Camera/IMG_20140109_224355.jpg";
							// Log.d("Path ", Path);
							// File mfile = new File(Path);
							// if (mfile.exists()) {
							// Bitmap bitmap = BitmapFactory.decodeFile(Path);
							// PIC.progressBar1.setVisibility(View.GONE);
							// PIC.imageButton1.setImageBitmap(bitmap);
							// }
							PIC.progressBar1.setVisibility(View.GONE);
							PIC.imageButton1.setImageBitmap(loadpic.getImg());
							break;
						}
						super.handleMessage(msg);
					}
				};
				loadpic.handleWebPic(url, mHandler);

			} else {
				PIC = (Pic_date) convertView.getTag();

			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		// checkbox 點擊事件
		// PIC.checkBox1.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
		return convertView;
	}

}
