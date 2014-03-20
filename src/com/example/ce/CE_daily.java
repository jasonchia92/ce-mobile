package com.example.ce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.ce.index.Home;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CE_daily extends Activity implements OnCheckedChangeListener {
	private RadioButton rb1, rb2, rb3, rb4;
	ProgressDialog progressDialog;
	private Context context;
	private List<String> list;
	private EditText text;
	private EditText title;
	private EditText Foreman;
	private EditText Number;
	private EditText Remark;
	private Button Save;
	private Button ii;
	private Button button2;// 取消返回建
	String Response;
	String url = "http://apps.csie.stu.edu.tw:3980/api/project/saveDairy";
	Message msg = new Message();
	String Project_id;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily);
		Bundle bundle = this.getIntent().getExtras();
		Log.d("123", bundle.toString());
		Project_id = bundle.getString("id");
		Log.d("ii", Project_id);
		intit();
		main();
		Onclick();

	}

	public void actionbar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Title");// 主標題
		actionBar.setSubtitle("SubTitle");// 副標題
		actionBar.setIcon(getResources().getDrawable(R.drawable.ic_launcher));// API14才有
																				// Android4.0

	}

	public void intit() {
		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		rb3 = (RadioButton) findViewById(R.id.radioButton3);
		rb4 = (RadioButton) findViewById(R.id.radioButton4);
		text = (EditText) findViewById(R.id.text);
		Foreman = (EditText) findViewById(R.id.Foreman);
		Number = (EditText) findViewById(R.id.Number);
		Remark = (EditText) findViewById(R.id.Remark);
		title = (EditText) findViewById(R.id.title);
		Save = (Button) findViewById(R.id.save);
		ii = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		// spinner = (Spinner) findViewById(R.id.spinner1);
		rb1.setOnCheckedChangeListener(this);
		rb2.setOnCheckedChangeListener(this);
		rb3.setOnCheckedChangeListener(this);
		rb4.setOnCheckedChangeListener(this);
		Foreman.getText().toString();// 工頭 尚未確認填寫名稱還是人數
		Number.getText().toString();// 人數
		Remark.getText().toString();// 施工備註
		title.getText().toString();// 日報名稱

	}

	private void Onclick() {
		Save.setOnClickListener(save);

	}

	private Button.OnClickListener save = new Button.OnClickListener() {

		@Override
		public void onClick(View v) {
			try {
				Post_data test = new Post_data();
				test.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	};

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

	public void main() {
		ii.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openDialog();
			}
		});

	}

	private void openDialog() {

		final String[] days = {
				"收納空間是一個家不能缺少的部分，但輕裝修裡的收納空間該如何處理？ 建議「化整為零」與「隱藏式收納」兩個要訣。「化整為零」就是將必須收納的物件集中規劃，最好是量身規劃的收納機能設計，被設定後的收納空間用得比較久；「隱藏式收納」指的是將收納機能隱藏在空間之中，不影響生活品質。另外，要巧妙地利用傢飾品與收納機能的結合，讓物件在空間的存在性具備實用與美觀雙重功能。",
				"畸零空間是最好利用作為收納規劃的地方，以木作包覆大樑延伸而成的展示櫃讓空間利用更為精準，而連結不同的空間櫥櫃使用，像玄關櫃與電視櫃的合併，則充份發揮空間的使用坪效",
				"最神的就是木作櫃,除了高櫃,矮櫃與吊櫃之外(反正就是沒上就較矮櫃,沒下就叫吊櫃,有上有下就較高櫃),也有層板與層架之別(有上下左右的叫層架,沒有左右的叫層板),也有有抽屜與沒抽屜之分,層板也有固定與活動之分,也有有門與沒門之分,門後面還分有鏡子跟沒有鏡子,最後就是會不會動的五金,包含開關門的百葉,推拉門的滑軌,可以上",
				"所以當您在估價單的材質與工法項目內如果寫明包含哪一個品牌的五金時,這時就可以做為「呈堂證供」!有呈堂證供的好處是事前價格合理,事後有保障!",
				"客廳及起居空間裡的活動，不管是聚會談話、或是視聽音響聆聽觀賞，都是比較需要空間感的，而收納物品的規劃也以明亮開朗為宜。最好利用簡單的線條或櫥櫃設計，還要能符合一些收藏品展示的需求、燈光的投射，或是採用開放式的層架營造開放的感覺，在色系上的選用也要能夠讓空間有延伸開闊的效果。 ",
				"STEP 1了解各屋況輕裝修裝修要訣→STEP 2掌握輕裝修施工重點→STEP 3聰明規劃預算→STEP 4不可忽略的基礎工程→STEP 5不大興土木達到裝修效果→STEP 6營造到位的居家風格 減少硬體工程，輕裝修、重佈置 降低施作複雜的硬體工程比例",
				"【裝潢真簡單‧書系特色】 提供預算有限，或是不喜歡過度裝潢的讀者，運用「輕裝修、重佈置」的概念，以清楚易懂的步驟、圖表拆解歸納裝潢過程大小事，簡單易學，照著做就能改造出機能與美感兼具的幸福家" };

		AlertDialog.Builder myDialog = new AlertDialog.Builder(CE_daily.this);
		myDialog.setTitle("施工重點");

		myDialog.setItems(days, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					text.setText("收納空間是一個家不能缺少的部分，但輕裝修裡的收納空間該如何處理？ 建議「化整為零」與「隱藏式收納」兩個要訣。「化整為零」就是將必須收納的物件集中規劃，最好是量身規劃的收納機能設計，被設定後的收納空間用得比較久；「隱藏式收納」指的是將收納機能隱藏在空間之中，不影響生活品質。另外，要巧妙地利用傢飾品與收納機能的結合，讓物件在空間的存在性具備實用與美觀雙重功能。");
					text.getText().toString();
					break;
				case 1:
					text.setText("畸零空間是最好利用作為收納規劃的地方，以木作包覆大樑延伸而成的展示櫃讓空間利用更為精準，而連結不同的空間櫥櫃使用，像玄關櫃與電視櫃的合併，則充份發揮空間的使用坪效");
					text.getText().toString();
					break;
				case 2:
					text.setText("最神的就是木作櫃,除了高櫃,矮櫃與吊櫃之外(反正就是沒上就較矮櫃,沒下就叫吊櫃,有上有下就較高櫃),也有層板與層架之別(有上下左右的叫層架,沒有左右的叫層板),也有有抽屜與沒抽屜之分,層板也有固定與活動之分,也有有門與沒門之分,門後面還分有鏡子跟沒有鏡子,最後就是會不會動的五金,包含開關門的百葉,推拉門的滑軌,可以上");
					text.getText().toString();
					break;
				case 3:
					text.setText("所以當您在估價單的材質與工法項目內如果寫明包含哪一個品牌的五金時,這時就可以做為「呈堂證供」!有呈堂證供的好處是事前價格合理,事後有保障!");
					text.getText().toString();
					break;
				case 4:
					text.setText("客廳及起居空間裡的活動，不管是聚會談話、或是視聽音響聆聽觀賞，都是比較需要空間感的，而收納物品的規劃也以明亮開朗為宜。最好利用簡單的線條或櫥櫃設計，還要能符合一些收藏品展示的需求、燈光的投射，或是採用開放式的層架營造開放的感覺，在色系上的選用也要能夠讓空間有延伸開闊的效果。 ");
					text.getText().toString();
					break;
				case 5:
					text.setText("STEP 1了解各屋況輕裝修裝修要訣→STEP 2掌握輕裝修施工重點→STEP 3聰明規劃預算→STEP 4不可忽略的基礎工程→STEP 5不大興土木達到裝修效果→STEP 6營造到位的居家風格 減少硬體工程，輕裝修、重佈置 降低施作複雜的硬體工程比例 ");
					text.getText().toString();
					break;
				case 6:
					text.setText("【裝潢真簡單‧書系特色】 提供預算有限，或是不喜歡過度裝潢的讀者，運用「輕裝修、重佈置」的概念，以清楚易懂的步驟、圖表拆解歸納裝潢過程大小事，簡單易學，照著做就能改造出機能與美感兼具的幸福家");
					text.getText().toString();// 施工重點
				default:
					break;
				}

			}
		});

		myDialog.setNegativeButton("Cancel", null);

		myDialog.show();
	}

	// / 控制選取Radiobutton
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.radioButton1:
			if (isChecked == true) {
				rb1.setChecked(isChecked);
				rb2.setChecked(!isChecked);
				rb3.setChecked(!isChecked);
				rb4.setChecked(!isChecked);
				isChecked = false;
			}
			break;
		case R.id.radioButton2:
			if (isChecked == true) {
				rb1.setChecked(!isChecked);
				rb2.setChecked(isChecked);
				rb3.setChecked(!isChecked);
				rb4.setChecked(!isChecked);
				isChecked = false;
			}
			break;
		case R.id.radioButton3:
			if (isChecked == true) {
				rb1.setChecked(!isChecked);
				rb2.setChecked(!isChecked);
				rb3.setChecked(isChecked);
				rb4.setChecked(!isChecked);
				isChecked = false;
			}
			break;
		case R.id.radioButton4:
			if (isChecked == true) {
				rb1.setChecked(!isChecked);
				rb2.setChecked(!isChecked);
				rb3.setChecked(!isChecked);
				rb4.setChecked(isChecked);
				isChecked = false;
			}
			break;

		default:
			break;
		}
	}

	// 取出List<NameValuePair> params

	public class Post_data extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			URL URL;
			try {

				final ArrayList<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("project_id", Project_id));
				params1.add(new BasicNameValuePair("filling_name", title
						.getText().toString()));
				params1.add(new BasicNameValuePair("name", Foreman.getText()
						.toString()));
				params1.add(new BasicNameValuePair("project_number", Number
						.getText().toString()));
				params1.add(new BasicNameValuePair("work_plan", text.getText()
						.toString()));
				params1.add(new BasicNameValuePair("failure_improve", Remark
						.getText().toString()));
				URL = new URL(url);

				HttpURLConnection connection = (HttpURLConnection) URL
						.openConnection();
				connection.setRequestMethod("POST");// 設置以POST方式傳輸
				connection.setRequestProperty("Charset", "UTF-8");// 設置文字類型
				connection.setRequestProperty("Connection", "Keep-Alive"); // 設置維持長時間連接
				connection.setUseCaches(false);// Post請求不能使用緩存
				connection.setDoInput(true);
				connection.setDoOutput(true);
				// Send request
				OutputStream os = connection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, "UTF-8"));
				writer.write(getQuery(params1));// 向對像輸出流，寫出數據，這些數據將存到內存緩衝區中
				writer.flush();// 刷新對像輸出流
				writer.close();// 關閉流對象
				// Get Response
				InputStream is = connection.getInputStream();// 實際發送請求的代碼
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				String line;

				if ((line = rd.readLine()) != null) {
					System.out.println("回傳值------>" + line);
					progressDialog.dismiss();
				}
				rd.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

			return null;

		}

		private String getQuery(List<NameValuePair> params1)
				throws UnsupportedEncodingException {
			StringBuilder result = new StringBuilder();
			boolean data = true;
			for (NameValuePair pair : params1) {
				if (data)
					data = false;
				else
					result.append("&");
				result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
			}
			System.out.println("Result------>" + result.toString());
			Log.d("result", result.toString());
			return result.toString();
		}

		@SuppressWarnings("static-access")
		protected void onPreExecute() {
			super.onPreExecute();

			progressDialog = new ProgressDialog(CE_daily.this);
			progressDialog.setTitle("提醒");
			progressDialog.setMessage("資料上傳中...");
			progressDialog.setCancelable(true);
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (progressDialog.isShowing())
				progressDialog.dismiss();
			// 背景工作處理完"後"需作的事
		}
	}

}
