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
            DialogInterface.OnClickListener positiveButtonClickListener,
            @StringRes int negativeButton,
            DialogInterface.OnClickListener negativeButtonClickListener) {

        new AlertDialog.Builder(context, R.style.tryouts_AlertDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveButtonClickListener)
                .setNegativeButton(negativeButton, negativeButtonClickListener)
                .create()
                .show();
    }

    public static void showDialog(
            Context context,
            String title,
            String message,
            String positiveButton,
            DialogInterface.OnClickListener positiveButtonClickListener,
            String negativeButton,
            DialogInterface.OnClickListener negativeButtonClickListener) {

        new AlertDialog.Builder(context, R.style.tryouts_AlertDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveButtonClickListener)
                .setNegativeButton(negativeButton, negativeButtonClickListener)
                .create()
                .show();
    }


}
