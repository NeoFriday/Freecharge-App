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

public class Register extends AppCompatActivity {

    TextInputEditText et_user, et_password, et_confirmPassword;
    Button btn_reg, btn_login1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_user=findViewById(R.id.et_user_reg);
        et_password=findViewById(R.id.et_password);
        et_confirmPassword=findViewById(R.id.et_confirmPassword);
        btn_reg=findViewById(R.id.btn_register1);
        btn_login1=findViewById(R.id.btn_login1);

        DB = new DBHelper(this);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et_user.getText().toString();
                String pass = et_password.getText().toString();
                String confirmPass = et_confirmPassword.getText().toString();

                if (user.equals("") || pass.equals("") || confirmPass.equals(""))
                {
                    Toast.makeText(Register.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(confirmPass))
                    {
                        Boolean check = DB.checkUser(user);
                        if (check == false)
                        {
                           Boolean regResult = DB.addUser(user,pass);
                           if (regResult == true)
                           {
                               Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(Register.this, Login.class);
                               startActivity(intent);
                           }
                           else
                           {
                               Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                           }
                        }
                        else
                        {
                            Toast.makeText(Register.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
