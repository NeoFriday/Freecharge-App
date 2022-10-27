package com.example.Freecharge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class Planlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> arr_id, arr_provider, arr_data, arr_validity;
    DBHelper DB;
    MyAdapter adapter;
    String admin, password;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planlist);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i1 = getIntent();
        admin = i1.getStringExtra("NAME");
        password = i1.getStringExtra("NUMBER");


        DB = new DBHelper(this);
        arr_id = new ArrayList<>();
        arr_provider = new ArrayList<>();
        arr_data = new ArrayList<>();
        arr_validity = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this, arr_id, arr_provider, arr_data, arr_validity, admin, password);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about){
            Toast.makeText(this, "Anant Patel", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.logout){
            Intent logout = new Intent(Planlist.this,gettingStarted.class);
            startActivity(logout);
            finish();
        }
        else if (id == R.id.back){
            Intent back = new Intent(Planlist.this,MainActivity.class);
            startActivity(back);
        }
        return true;
    }

    private void displayData() {
        Cursor cursor = DB.getData();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No entry exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                arr_id.add(cursor.getString(0));
                arr_provider.add(cursor.getString(1));
                arr_data.add(cursor.getString(2));
                arr_validity.add(cursor.getString(3));
            }
        }
    }
}