package com.example.Freecharge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Confirmation extends AppCompatActivity {

    private static final String CHANNEL_ID = "Freecharge";
    private static final int NOTIFICATION_ID = 1;
    TextInputEditText et_otp;
    Button btn_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        et_otp = findViewById(R.id.et_otp);
        btn_otp = findViewById(R.id.btn_otp);

        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();
                addNotification();

                String number = et_otp.getText().toString();
                Intent i2 = new Intent(Confirmation.this, ConfirmOTP.class);
                i2.putExtra("NUMBER", number);
                startActivity(i2);
            }
        });
    }
    private void createNotification(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            CharSequence name = "One step to continue";
            String desc = "Your One Time Password is 5165";

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(desc);

            NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notification_logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification_logo));
        builder.setContentTitle("One step to continue");
        builder.setContentText("Your One Time Password is 5165");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}