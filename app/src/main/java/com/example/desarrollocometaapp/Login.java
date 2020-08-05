package com.example.desarrollocometaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, noAccountButton;
    TextInputLayout email, password;
    private RequestQueue myQueue;


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
            String emailText = email.getEditText().getText().toString().trim();
            String passwordText = password.getEditText().getText().toString().trim();

            if(!emailText.isEmpty() && !passwordText.isEmpty()){

                String data = "{" + "\"email\"" + ":" + "\"" + emailText + "\"," + "\"password\"" + ":" + "\"" + passwordText + "\"" + "}";
                //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                postInfo(data);
                /*Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/}
            else{
                Toast.makeText(this, "El campo de usuario o contraseña está vacío", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.noAccountBtn){
            startActivity(new Intent(this, NewAccount.class));
        }

    }
//todo: Post
    private void postInfo(String data) {
        String url = "https://cometa.app/cafeteria/home/pruebapost";
        myQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap< String, String>();
                params.put("email", email.getEditText().getText().toString().trim());
                params.put("password", password.getEditText().getText().toString().trim());
                return params;
            }
        };
        myQueue.add(stringRequest);
    }
}
