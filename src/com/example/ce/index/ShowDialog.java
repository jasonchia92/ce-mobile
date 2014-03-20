package com.example.ce.index;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ShowDialog {
	private AlertDialog dialog;
	@SuppressWarnings("deprecation")
	public void Showdialog(Context context,String message){
		 dialog = new AlertDialog.Builder(context).create();
		 dialog.setMessage(message);
		 dialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, final int which) {
					dialog.dismiss();
				}	
			});
		 dialog.show();
		}
}
