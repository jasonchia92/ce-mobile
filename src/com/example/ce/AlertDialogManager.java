package com.example.ce;

import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogManager {
	public void showAlertDialog(Context context, String title, String message
			) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Showing Alert Message
		alertDialog.show();
	}
}
