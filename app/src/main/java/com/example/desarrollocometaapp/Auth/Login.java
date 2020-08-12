package com.example.desarrollocometaapp.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.example.desarrollocometaapp.Views.MainActivity;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, noAccountButton;
    TextInputLayout email, password;
    private RequestQueue myQueue;
    RequestClass requestClass;
    String url = "https://cometa.app/cafeteria/login/check";


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
        requestClass = new RequestClass();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn){
            String emailText = email.getEditText().getText().toString().trim();
            String passwordText = password.getEditText().getText().toString().trim();

            if(!emailText.isEmpty() && !passwordText.isEmpty()){
                requestClass.postStringLoginRequest(getApplicationContext(), url, emailText, passwordText);

               /* Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/}
            else{
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.noAccountBtn){
            startActivity(new Intent(this, NewAccount.class));
        }

    }

    private void moveToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
