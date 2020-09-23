package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollocometaapp.Auth.Login;
import com.example.desarrollocometaapp.Classes.SharedPreferenceConfig;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

import java.util.ArrayList;

public class ManualActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button plusButton, minusButton, saveButton;
    private TextView quantityText, priceText, totalPriceText;
    private Spinner productsSpinner;
    private int quantity = 1;
    private double totalPrice;
    String price;

    RequestClass requester;
    String productId;
    SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        quantityText = (TextView) findViewById(R.id.quantityTextViewManual);
        quantityText.setText("" + quantity);
        priceText = (TextView) findViewById(R.id.priceTextViewManual);
        totalPriceText = (TextView) findViewById(R.id.sumaTextViewManual);
        plusButton = (Button) findViewById(R.id.plusButtonManual);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButtonManual);
        minusButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButtonManual);
        saveButton.setOnClickListener(this);
        productsSpinner = (Spinner) findViewById(R.id.productsSpinnerManual);
        requester = new RequestClass();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,R.layout.custom_spinner, getNameArray());
        productsSpinner.setAdapter(adapter);
        productsSpinner.setOnItemSelectedListener(this);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.plusButtonManual:
                quantity += 1;
                quantityText.setText("" + quantity);
                double d = Double.parseDouble(price);
                totalPrice = quantity * d;
                totalPriceText.setText("" + totalPrice + "€");

                break;
            case R.id.minusButtonManual:
                if(quantity >= 2){
                    quantity -= 1;
                    double parseDouble = Double.parseDouble(price);
                    totalPrice = quantity * parseDouble;
                    quantityText.setText("" + quantity);
                    totalPriceText.setText("" + totalPrice + " €");
                }
                break;

            case R.id.saveButtonManual:
                String id =  sharedPreferenceConfig.getId();
                requester.postSaveProductRequest(getApplicationContext(), id , productId, quantityText.getText().toString());
                Intent intent = new Intent(this, RealizandoCompra.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            productId = getIdArray().get(position);
            price = getPriceArray().get(position);
            priceText.setText(price + " €");
            totalPriceText.setText(price + " €");

        }catch (Error error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public ArrayList<String> getNameArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("nameArray");
        return list;
    }
    public ArrayList<String> getIdArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("idArray");
        return list;
    }
    public ArrayList<String> getPriceArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("priceArray");
        return list;
    }

}