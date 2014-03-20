package com.example.ce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class F3 extends Fragment {

	private ListView listView;
	JSONObject object;
	String url = "http://apps.csie.stu.edu.tw:3980/api/file/getFileList/type/all";// 檔案管理
	String Response;
	ArrayList<HashMap<String, String>> levelList = new ArrayList<HashMap<String, String>>();
	String[] from = { "FILE_ID", "FILE_NAME", "FILE_TYPE", "FILE_SIZE",
			"UPLOAD_TIME", "REAL_NAME", "PARENT", "PROJECT_ID" };
	Notification notification = new Notification();// 通知欄的方法 test
	NotificationManager manager;// 通知欄監控
	String FILE_ID[], PARENT[], PROJECT_ID[];
	String REAL_NAME[];
	String UPLOAD_TIME; // 上傳時間
	String FILE_SIZE; // 檔案尺寸
	String FILE_TYPE; // 檔案類型
	String FILE_NAME; // 檔案名稱

	int[] to = { R.id.textView1, R.id.textView2, R.id.textView7,
			R.id.textView8, R.id.textView9, R.id.textView10, R.id.textView11,
			R.id.textView12 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.f3, container, false);
		listView = (ListView) v.findViewById(R.id.listView2);

		Thread thread = new Thread() {
			Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 1) {
						SimpleAdapter adapter = new SimpleAdapter(getActivity()
								.getBaseContext(), levelList,
								R.layout.filesystem, from, to);
						adapter.notifyDataSetChanged();
						listView.setAdapter(adapter);

					}
				}

			};

			public void run() {
				try {
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					nameValuePairs.add(new BasicNameValuePair("email", ""));
					Http_Post response = new Http_Post();
					Response = response.Response(url, nameValuePairs);
					JSONObject jsonResponse;
					jsonResponse = new JSONObject(Response);
					JSONArray jsonArray = jsonResponse.getJSONArray("data");

					levelList.clear();
					FILE_ID = new String[jsonArray.length()];
					REAL_NAME = new String[jsonArray.length()]; // 檔案陣列接收
					PARENT = new String[jsonArray.length()];
					PROJECT_ID = new String[jsonArray.length()];

					for (int i = 0; i < jsonArray.length(); i++) {
						object = jsonArray.getJSONObject(i);
						Log.d("FILE_ID", object.getString("FILE_ID"));
						Log.d("REAL_NAME", object.getString("REAL_NAME"));
						Log.d("PARENT", object.getString("PARENT"));
						Log.d("PROJECT_ID", object.getString("PROJECT_ID"));
						Log.d("UPLOAD_TIME", object.getString("UPLOAD_TIME"));
						Log.d("FILE_SIZE", object.getString("FILE_SIZE"));
						Log.d("FILE_TYPE", object.getString("FILE_TYPE"));
						Log.d("FILE_NAME", object.getString("FILE_NAME"));
						// // Log 顯示 Date
						FILE_ID[i] = object.getString("FILE_ID");
						REAL_NAME[i] = object.getString("REAL_NAME");
						PARENT[i] = object.getString("PARENT");
						PROJECT_ID[i] = object.getString("PROJECT_ID");
						UPLOAD_TIME = object.getString("UPLOAD_TIME");
						FILE_SIZE = object.getString("FILE_SIZE");
						FILE_TYPE = object.getString("FILE_TYPE");
						FILE_NAME = object.getString("FILE_NAME");
						// 陣列 字串 接值 Json
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("FILE_ID", FILE_ID[i]);
						map.put("FILE_TYPE", FILE_TYPE);
						map.put("REAL_NAME", REAL_NAME[i]);
						map.put("PARENT", PARENT[i]);
						map.put("PROJECT_ID", PROJECT_ID[i]);
						map.put("UPLOAD_TIME", UPLOAD_TIME);
						map.put("FILE_SIZE", FILE_SIZE);
						map.put("FILE_NAME", FILE_NAME);
						levelList.add(map);
						// add levelist
						Message msg = new Message();
						msg.what = 1;
						handler.sendMessage(msg);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		thread.start();

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				// do things with the clicked item

				Thread thread = new Thread() {
					public void run() {
						try {
							HttpDownloader httpDownloader = new HttpDownloader();
							// 调用httpDownloader对象的重载方法download下载mp3文件
							int result = httpDownloader.downfile(
									"http://apps.csie.stu.edu.tw:3980/api/file/download/file_id/"
											+ FILE_ID[position], "CE/",
									REAL_NAME[position]);
							if (result != 0) {
								Log.d("下載中", "....");
								Notification();// 通知訊息窗

								// 下載參數 文件URL + 指定資料夾 + 文件名稱
								Log.d("文件", REAL_NAME[position]);
								// 回傳文件的名稱
								Log.d("下載路徑",
										"http://apps.csie.stu.edu.tw:3980/api/file/download/file_id/"
												+ FILE_ID[position]);

								System.out.println(result);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				};
				thread.start();
			}
		});
		return v;
	}

	// / 通知視窗
	public void Notification() {
		manager = (NotificationManager) getActivity().getSystemService(
				Context.NOTIFICATION_SERVICE);

		final NotificationCompat.Builder builder = new NotificationCompat.Builder(
				getActivity());
		builder.setSmallIcon(R.drawable.downloads);
		builder.setContentTitle("下載");
		builder.setContentText("dowload");
		builder.setTicker("資料下載中");
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				int id = 1;
				// 完成一个操作20次
				for (incr = 0; incr <= 100; incr += 5) {
					// 给进度条设置最大值, 当前的完成度，以及“确定”状态
					builder.setProgress(100, incr, false);
					// 第一次就显示进度条
					manager.notify(0, builder.build());
					// 睡眠这条线程，模拟耗时操作
					try {
						// 睡5秒
						Thread.sleep(5 * 100);
					} catch (InterruptedException e) {
						Log.d("notify", "sleep failure");
					}
				}
				// 当循环完成的时候更新这个进度条
				builder.setContentText("下載 完成")
				// 删除进度条
						.setProgress(0, 0, false);
				manager.notify(0, builder.build());
				// 循環失敗的時候下面的放法
				// builder.setProgress(0, 0, true);
				// manager.notify(0, builder.build());

			}
		}
		// 开始这个线程
		).start();
	}
}
