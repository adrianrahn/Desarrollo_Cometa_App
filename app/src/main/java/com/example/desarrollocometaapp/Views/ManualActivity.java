package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

public class ManualActivity extends AppCompatActivity implements View.OnClickListener {

    private Button plusButton, minusButton, saveButton;
    private TextView quantityText;
    private Spinner productsSpinner;
    private int quantity = 0;
    RequestClass requester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        quantityText = (TextView) findViewById(R.id.quantityTextViewManual);
        quantityText.setText("" + quantity);
        plusButton = (Button) findViewById(R.id.plusButtonManual);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButtonManual);
        minusButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButtonManual);
        saveButton.setOnClickListener(this);
        productsSpinner = (Spinner) findViewById(R.id.productsSpinnerManual);
        requester = new RequestClass();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.plusButtonManual:
                quantity += 1;
                quantityText.setText("" + quantity);
                break;
            case R.id.minusButtonManual:
                if(quantity >= 1){
                    quantity -= 1;
                    quantityText.setText("" + quantity);
                }
                break;

            case R.id.saveButtonManual:
                requester.getStringRequest(getApplicationContext(), "https://cometa.app/cafeteria/stock/getproducts");
                break;
            default:
                break;
        }
    }
}