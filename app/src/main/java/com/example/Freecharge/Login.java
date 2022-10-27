package com.example.Freecharge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputEditText et_user2, et_password2;
    Button btn_login, btn_register;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user2 = findViewById(R.id.et_user2);
        et_password2 = findViewById(R.id.et_password2);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        myDB = new DBHelper(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = et_user2.getText().toString();
                String pass = et_password2.getText().toString();

                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(Login.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else if (et_user2.getText().toString().equals("Infinity") && et_password2.getText().toString().equals("1234554321"))
                {
                    Intent i1 = new Intent(Login.this,Admin.class);
                    i1.putExtra("NAME", user);
                    i1.putExtra("NUMBER" , pass);
                    startActivity(i1);
                    finish();
                }
                else
                {
                    Boolean checkUsernamePass = myDB.checkUserPass(user,pass);
                    if (checkUsernamePass == true)
                    {
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent i49 = new Intent(Login.this,MainActivity.class);
//                        i49.putExtra("NAME", user);
//                        i49.putExtra("NUMBER" , pass);
                        startActivity(i49);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}