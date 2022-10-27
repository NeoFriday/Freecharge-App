package com.example.Freecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class Verification extends AppCompatActivity {

    LottieAnimationView verification, progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verification = findViewById(R.id.verification_lottie);
        progress = findViewById(R.id.progress_lottie);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(Verification.this,Confirmation.class);
                startActivity(i1);
                finish();
            }
        },5000);

        verification.animate().translationY(1600).setDuration(1000).setStartDelay(4000);
        progress.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

    }
}