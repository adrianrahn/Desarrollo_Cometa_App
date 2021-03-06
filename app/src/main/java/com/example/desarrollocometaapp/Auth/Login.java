package com.example.desarrollocometaapp.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.desarrollocometaapp.Classes.SharedPreferenceConfig;
import com.example.desarrollocometaapp.Views.MainActivity;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    int i = 0;
    Map<String, String> respuesta = new HashMap<String, String>();
    Button loginButton, noAccountButton;
    TextInputLayout email, password;
    RequestClass requestClass;
    String url = "https://cometa.app/cafeteria/login/check";
    SharedPreferenceConfig sharedPreferenceConfig;


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
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn){
            String emailText = email.getEditText().getText().toString().trim();
            String passwordText = password.getEditText().getText().toString().trim();


            if(!emailText.isEmpty() && !passwordText.isEmpty()){

                requestClass.postStringLoginRequests(getApplicationContext(), emailText, passwordText);
                sharedPreferenceConfig.loginStatus(true);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else{
                Toast.makeText(this, "Correo y contraseña incorrecto", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.noAccountBtn){
           startActivity(new Intent(this, NewAccount.class));
        }
    }

    }

