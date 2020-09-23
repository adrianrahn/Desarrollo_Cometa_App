package com.example.desarrollocometaapp.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.desarrollocometaapp.Classes.RequestClass;
import com.example.desarrollocometaapp.Classes.SharedPreferenceConfig;
import com.example.desarrollocometaapp.Views.MainActivity;
import com.example.desarrollocometaapp.R;
import com.google.android.material.textfield.TextInputLayout;
public class NewAccount extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout email, password, password2;
    Button createAccountButton;
    RequestClass requestClass;
    String url = "https://cometa.app/cafeteria/login/check";
    SharedPreferenceConfig sharedPreferenceConfig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        email = (TextInputLayout) findViewById(R.id.emailTextNewAccount);
        password = (TextInputLayout) findViewById(R.id.passwordTextNewAccount);
        password2 = (TextInputLayout) findViewById(R.id.passwordTextNewAccount2);
        createAccountButton = (Button) findViewById(R.id.createAccountBtn);
        createAccountButton.setOnClickListener(this);
        requestClass = new RequestClass();
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

    }

    public void onClick(View view) {
        if (view.getId() == R.id.createAccountBtn){
            String emailText = email.getEditText().getText().toString().trim();
            String passwordText = password.getEditText().getText().toString().trim();
            String passwordText2 = password2.getEditText().getText().toString().trim();

            if(!emailText.isEmpty() && !passwordText.isEmpty()) {

                if(passwordText == passwordText2){requestClass.postStringLoginRequests(getApplicationContext(), emailText, passwordText);
                    sharedPreferenceConfig.loginStatus(true);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);}else{
                    Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El campo de usuario o contraseña está vacío", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
