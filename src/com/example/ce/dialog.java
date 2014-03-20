package com.example.ce;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class dialog extends Application{

	public static void newWorkItem(final Context context1,
			final Activity activity1, final Handler handler,
			final int project_id) {

		int[] now_ymd = setNowDate();
		String StartTime_str = now_ymd[0] + "-" + now_ymd[1] + "-" + now_ymd[2];
		String EndTime_str = now_ymd[0] + "-" + now_ymd[1] + "-" + now_ymd[2];

		View view = View.inflate(context1, R.layout.dialog, null);
		final EditText TitleName = (EditText) view.findViewById(R.id.editText1);
		final EditText Content = (EditText) view.findViewById(R.id.editText2);
		final EditText StartTime = (EditText) view.findViewById(R.id.editText3);
		final EditText EndTime = (EditText) view.findViewById(R.id.editText4);
		ImageButton cancelIB = (ImageButton) view
				.findViewById(R.id.imageButton1);
		ImageButton newIB = (ImageButton) view.findViewById(R.id.imageButton2);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context1);
		// alertDialog.setTitle(context1.getResources().getString(R.string.wadn_alerttitle));
		alertDialog.setView(view);

		final AlertDialog alert = alertDialog.create();
		alert.show(); // 取代原本的 alertDialog.show(); 必須在 button 存取之前呼叫

		StartTime.setText(StartTime_str);
		StartTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				select_date(context1, StartTime);
			}
		});

		EndTime.setText(EndTime_str);
		EndTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				select_date(context1, EndTime);
			}
		});

		cancelIB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert.dismiss();
			}
		});

		newIB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String start_time = StartTime.getText().toString();
				String end_time = EndTime.getText().toString();
				if (java.sql.Date.valueOf(start_time).after(
						java.sql.Date.valueOf(end_time))) {
					Toast.makeText(context1, "開始日期不能大於結束結束", Toast.LENGTH_SHORT)
							.show();
				} else {
					Thread TH1 = new Thread() {
						public void run() {

							ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("action",
									"task_add"));
							params.add(new BasicNameValuePair("task_name",
									TitleName.getText().toString()));
							params.add(new BasicNameValuePair("task_des",
									Content.getText().toString()));
							params.add(new BasicNameValuePair("start_date",
									StartTime.getText().toString()));
							params.add(new BasicNameValuePair("end_date",
									EndTime.getText().toString()));
							params.add(new BasicNameValuePair("project_id", ""
									+ project_id));

						}
					};
					TH1.start();
				}

			}
		});
	}

	private static void select_date(Context context1, final EditText setDate) {
		String[] date = setDate.getText().toString().split("-");
		final int[] seledate = new int[3];

		if (date.length != 3) {
			System.out.println("ERROR: 時間格式錯誤");
			return;
		}
		View view = View.inflate(context1, R.layout.datepicker, null);
		DatePicker datepicker = (DatePicker) view
				.findViewById(R.id.datePicker1);

		ImageButton cancel_IB = (ImageButton) view
				.findViewById(R.id.imageButton1);
		ImageButton save_IB = (ImageButton) view
				.findViewById(R.id.imageButton2);

		System.out.println("select_date:" + date[0] + "-" + date[1] + "-"
				+ date[2]);
		datepicker.init(Integer.valueOf(date[0]), Integer.valueOf(date[1]) - 1,
				Integer.valueOf(date[2]), new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int month, int day) {
						seledate[0] = year;
						seledate[1] = month + 1;
						seledate[2] = day;
						System.out.println("month:" + month);
					}
				});

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context1);
		// alertDialog.setTitle("設定日期");
		alertDialog.setView(view);

		final AlertDialog alert = alertDialog.create();
		alert.show(); // 取代原本的 alertDialog.show(); 必須在 button 存取之前呼叫

		cancel_IB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert.dismiss();
			}
		});

		save_IB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDate.setText(seledate[0] + "-" + seledate[1] + "-"
						+ seledate[2]);
				alert.dismiss();
			}
		});
	}

	private static int[] setNowDate() {

		int[] now_ymd = new int[3];
		Calendar calendar = Calendar.getInstance();
		now_ymd[0] = calendar.get(Calendar.YEAR);
		now_ymd[1] = calendar.get(Calendar.MONTH) + 1;
		now_ymd[2] = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("setNowDate:" + now_ymd[0] + "-" + now_ymd[1] + "-"
				+ now_ymd[2]);
		return now_ymd;
	}
}
