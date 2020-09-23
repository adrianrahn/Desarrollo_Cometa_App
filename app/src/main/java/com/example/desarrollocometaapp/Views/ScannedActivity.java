package com.example.desarrollocometaapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desarrollocometaapp.Classes.SharedPreferenceConfig;
import com.example.desarrollocometaapp.R;
import com.example.desarrollocometaapp.Classes.RequestClass;

public class ScannedActivity extends AppCompatActivity implements View.OnClickListener {
    private Button plusButton, minusButton, saveButton;
    private TextView quantityText,productText, priceText, totalPriceText;
    private int quantity = 1;
    RequestClass requester;
    SharedPreferenceConfig sharedPreferenceConfig;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned);

        productText = (TextView) findViewById(R.id.productTextViewScanned);
        productText.setText(getProductName());
        quantityText = (TextView) findViewById(R.id.quantityTextViewScanned);
        quantityText.setText("" + quantity);
        totalPriceText = (TextView) findViewById(R.id.sumaTextViewScanned);
        totalPriceText.setText(getProductPrice() + " €");
        priceText = (TextView) findViewById(R.id.priceTextViewScanned);
        priceText.setText(getProductPrice() + " €");
        plusButton = (Button) findViewById(R.id.plusButtonScanned);
        plusButton.setOnClickListener(this);
        minusButton = (Button) findViewById(R.id.minusButtonScanned);
        minusButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.saveButtonScanned);
        saveButton.setOnClickListener(this);
        requester = new RequestClass();
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.plusButtonScanned:
                quantity += 1;
                quantityText.setText("" + quantity);
                double d = Double.parseDouble(getProductPrice());
                totalPrice = quantity * d;
                totalPriceText.setText("" + totalPrice + "€");
                break;
            case R.id.minusButtonScanned:
                if(quantity >= 2){
                    quantity -= 1;
                    quantityText.setText("" + quantity);
                    double parseDouble = Double.parseDouble(getProductPrice());
                    totalPrice = quantity * parseDouble;
                    totalPriceText.setText("" + totalPrice + "€");
                }
                break;
        case R.id.saveButtonScanned:
            requester.postSaveProductRequest(getApplicationContext(), sharedPreferenceConfig.getId(), getProductId(), quantityText.getText().toString());
            Intent intent = new Intent(this, RealizandoCompra.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            break;

        default:
        break;
        }
    }

    public String getProductId(){
        String id = getIntent().getStringExtra("JsonObjectId");
        return id;
    }

    public String getProductName(){
        String id = getIntent().getStringExtra("productName");
        return id;
    }
    public String getProductPrice(){
        String id = getIntent().getStringExtra("productPrice");
        return id;
    }

}