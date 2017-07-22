package com.dp.patidatsamajdirectoryapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ps11 on 04/07/17.
 */

public class ProgressDialogUtil {
   public static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String message){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

    }

    public static void hideProgressDialog(){
        progressDialog.dismiss();
    }
}
