package com.example.ce;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ce.adpter.about_list;
import com.example.ce.adpter.daily_list;
import com.example.ce.adpter.schedule_list;
import com.example.ce.index.Home;

;

public class CE_Poject_about extends ActivityGroup {

	private TextView add;
	private TextView poject;
	private ImageView view;
	private ImageButton button;
	private TabHost mTabHost;
	String Poject_id;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ce_poject_about);
		
		push();
		main();
	}

	public void main() {
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		ImageView img= (ImageView) findViewById(R.id.cover);
		poject.setText(bundle.getString("poject"));
		if(bundle.getString("status").contains("ready")){
			img.setImageResource(R.drawable.project_ready);
		}
		else if(bundle.getString("status").contains("constructing")){
			img.setImageResource(R.drawable.project_constructing);
		}
		else{
			img.setImageResource(R.drawable.project_done);
		}
		add.setText(bundle.getString("add"));

	}

	public void get() {

	}

	public void push() {

		poject = (TextView) findViewById(R.id.poject);
		add = (TextView) findViewById(R.id.city);
		view = (ImageView) findViewById(R.id.imageView1);
		button = (ImageButton) findViewById(R.id.imageButton1);

		// 跳頁傳值將Porject_id傳到 about_list的分頁中
		Intent intent1 = new Intent(CE_Poject_about.this, about_list.class);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		Poject_id = bundle.getString("id");

		Bundle bundle2 = new Bundle();
		bundle2.putString("id", Poject_id);

		intent1.putExtras(bundle2);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabHost.setup(this.getLocalActivityManager());

		mTabHost.addTab(mTabHost.newTabSpec(0 + "").setIndicator("關於")
				.setContent(intent1));
		// 測試中 傳值到日報中
		Intent daily = new Intent(this, daily_list.class);
		Intent intent2 = this.getIntent();
		Bundle test1 = intent2.getExtras();
		Poject_id = test1.getString("id");
		Bundle test2 = new Bundle();
		test2.putString("id", Poject_id);

		daily.putExtras(test2);

		mTabHost.addTab(mTabHost
				.newTabSpec(2 + "")
				.setIndicator("日報")
				.setContent(
						new Intent(daily)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		// 傳值到進度
		Intent schedule = new Intent(this, schedule_list.class);
		Intent intent3 = this.getIntent();
		Bundle test = intent3.getExtras();
		Poject_id = test.getString("id");

		Bundle id = new Bundle();
		id.putString("id", Poject_id);
		schedule.putExtras(id);

		mTabHost.addTab(mTabHost
				.newTabSpec(3 + "")
				.setIndicator("進度")
				.setContent(
						new Intent(schedule)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
		mTabHost.setCurrentTab(0);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ce, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
		case R.id.home:
			intent.setClass(this, Home.class);
			startActivity(intent);
			break;
		case R.id.logout:
			AlertDialog.Builder builder = new Builder(this);
			builder.setMessage("確定登出")
			.setTitle("提醒")
			.setPositiveButton("取消",
					new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();

				}
			})
			.setNegativeButton("確認",
					new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();

				}
			}).show();
			break;
		default:
			break;
		}

		return true;
	}

}
