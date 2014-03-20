package com.example.ce.index;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ce.CE;
import com.example.ce.Poject_Tab;
import com.example.ce.R;

public class Home extends Activity {
	private GridView GridV1,GridV2;
	private Button Bt1,Bt2,Bt3;
	private TextView TVAnnouncement1,TVAnnouncement2,TVAnnouncement3,TVAnnouncement4;
	private int[] img = { R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher };
	private String []name1 = {"�������P�_�Ͼ�s��59��20��19�Ѳn�n��","�x�_���H�q�ϩ����F��79��99��","�x�n��","�x����"};
	private String []name2 = {"�w����","�I�u��","���I�u","�I�u��"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		View();
		GridViewValue();
		Onclick();
	}
	private void GridViewValue() {//BaseAdapter
		GridBaseAdapter gridbase =new GridBaseAdapter(Home.this,name1,name2);
		GridV1.setNumColumns(2);
		GridV1.setAdapter(gridbase);
		GridV2.setNumColumns(2);
		GridV2.setAdapter(gridbase);
	}
	private void Onclick() {
			GridV1.setOnItemClickListener(GI);
			GridV1.setOnTouchListener(GT);
			GridV2.setOnTouchListener(GT);
			Bt1.setOnClickListener(BT);
			Bt2.setOnClickListener(BT);
			Bt3.setOnClickListener(BT);
			TVAnnouncement1.setOnClickListener(TV);
			TVAnnouncement2.setOnClickListener(TV);
			TVAnnouncement3.setOnClickListener(TV);
			TVAnnouncement4.setOnClickListener(TV);
	}
	private void View() {
		GridV1=(GridView)findViewById(R.id.GridView1);//��h���iGrid
		GridV2=(GridView)findViewById(R.id.GridView2);//��h�s��Grid
		Bt1=(Button)findViewById(R.id.button1);//��h���i
		Bt2=(Button)findViewById(R.id.button2);//��h�M��
		Bt3=(Button)findViewById(R.id.button3);//��h�s��
		TVAnnouncement1=(TextView)findViewById(R.id.textViewannouncement1);
		TVAnnouncement2=(TextView)findViewById(R.id.textViewannouncement2);
		TVAnnouncement3=(TextView)findViewById(R.id.textViewannouncement3);
		TVAnnouncement4=(TextView)findViewById(R.id.textViewannouncement4);
	}
	private Button.OnClickListener BT =new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:// ��h���i
				Intent intent = new Intent();
				intent.setClass(Home.this, Announcement.class);
				startActivity(intent);
//				finish();
				Toast.makeText(Home.this, "��h���i!!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.button2://��h�M��
				Intent intent1 = new Intent();
				intent1.setClass(Home.this, Poject_Tab.class);
				startActivity(intent1);
				Toast.makeText(Home.this, "��h�M��!!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.button3://��h�s��
				Toast.makeText(Home.this, "��h�s��!!", Toast.LENGTH_SHORT).show();
				Intent intent2 = new Intent();
				intent2.setClass(Home.this, CE.class);
				startActivity(intent2);
				Toast.makeText(Home.this, "��h�M��!!", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
	private TextView.OnClickListener TV =new TextView.OnClickListener(){
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.textViewannouncement1://���i1
				Toast.makeText(Home.this, "�N�b2��15���|�����", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textViewannouncement2://���i2
				Toast.makeText(Home.this, "�Y�N���s���Ю�", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textViewannouncement3://���i3
				Toast.makeText(Home.this, "���ƪ��k��b12��10���n�X��", Toast.LENGTH_SHORT).show();
				break;
			case R.id.textViewannouncement4://���i4
				Toast.makeText(Home.this, "�o�~��", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};
	private GridView.OnItemClickListener GI = new GridView.OnItemClickListener(){//����
		@Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	    	String a= (String)(GridV1.getItemAtPosition(position));//����
//	    	Toast.makeText(Home.this, a, Toast.LENGTH_SHORT).show();
	    	Intent intent1 = new Intent();
			intent1.setClass(Home.this, Poject_Tab.class);
			startActivity(intent1);
		}
	};
	private GridView.OnTouchListener GT = new GridView.OnTouchListener(){//GridView���i��
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return MotionEvent.ACTION_MOVE == event.getAction() ? true
                    : false;
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item)//���
    {
        switch (item.getItemId())
        {
        case R.id.changeaccount:
            Toast.makeText(Home.this, "�����b��!!", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.personnelmanagement:
            Toast.makeText(Home.this, "�H�ƺ޲z!!", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.sharingfile:
            Toast.makeText(Home.this, "�@���ɮ�!!", Toast.LENGTH_SHORT).show();
            return true;
 
        case R.id.signout:
            Toast.makeText(Home.this, "�b���n�X!!", Toast.LENGTH_SHORT).show();
            return true;
 
        default:
            return super.onOptionsItemSelected(item);
        }
    }    

}