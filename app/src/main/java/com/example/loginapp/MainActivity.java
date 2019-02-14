package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private void validate()
    {
        String userName = username.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
          if (userName.equals("admin") && userPassword.equals("admin")) {
                Toast.makeText(MainActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
            } else {
              Toast.makeText(MainActivity.this, "Username or Password Incorrect ", Toast.LENGTH_SHORT).show();
                count = count + 1;
                if(count>2)
                    System.exit(0);
            }
    }
}
