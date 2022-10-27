package com.example.Freecharge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public CardView c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c1 = findViewById(R.id.cv_jio);
        c2 = findViewById(R.id.cv_airtel);
        c3 = findViewById(R.id.cv_vi);
        c4 = findViewById(R.id.cv_bsnl);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.cv_jio:

            case R.id.cv_airtel:

            case R.id.cv_vi:

            case R.id.cv_bsnl:
                i = new Intent(this,Planlist.class);
                startActivity(i);
                break;
        }
    }
}