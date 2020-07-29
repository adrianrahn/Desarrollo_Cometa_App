package com.example.desarrollocometaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button plusButton, minusButton, scanButton, saveButton;
    TextView quantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityText = (TextView) findViewById(R.id.quantityTextView);
        plusButton = (Button) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);
        scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.plusButton:
                Toast.makeText(this, "plusButton pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.minusButton:
                Toast.makeText(this, "minusButton pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scanButton:
                Toast.makeText(this, "scanButton pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveButton:
                Toast.makeText(this, "saveButton pressed", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
