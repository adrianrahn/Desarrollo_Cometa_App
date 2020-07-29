package com.example.desarrollocometaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class NewAccount extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout email, password;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        email = (TextInputLayout) findViewById(R.id.emailTextNewAccount);
        password = (TextInputLayout) findViewById(R.id.passwordTextNewAccount);
        createAccountButton = (Button) findViewById(R.id.createAccountBtn);
        createAccountButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.createAccountBtn){
            Toast.makeText(this, "CreateAccount pressed", Toast.LENGTH_SHORT).show();
        }
    }
}
