package com.hipo.tryouts.androidsdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private CheckBox screenshotOptionCheckbox;
    private String feedbackText;
    private String userName;
    private String versionName;
    private String screenshotBase64;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tryouts_activity_feedback);

        screenshotBase64 = getIntent().getStringExtra(SCREENSHOT);
        feedbackEditText = (EditText) findViewById(R.id.activity_feedback_edittext_feedback);
        userNameEditText = (EditText) findViewById(R.id.activity_feedback_edittext_username);
        screenshotOptionCheckbox = (CheckBox) findViewById(R.id.activity_feedback_checkbox_screenshot);
        submitButton = (Button) findViewById(R.id.activity_feedback_button_submit);
        submitButton.setOnClickListener(submitFeedback);

    }

    private final View.OnClickListener submitFeedback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (feedbackText.isEmpty() && userName.isEmpty()) {
                Toast toast = Toast.makeText(Tryouts.getApplicationContext(),R.string.tryouts_fill_all_fields,Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                try {
                    PackageInfo pinfo = Tryouts.getApplicationContext().getPackageManager().getPackageInfo(Tryouts.getApplicationContext().getPackageName(), 0);
                    versionName = pinfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                feedbackText = feedbackEditText.getText().toString();
                userName = userNameEditText.getText().toString();

                if (screenshotOptionCheckbox.isChecked()) {

                    Call<Feedback> call = TryoutsService.getApi().sendFeedback(
                            Tryouts.getApiKey()+":"+Tryouts.getApiSecret(),
                            Tryouts.getAppIdentifier(),
                            new Feedback(userName, versionName, feedbackText, screenshotBase64));
                    //TODO:What to do with response
                    call.enqueue(new Callback<Feedback>() {
                        @Override
                        public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                            finish();
                        }
                        @Override
                        public void onFailure(Call<Feedback> call, Throwable t) {

                        }
                    });

                }
                else {

                    Call<Feedback> call = TryoutsService.getApi().sendFeedback(
                            Tryouts.getApiKey()+":"+Tryouts.getApiSecret(),
                            Tryouts.getAppIdentifier(),
                            new Feedback(userName, versionName, feedbackText));

                    //TODO:What to do with response
                    call.enqueue(new Callback<Feedback>() {
                        @Override
                        public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                            finish();
                        }
                        @Override
                        public void onFailure(Call<Feedback> call, Throwable t) {
                        }
                    });
                }
            }
        }
    };
}
