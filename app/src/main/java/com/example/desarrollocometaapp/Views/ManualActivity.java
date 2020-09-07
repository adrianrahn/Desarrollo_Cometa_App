package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollocometaapp.Classes.Producto;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

import java.util.ArrayList;

public class ManualActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button plusButton, minusButton, saveButton;
    private TextView quantityText;
    private Spinner productsSpinner;
    private int quantity = 0;
    RequestClass requester;
    String productId;

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
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,R.layout.custom_spinner, getNameArray());
        productsSpinner.setAdapter(adapter);
        productsSpinner.setOnItemSelectedListener(this);

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
                String respuesta = requester.postSaveProductRequest(getApplicationContext(), "https://cometa.app/cafeteria/stock/newsale", "1", productId, quantityText.getText().toString());
                Toast.makeText(this, respuesta, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            productId = getIdArray().get(position);
        }catch (Error error){
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getUserId(){
        String id = getIntent().getStringExtra("userId");
        return id;
    }

    public ArrayList<String> getNameArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("nameArray");
        return list;
    }
    public ArrayList<String> getIdArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("idArray");
        return list;
    }public ArrayList<String> getPriceArray(){
        ArrayList<String> list = getIntent().getStringArrayListExtra("priceArray");
        return list;
    }
}