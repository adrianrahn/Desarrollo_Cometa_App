package com.example.desarrollocometaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, noAccountButton;
    TextInputLayout email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (TextInputLayout) findViewById(R.id.emailTextLogin);
        password = (TextInputLayout) findViewById(R.id.passwordTextLogin);
        loginButton = (Button) findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this);
        noAccountButton = (Button) findViewById(R.id.noAccountBtn);
        noAccountButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn){
            Toast.makeText(this, "login pressed", Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.noAccountBtn){
            Toast.makeText(this, "noAccount pressed", Toast.LENGTH_SHORT).show();

        }

    }
}
