package com.example.loginapp;

import android.Manifest;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button newuser;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.pwd1);
        login = (Button)findViewById(R.id.loginbutton);

        newuser = (Button)findViewById(R.id.newuser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(v.getContext(),SecondActivity.class);
                startActivityForResult(signup, 0);
            }
        });
    }
    public void readData(String user, String pass) {
        try {
            int flag =0;
            File root = Environment.getExternalStorageDirectory();
            File file = new File(root, "loginapp.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                String TempUser = tokens[4];
                String TempPass = tokens[5];
                if (user.equals(TempUser) && pass.equals(TempPass)) {
                    flag = 0;
                    Toast.makeText(MainActivity.this, "Successfull Login", Toast.LENGTH_SHORT).show();
                }
            }
                bufferedReader.close();
        }
        catch(Exception ex)
            {
                ex.printStackTrace();
            }
    }
    private void validate() {
        String uname = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                if (uname.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter username or password", Toast.LENGTH_SHORT).show();
                } else
                    readData(uname, pass);
            } else {
                requestStoragePermissions();
                if (uname.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter username or password", Toast.LENGTH_SHORT).show();
                } else
                    readData(uname,pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        private void requestStoragePermissions() {
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
    }
}




