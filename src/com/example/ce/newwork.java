//package com.example.ce;
//
//import java.util.Calendar;
//
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.app.DatePickerDialog.OnDateSetListener;
//import android.app.Dialog;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//public class newwork extends Activity {
//	protected static final int ID_DATEPICKER = 0;
//	private EditText Title;// 標題
//	private EditText context;// 內容
//	private EditText star; // 開始時間
//	private EditText end; // 結束時間
//	private ImageButton cancel;// 取消
//	private ImageButton Enter; // 確定
//	private int month, day, year;
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.newwork);
//		edit();
//		imagebutton();
//		date();
//
//	}
//
//	public void edit() {
//		Title = (EditText) findViewById(R.id.title);
//		context = (EditText) findViewById(R.id.context);
//		Title.getText().toString();
//		context.getText().toString();
//		star = (EditText) findViewById(R.id.star);
//		end = (EditText) findViewById(R.id.end);
//		star.setOnClickListener(starbtuuon);
//
//	}
//
//	public void imagebutton() {
//
//		cancel = (ImageButton) findViewById(R.id.cancel);
//		Enter = (ImageButton) findViewById(R.id.Enter);
//
//	}
//
//	private void date() {
//		star.setText(new StringBuilder().append(month).append(day).append(year));
//	}
//
//	private OnClickListener starbtuuon = new OnClickListener() {
//
//		@SuppressWarnings("deprecation")
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			final Calendar c = Calendar.getInstance();
//			year = c.get(Calendar.YEAR);
//			month = c.get(Calendar.MONTH);
//			day = c.get(Calendar.DAY_OF_MONTH);
//			showDialog(ID_DATEPICKER);
//			Toast toast = Toast.makeText(getApplicationContext(), "開始時間",
//					Toast.LENGTH_LONG);
//			toast.show();
//
//		}
//	};
//
//	@Override
//	protected Dialog onCreateDialog(int id) {
//		// TODO Auto-generated method stub
//		switch (id) {
//		case ID_DATEPICKER:
//			Toast.makeText(newwork.this, "選擇開始時間", Toast.LENGTH_LONG).show();
//			return new DatePickerDialog(this, date, year, month, day);
//
//		}
//		return null;
//	}
//
//	private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//		@Override
//		public void onDateSet(DatePicker view, int year, int monthOfYear,
//				int dayOfMonth) {
//			// TODO Auto-generated method stub
//			// date = (OnDateSetListener) star.getText();
//
//		}
//	};
//
//}
