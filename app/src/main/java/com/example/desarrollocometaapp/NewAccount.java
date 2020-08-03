package com.example.desarrollocometaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            String emailText = email.getEditText().getText().toString().trim();
            String passwordText = password.getEditText().getText().toString().trim();

            if(!emailText.isEmpty() && !passwordText.isEmpty()){
                Toast.makeText(this, emailText + passwordText, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);}
            else{
                Toast.makeText(this, "El campo de usuario o contraseña está vacío", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
