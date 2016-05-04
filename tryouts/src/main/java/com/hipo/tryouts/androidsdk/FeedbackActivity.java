package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    private static final String SCREENSHOT = "screenshotBase64";


    public static Intent newIntent(Context context, String screenshotBase64) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SCREENSHOT, screenshotBase64);
        return intent;
    }

    private Button submitButton;
    private EditText feedbackEditText;
    private EditText userNameEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tryouts_activity_feedback);

        final String screenshotBase64 = getIntent().getStringExtra(SCREENSHOT);

        submitButton = (Button) findViewById(R.id.activity_feedback_button_submit);
        feedbackEditText = (EditText) findViewById(R.id.activity_feedback_edittext_feedback);
        userNameEditText = (EditText) findViewById(R.id.activity_feedback_edittext_username);

    }
}
