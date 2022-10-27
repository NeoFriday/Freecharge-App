package com.example.Freecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Admin extends AppCompatActivity {

    TextInputEditText txt_id, txt_provider, txt_data, txt_validity;
    Button btn_insert, btn_update, btn_delete, btn_display;
    DBHelper DB;
    String admin, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent i1 = getIntent();
        admin = i1.getStringExtra("NAME");
        password = i1.getStringExtra("NUMBER");

        txt_id = findViewById(R.id.txt_id);
        txt_provider = findViewById(R.id.txt_provider);
        txt_data = findViewById(R.id.txt_data);
        txt_validity = findViewById(R.id.txt_validity);
        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_display = findViewById(R.id.btn_display);

        DB = new DBHelper(this);

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this,Planlist.class);
                intent.putExtra("NAME", admin);
                intent.putExtra("NUMBER" , password);
                startActivity(intent);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = txt_id.getText().toString();
                String providerTXT = txt_provider.getText().toString();
                String dataTXT = txt_data.getText().toString();
                String validityTXT = txt_validity.getText().toString();

                Boolean checkinsertdata = DB.insertdata(idTXT, providerTXT, dataTXT, validityTXT);
                if (checkinsertdata == true)
                {
                    Toast.makeText(Admin.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Admin.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up_id = txt_id.getText().toString();
                String up_provider = txt_provider.getText().toString();
                String up_data = txt_data.getText().toString();
                String up_validity = txt_validity.getText().toString();

                Boolean check = DB.updatedata(up_id, up_provider, up_data, up_validity);

                if (check == true){
                    Toast.makeText(Admin.this, "Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Admin.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up_id = txt_id.getText().toString();

                Boolean check = DB.deletedata(up_id);

                if (check == true){
                    Toast.makeText(Admin.this, "Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Admin.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}