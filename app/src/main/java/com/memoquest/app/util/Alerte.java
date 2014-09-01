package com.memoquest.app.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.memoquest.app.R;


public class Alerte {

    public static void showAlertDialog(String title, String message, Context context) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setIcon(R.drawable.ic_launcher)
                .show();
    }
}
