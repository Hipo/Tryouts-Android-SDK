package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;
    private EditText userNameEditText;
    private CheckBox screenshotOptionCheckbox;
    private String versionName;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tryouts_activity_feedback);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        feedbackEditText = (EditText) findViewById(R.id.feedback_edittext_feedback);
        userNameEditText = (EditText) findViewById(R.id.feedback_edittext_username);
        screenshotOptionCheckbox = (CheckBox) findViewById(R.id.feedback_checkbox_screenshot);
        findViewById(R.id.feedback_button_submit).setOnClickListener(submitFeedback);
    }

    private final View.OnClickListener submitFeedback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String feedbackText = feedbackEditText.getText().toString();
            String userName = userNameEditText.getText().toString();

            if (feedbackEditText.getText().toString().isEmpty() && userNameEditText.getText().toString().isEmpty()) {
                Toast toast = Toast.makeText(Tryouts.getApplicationContext(), R.string.tryouts_fill_all_fields, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                try {
                    PackageInfo pinfo = Tryouts.getApplicationContext().getPackageManager().getPackageInfo(Tryouts.getApplicationContext().getPackageName(), 0);
                    versionName = pinfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                if (screenshotOptionCheckbox.isChecked()) {

                    Call<Feedback> call = TryoutsService.getApi().sendFeedback(
                            Tryouts.getApiKey() + ":" + Tryouts.getApiSecret(),
                            Tryouts.getAppIdentifier(),
                            new Feedback(userName, versionName, feedbackText, Tryouts.getScreenshotBase64()));
                    //TODO:What to do with response
                    call.enqueue(new Callback<Feedback>() {
                        @Override
                        public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                            Toast.makeText(Tryouts.getApplicationContext(),R.string.tryouts_successful,Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Feedback> call, Throwable t) {
                            Toast.makeText(Tryouts.getApplicationContext(),R.string.tryouts_failed,Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

                } else {

                    Call<Feedback> call = TryoutsService.getApi().sendFeedback(
                            Tryouts.getApiKey() + ":" + Tryouts.getApiSecret(),
                            Tryouts.getAppIdentifier(),
                            new Feedback(userName, versionName, feedbackText));

                    //TODO:What to do with response
                    call.enqueue(new Callback<Feedback>() {
                        @Override
                        public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                            Toast.makeText(Tryouts.getApplicationContext(),R.string.tryouts_successful,Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Feedback> call, Throwable t) {
                            Toast.makeText(Tryouts.getApplicationContext(),R.string.tryouts_failed,Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Tryouts.setScreenshotBase64(null);
    }
}
