package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

class DialogUtil {

    public static void showDialog(
            Context context,
            @StringRes int title,
            @StringRes int message,
            @StringRes int positiveButton,
            @StringRes int negativeButton,
            DialogInterface.OnClickListener onClickListener) {

        new AlertDialog.Builder(context, R.style.tryouts_AlertDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, onClickListener)
                .setNegativeButton(negativeButton, null)
                .create()
                .show();
    }

    public static void showDialog(
            Context context,
            String title,
            String message,
            String positiveButton,
            String negativeButton,
            DialogInterface.OnClickListener positiveButtonClickListener) {

        new AlertDialog.Builder(context, R.style.tryouts_AlertDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveButtonClickListener)
                .setNegativeButton(negativeButton, null)
                .create()
                .show();
    }


}
