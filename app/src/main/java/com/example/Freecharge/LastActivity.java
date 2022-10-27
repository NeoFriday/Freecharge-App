package com.example.Freecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class LastActivity extends AppCompatActivity {

    LottieAnimationView success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        success = findViewById(R.id.success_lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(LastActivity.this,Feedback.class);
                startActivity(i1);
                finish();
            }
        },5000);
        success.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

    }
}