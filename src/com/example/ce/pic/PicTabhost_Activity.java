package com.example.ce.pic;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import com.example.ce.R;
import com.example.ce.adpter.pic_tab_adpter;

public class PicTabhost_Activity extends FragmentActivity {

	private TabHost tabHost;
	private TabWidget tabWidget;
	private GridView gridView1;
	private GridView gridView2;
	private ImageButton imageButton1;
	private ImageButton imageButton2;
	private ImageButton imageButton3;
	private RelativeLayout relativeLayout1;
	private String task_id;
	private String task_name;
	private boolean take;
	private String path;
	private String Client_tab = "業主";// index=0
	private String Client_tab_tag = "tab1";
	private String Company_tab = "公司";// index=1
	private String Company_tab_tag = "tab2";
	private boolean id_Company = true;// 權限
	private int GET_PHOTO = 1;
	private pic_tab_adpter Client_PTAA1;
	private pic_tab_adpter Company_PTAA1;
	private final int reUI_1 = 1, reUI_2 = 2, reUI_3 = 3, reUI_4 = 4;
	private String[] Client_server_pic = {
			"http://images5.fanpop.com/image/photos/29400000/kakashi_shippuuden_avatar_picture_84787-1-jpg-kakashi-sensei-29426769-128-128.jpg",
			"http://www2.nau.edu/d-elearn/support/tutorials/bblearn/dreamweaver_bblearn/images/dw_icon.jpg",
			"http://bioneural.net/wordpress/wp-content/uploads/2008/02/geotag-128px.jpg" };
	private boolean FIRST = true;
	private Handler handler1 = new Handler() {
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pic_tabhost);

		Intent intent1 = getIntent();
		Bundle bundle1 = intent1.getExtras();
		// task_id = bundle1.getString("task_id", null);
		// task_name = bundle1.getString("task_name", null);

		// if (task_id != null && task_name != null) {

		init();
		setTabHost();
		// Thread_run();

		// }
	}

	private void init() {
		relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
		imageButton3 = (ImageButton) findViewById(R.id.imageButton3);

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);

		View view1 = tabHost.findViewWithTag("FragmentView1");
		gridView1 = (GridView) view1.findViewById(R.id.gridView1);

		View view2 = tabHost.findViewWithTag("FragmentView2");
		gridView2 = (GridView) view2.findViewById(R.id.gridView1);

		Client_PTAA1 = new pic_tab_adpter(this, PicTabhost_Activity.this,
				gridView1);
		Company_PTAA1 = new pic_tab_adpter(this, PicTabhost_Activity.this,
				gridView2);

//		Client_PTAA1.task_id = task_id;
//		Company_PTAA1.task_id = task_id;

		gridView1.setAdapter(Client_PTAA1);

	}

	private void setTabHost() {
		tabHost.setup();

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				System.out.println("tabId:" + tabId);
				if (tabId == Client_tab_tag) {
					// View view1=tabHost.findViewWithTag("FragmentView1");
					// gridView1=(GridView) view1.findViewById(R.id.gridView1);
					// gridView1.setAdapter(Client_PTAA1);

					// tabHost.setCurrentTab( 0 );
				} else if (tabId == Company_tab_tag) {
					// View view2=tabHost.findViewWithTag("FragmentView2");
					// gridView2=(GridView) view2.findViewById(R.id.gridView1);
					// gridView2.setAdapter(Company_PTAA1);

					// if(first_Company)
					// {
					// Thread_run();
					// first_Company=false;
					// }
					// tabHost.setCurrentTab( 1 );
				}
			}
		};

		tabHost.addTab(tabHost.newTabSpec(Client_tab_tag)
				.setIndicator(Client_tab).setContent(R.id.clientFragment1));

		if (id_Company) {
			tabHost.addTab(tabHost.newTabSpec(Company_tab_tag)
					.setIndicator(Company_tab)
					.setContent(R.id.companyFragment1));

		}
		tabHost.setCurrentTab(0);

		tabHost.setOnTabChangedListener(tabChangeListener);

	}

	// / 點擊照相
	public void onClick_Camera(View view1) {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			path = Environment.getExternalStorageDirectory()
					+ "/images/make_machine_example.jpg";
			File file = new File(path);
			Uri output = Uri.fromFile(file);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
			startActivityForResult(intent, GET_PHOTO);
		} else {
			Toast.makeText(PicTabhost_Activity.this, "R.string.sdcarderror",
					Toast.LENGTH_LONG).show();
		}
	}

	// 點選menu
	public void onClick_Camera(MenuItem view) {
		changeButtonCheckboxShow(1);
	}

	public void onClick_Upload(MenuItem view) {
		changeButtonCheckboxShow(2);
	}

	public void onClick_Remove(MenuItem view) {

		changeButtonCheckboxShow(3);
	}

	private void changeButtonCheckboxShow(int i) {
		// for(int j=0;j<Client_PTAA1.ALPD1.size();j++)
		// {
		// Client_PTAA1.ALPD1.get(j).checkBox=false;
		// }
		// for(int j=0;j<Company_PTAA1.ALPD1.size();j++)
		// {
		// Company_PTAA1.ALPD1.get(j).checkBox=false;
		// }
		Client_PTAA1.upload_checkbox = (i == 2 ? true : false);
		Company_PTAA1.upload_checkbox = (i == 2 ? true : false);
		Client_PTAA1.remove_checkbox = (i == 3 ? true : false);
		Company_PTAA1.remove_checkbox = (i == 3 ? true : false);
		Client_PTAA1.notifyDataSetChanged();
		Company_PTAA1.notifyDataSetChanged();
		imageButton1.setVisibility(i == 1 ? ImageButton.VISIBLE
				: ImageButton.GONE);
		imageButton2.setVisibility(i == 2 ? ImageButton.VISIBLE
				: ImageButton.GONE);
		imageButton3.setVisibility(i == 3 ? ImageButton.VISIBLE
				: ImageButton.GONE);
	}

	public void onClick_Logout(MenuItem view) {
		// HttpTools HT1=new HttpTools(PicTabhost_Activity.this);
		// HT1.user_logout(handler1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
