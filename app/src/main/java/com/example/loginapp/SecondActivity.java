package com.example.loginapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.lang.*;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
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
        username = (EditText) findViewById(R.id.username);
        password1 = (EditText) findViewById(R.id.pwd1);
        password2 = (EditText) findViewById(R.id.pwd2);
        email = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name1);
        lname = (EditText) findViewById(R.id.lname);
        age = (EditText) findViewById(R.id.age);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checking();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirect = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(redirect, 0);
            }
        });

    }

    public void checking() {
        //Conversion into String
        String password123 = password1.getText().toString().trim();
        String password234 = password2.getText().toString().trim();
        String username1 = username.getText().toString().trim();
        String name1 = name.getText().toString().trim();
        String lname2 = lname.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String age1 = age.getText().toString().trim();
        int age3 = 0;
        try {
            age3 = Integer.parseInt(age1);
        } catch (NumberFormatException e) { }
        if (!password123.isEmpty() && !password234.isEmpty() && !username1.isEmpty() && !name1.isEmpty() && !lname2.isEmpty() && !age1.isEmpty() && !mail.isEmpty()) {
            if (age3 < 1)
                Toast.makeText(SecondActivity.this, "Age error..", Toast.LENGTH_SHORT).show();
            else if (age3 > 99)
                Toast.makeText(SecondActivity.this, "Age error..", Toast.LENGTH_SHORT).show();
            else if (password123.equals(password234)) {
                if (ContextCompat.checkSelfPermission(SecondActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        FileWriter writer;
                        String line = String.format("%s,%s,%s,%s,%s,%s\n", name1, lname2, age1, mail, username1, password123);
                        File root = Environment.getExternalStorageDirectory();
                        File file = new File(root, "loginapp.txt");
                        if (file.exists()) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                            bw.append(line);
                            bw.flush();
                            Toast.makeText(SecondActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
                            startActivityForResult(i,0);
                        } else {
                            writer = new FileWriter(file);
                            writer.append(line);
                            writer.flush();
                            writer.close();
                            Toast.makeText(SecondActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
                            startActivityForResult(i,0);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        new AlertDialog.Builder(this)
                                .setTitle("permission needed").setMessage("Storage permission needed")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                    } else
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    try {
                        FileWriter writer;
                        String line = String.format("%s,%s,%s,%s,%s,%s\n", name1, lname2, age1, mail, username1, password123);
                        File root = Environment.getExternalStorageDirectory();
                        File file = new File(root, "loginapp.txt");
                        if (file.exists()) {
                            BufferedWriter bfw =
                                    new BufferedWriter(new FileWriter(file, true));
                            bfw.append(line);
                            bfw.flush();
                            Toast.makeText(SecondActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
                            startActivityForResult(i,0);
                        } else {
                            writer = new FileWriter(file);
                            writer.append(line);
                            writer.flush();
                            writer.close();
                            Toast.makeText(SecondActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
                            startActivityForResult(i,0);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else {
                Toast.makeText(SecondActivity.this, "Password Does not Match", Toast.LENGTH_SHORT).show();
            }
        }
        else if (password123.isEmpty() && password234.isEmpty() && username1.isEmpty() && name1.isEmpty() && lname2.isEmpty() && age1.isEmpty() && mail.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Details.", Toast.LENGTH_SHORT).show();
            } else if (username1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "UserName Error", Toast.LENGTH_SHORT).show();
            } else if (password123.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            } else if (password234.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Re-Type Password ", Toast.LENGTH_SHORT).show();
            } else if (name1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            } else if (lname2.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            } else if (mail.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
            } else if (age1.isEmpty()) {
                Toast.makeText(SecondActivity.this, "Please Enter Age", Toast.LENGTH_SHORT).show();
            }
    }
}




