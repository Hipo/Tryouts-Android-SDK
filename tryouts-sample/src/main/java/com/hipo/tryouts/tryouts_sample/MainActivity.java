package com.hipo.tryouts.tryouts_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hipo.tryouts.androidsdk.Tryouts;

/**
 * Created by ahmetmentes on 3/28/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button feedbackButton = (Button) findViewById(R.id.feedback_button);
        if (feedbackButton != null) {
            feedbackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tryouts.sendFeedback(MainActivity.this);
                }
            });
        }
    }
}
