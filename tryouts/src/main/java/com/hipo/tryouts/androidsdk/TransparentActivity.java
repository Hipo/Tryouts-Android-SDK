package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class TransparentActivity extends AppCompatActivity {

    public static final String LATEST_RELEASE = "latestRelease";

    public static Intent newIntent(Context context, AppRelease latestRelease) {
        Intent intent = new Intent(context, TransparentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(LATEST_RELEASE, latestRelease);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppRelease appRelease = getIntent().getParcelableExtra(LATEST_RELEASE);

        DialogUtil.showDialog(
                this,
                getString(R.string.tryouts_new_version_found),
                String.format(getString(R.string.tryouts_is_available_as_an_updated), appRelease.getName()),
                getString(R.string.tryouts_remind_me_later),
                getString(R.string.tryouts_update_now),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
    }
}
