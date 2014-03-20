package com.example.ce.index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ce.R;

public class Login extends Activity {
	private EditText EdAccount, EdPasswd;
	private String StAccount, StPasswd, Android_ID, tmDevice, tmSerial,
			Response;
	private Button BtLogin;
	private String URLLogin = "http://apps.csie.stu.edu.tw/ce/mobile_service.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		View();
		Onclick();
	}

	private void Onclick() {
		BtLogin.setOnClickListener(btLogin);

	}

	private void View() {
		EdAccount = (EditText) findViewById(R.id.editText1);// �b����J
		EdPasswd = (EditText) findViewById(R.id.editText2);// �K�X��J
		BtLogin = (Button) findViewById(R.id.button1);// �n�J���s
		StAccount = EdAccount.getText().toString();// ���ȱb��
		StPasswd = EdPasswd.getText().toString();// ���ȱK�X
	}

	// �n�J
	private Button.OnClickListener btLogin = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent();
			intent.setClass(Login.this, Home.class);
			Toast.makeText(Login.this, "�s�u���\", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			finish();

		}
	};
	private Runnable accountnull = new Runnable() {
		@Override
		public void run() {
			ShowDialog dialog = new ShowDialog();
			dialog.Showdialog(Login.this, "�b�����o���ť�");// �Nvalue�Ǩ�Dialog��Aerlog���
		}
	};

}
