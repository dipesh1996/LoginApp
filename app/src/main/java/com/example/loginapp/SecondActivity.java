package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {

    private EditText username;
    private EditText password1;
    private EditText password2;
    private EditText email;
    private EditText name;
    private EditText lname;
    private EditText age;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    username = (EditText)findViewById(R.id.username);
    password1 = (EditText)findViewById(R.id.pwd1);
    password2 = (EditText)findViewById(R.id.pwd2);
    email = (EditText)findViewById(R.id.email);
    name = (EditText)findViewById(R.id.name1);
    lname = (EditText)findViewById(R.id.lname);
    age = (EditText)findViewById(R.id.age);
    save = (Button)findViewById(R.id.save);
    cancel = (Button)findViewById(R.id.cancel);

    save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checking();
            }
        });
    cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(v.getContext(),MainActivity.class);
                startActivityForResult(redirect, 0);
            }
        });

    }

    private void checking() {
        String password123 = password1.getText().toString().trim();
        String password234 = password2.getText().toString().trim();
        String username1 = username.getText().toString().trim();
        String name1 = name.getText().toString().trim();
        String lname2 = lname.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String age1 = age.getText().toString().trim();
        int age3=0;
        try {
            age3 = Integer.parseInt(age1);
        }
        catch (NumberFormatException e){
        }

        if (!password123.isEmpty() && !password234.isEmpty() && !username1.isEmpty() && !name1.isEmpty() && !lname2.isEmpty() && !age1.isEmpty() && !mail.isEmpty()) {
            if (age3 < 1)
                Toast.makeText(SecondActivity.this, "Age error..", Toast.LENGTH_SHORT).show();
            else if (age3 > 99)
                Toast.makeText(SecondActivity.this, "Age error..", Toast.LENGTH_SHORT).show();
            else if (password123.equals(password234)) {
                Toast.makeText(SecondActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(SecondActivity.this, "Password Does not Match", Toast.LENGTH_SHORT).show();
        }
        else{
            if (password123.isEmpty() && password234.isEmpty() && username1.isEmpty() && name1.isEmpty() && lname2.isEmpty() && age1.isEmpty() && mail.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Details.", Toast.LENGTH_SHORT).show();
            }
            else if (username1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "UserName Error", Toast.LENGTH_SHORT).show();
            }
            else if (password123.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }
            else if (password234.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Re-Type Password ", Toast.LENGTH_SHORT).show();
            }
            else if (name1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            }
            else if (lname2.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            }
            else if (mail.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
            }
            else if (age1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
